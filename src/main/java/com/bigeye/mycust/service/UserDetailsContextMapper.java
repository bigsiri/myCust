package com.bigeye.mycust.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.InetOrgPersonContextMapper;
import org.springframework.stereotype.Service;

import com.bigeye.mycust.RoleConstants;

@Service("userDetailsContextMapper")
public final class UserDetailsContextMapper extends InetOrgPersonContextMapper {

	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {

		final List<GrantedAuthority> newAuthorities = new ArrayList<>();
		newAuthorities.addAll(authorities);

		final GrantedAuthority ga = new SimpleGrantedAuthority(RoleConstants.ADMIN);
		newAuthorities.add(ga);


		final UserDetails userDetails = super.mapUserFromContext(ctx, username, newAuthorities);

		return userDetails;
	}
}
