package com.bigeye.mycust.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth.common.OAuthException;
import org.springframework.security.oauth.common.signature.SharedConsumerSecret;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.bigeye.mycust.dao.UserDao;
import com.bigeye.mycust.model.Account;
import com.bigeye.mycust.model.Language;
import com.bigeye.mycust.model.user.User;
import com.bigeye.mycust.oauth.OAuthConsumerDetails;
import com.bigeye.mycust.service.AccountService;
import com.bigeye.mycust.service.BaseServiceImpl;
import com.bigeye.mycust.service.UserService;

import freemarker.template.Configuration;

@Service("userService")
@Transactional
public final class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService, UserDetailsService, ConsumerDetailsService {

	private static final String NONE_CONFUSING_CHARSET = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz23456789";

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${mail.fromAddress}")
	private String fromAddress;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Configuration configuration;

	@Autowired
	private MessageSource messageSource;
	
	@Value("${oAuthConsumerName}")
    String consumerName;
	
	@Value("${oAuthConsumerKey}")
    String consumerKey;
	
	@Value("${oAuthConsumerSecret}")
    String consumerSecret;
	
	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public void saveOrUpdate(User user) {
		if (user.getId() == null && StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(passwordEncoder.encode(user.getNewPassword()));
		} else if (StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(passwordEncoder.encode(user.getNewPassword()));
		}
		userDao.save(user);
	}

	@Override
	public User loadUserByUsername(String login) {
		final User user = userDao.findUserByUuidOrUsernameOrOpenId(login);
		if (user == null) {
			throw new UsernameNotFoundException("login " + login + " not found");
		}

		return user;
	}


	@Override
	public void resetUserPassword(User user) {
		user.setNewPassword(RandomStringUtils.random(7, NONE_CONFUSING_CHARSET));
		saveOrUpdate(user);
	}


	@Override
	public boolean forgotUserPassword(String email) {
		boolean found = false;
		final User user = loadUserByUsername(email);
		if(user != null){
			found = true;
			resetUserPassword(user);
		}
		return found;
	}

	@Override
	public void delete(Long id, String username) {
		final User loggedUser = loadUserByUsername(username);
		final User user = load(id);
		if(user.getId().equals(loggedUser.getId())){
			throw new AccessDeniedException("Not allowed to view or update user");
		}
		user.setEnabled(false);
	}

	@Override
	@Transactional
	public boolean registerNewUser(String email, String firstname, String lastname, Locale locale) {
		if (userDao.findByUsername(email) != null) {
			// user already exist
			return false;
		}

		final String[] roles = { "ROLE_USER" };
		final String password = RandomStringUtils.random(7, NONE_CONFUSING_CHARSET);

		final User user = new User(email, password, firstname, lastname, true, roles, Language.valueOf(locale.getLanguage()));
		saveOrUpdate(user);

		mailSender.send(new RegisterMimeMessagePreparator(configuration, messageSource, fromAddress, user.getUsername(), user, password, locale));

		return true;
	}

	private static final class RegisterMimeMessagePreparator implements MimeMessagePreparator {

		private final Configuration configuration;
		private final MessageSource messageSource;
		private final String fromAddress;
		private final String toAddress;
		private final User user;
		private final String password;
		private final Locale locale;

		public RegisterMimeMessagePreparator(Configuration configuration, MessageSource messageSource,
				String fromAddress, String toAddress, User user, String password, Locale locale) {
			this.configuration = configuration;
			this.messageSource = messageSource;
			this.fromAddress = fromAddress;
			this.toAddress = toAddress;
			this.user = user;
			this.password = password;
			this.locale = locale;
		}

		@Override
		public void prepare(MimeMessage mimeMessage) throws Exception {
			final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
			message.setTo(toAddress);
			message.setFrom(fromAddress);

			final Map<String, Object> model = new HashMap<String, Object>();
			model.put("user", user);
			model.put("password", password);

			final String text = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("mail/register_" + locale.getLanguage() + ".ftl", "UTF-8"), model);
			message.setText(text, true);
			message.setSubject(messageSource.getMessage("mail.register.subject", null, locale));
		}
	}

	@Override
	public User load(Long id) {
		return userDao.findOne(id);
	}

	@Override
	public List<User> getList() {
		return userDao.findAll();
	}

	@Override
	public User loadEager(Long id) {
		final User user = userDao.findOne(id);
		if (user != null) {
			Hibernate.initialize(user);
			Hibernate.initialize(user.getAuthorities());
		}
		return user;
	}

	@Override
	public ConsumerDetails loadConsumerByConsumerKey(String consumerKey)
			throws OAuthException {
	     if( consumerKey == null )
	            throw new OAuthException("No credentials found for the consumer key [" + consumerKey + "]");
	 
	        if( !consumerKey.equals( this.consumerKey ) )
	            throw new OAuthException("No credentials found for the consumer key [" + consumerKey + "]");
	 
	        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        authorities.add( new SimpleGrantedAuthority("ROLE_OAUTH_APPDIRECT") );
	 
			BaseConsumerDetails baseConsumerDetails = new BaseConsumerDetails();
			baseConsumerDetails.setAuthorities(authorities);
			baseConsumerDetails.setConsumerName(consumerName);
			baseConsumerDetails.setConsumerKey(consumerKey);
			
			SharedConsumerSecret signatureSecret = new SharedConsumerSecretImpl(consumerSecret);	
			
			baseConsumerDetails.setSignatureSecret(signatureSecret);
			baseConsumerDetails.setRequiredToObtainAuthenticatedToken(false);
			
	        return new OAuthConsumerDetails(
	                consumerName,
	                consumerKey,
	                consumerSecret,
	                authorities );
	}

	@Override
	public User findByLogin(String login) {
		return userDao.findUserByUuidOrUsernameOrOpenId(login);
	}

	@Override
	public User loadUserByOpenId(String openId) {
		return userDao.findUserByOpenId(openId);
	}

	@Autowired
	AccountService accountService;
	
	@Override
	public void assign(User user, String accountIdentifier) {
		
		Account account = accountService.findByAccountIdentifier(accountIdentifier);
		List<User> users = account.getUsers();
		
		User userTmp = userDao.findUserByOpenId(user.getOpenId());
		if(userTmp != null){
			user.setId(userTmp.getId());
		}
		user.setRoleUser(true);
		userDao.saveAndFlush(user);
		
		users.add(user);
		
		account.setUsers(users);
		
		accountService.saveOrUpdate(account);
	}

	@Override
	public void unassign(User user, String accountIdentifier) {
		Account account = accountService.findByAccountIdentifier(accountIdentifier);
		
		List<User> users = account.getUsers();
		User userTmp = userDao.findUserByOpenId(user.getOpenId());
		users.remove(userTmp);
		
		account.setUsers(users);
		
		accountService.saveOrUpdate(account);
	}

	@Override
	public List<User> findUserByAccountId(Long accountId) {
		return userDao.findUserByAccountsId(accountId);
	}

	
	@Override
	public User getSelf()
	{
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		if (authentication == null)
			return null;

		return (User) authentication.getPrincipal();
	}
		
}
