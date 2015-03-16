package com.bigeye.mycust.oauth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth.common.OAuthCodec;
import org.springframework.security.oauth.common.OAuthConsumerParameter;
import org.springframework.security.oauth.common.OAuthProviderParameter;
import org.springframework.security.oauth.provider.ConsumerAuthentication;
import org.springframework.security.oauth.provider.ExtraTrustConsumerDetails;
import org.springframework.security.oauth.provider.filter.AccessTokenProcessingFilter;
import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.oauth.provider.filter.UnauthenticatedRequestTokenProcessingFilter;
import org.springframework.security.oauth.provider.filter.UserAuthorizationProcessingFilter;
import org.springframework.security.oauth.provider.token.OAuthProviderToken;
import org.springframework.security.oauth.provider.verifier.OAuthVerifierServices;

/**
 * The 2-Legged OAuth process involves 2 or the 3 steps normally involved in the process.
 * The steps are:
 *
 * 1) Get Request Token
 * 2) Authorize Request Token
 * 3) Get Access Token
 * 4) Use Access Token to get the protected resource (not considered a step)
 *
 * The 2-Legged process uses steps 1 & 3. The OAuth filters involved in each step are:
 *
 * 1) {@link UnauthenticatedRequestTokenProcessingFilter}
 * 2) {@link UserAuthorizationProcessingFilter}
 * 3) {@link AccessTokenProcessingFilter}
 * 4) {@link ProtectedResourceProcessingFilter}
 *
 * Unfortunately we can't ask for the Request Token and then ask for an Access Token
 * as the filters above don't allow that scenario to take place. Either the protected
 * resource is accessed directly (through Filter number 4) or it must go through all
 * 3 steps of the process.
 *
 * The filter below extends the default Request Token filter (1) and Pre-authenticates the
 * Request Token if the Consumer is an {@link ExtraTrustConsumerDetails} and is not required
 * to Authenticate the Request Token. This way the Request Token can be used to call the
 * Access Token filter (3) skipping the User Authorization filter (2).
 *
 * @author Alessandro Giannone
 * @version 1.0
 */
public class PreauthenticatedRequestTokenProcessingFilter extends UnauthenticatedRequestTokenProcessingFilter
{
	private OAuthVerifierServices verifierServices;


	protected void onValidSignature( HttpServletRequest request, HttpServletResponse response, FilterChain chain )
			throws IOException
	{
		 //signature is verified; create the token, send the response.
		ConsumerAuthentication authentication = (ConsumerAuthentication) SecurityContextHolder.getContext().getAuthentication();
		OAuthProviderToken authToken = createOAuthToken(authentication);
		if (!authToken.getConsumerKey().equals(authentication.getConsumerDetails().getConsumerKey())){
			throw new IllegalStateException("The consumer key associated with the created auth token is not valid for the authenticated consumer.");
		}

		///////////////////////////////////////////////////////////////////
		// Pre-authorize the Request Token for 2-Legged OAuth.
		// This will only be done if the Consumer has ExtraTrust and is not required
		// to obtain an authenticated token (see isRequiredToObtainAuthenticatedToken())
		///////////////////////////////////////////////////////////////////

		if( (authentication.getConsumerDetails() instanceof ExtraTrustConsumerDetails) &&
			!((ExtraTrustConsumerDetails)authentication.getConsumerDetails()).isRequiredToObtainAuthenticatedToken() )
		{
		    String verifier = getVerifierServices().createVerifier();
		    getTokenServices().authorizeRequestToken( authToken.getValue(), verifier, authentication );
		}

		////////////////////////////////////////////////////////////


		String tokenValue = authToken.getValue();
		String callback = authentication.getOAuthParameters().get(OAuthConsumerParameter.oauth_callback.toString());

		StringBuilder responseValue = new StringBuilder(OAuthProviderParameter.oauth_token.toString())
		  .append('=')
		  .append(OAuthCodec.oauthEncode(tokenValue))
		  .append('&')
		  .append(OAuthProviderParameter.oauth_token_secret.toString())
		  .append('=')
		  .append(OAuthCodec.oauthEncode(authToken.getSecret()));

		if( callback != null )
		{
		  responseValue.append('&')
		  	.append(OAuthProviderParameter.oauth_callback_confirmed.toString())
		  	.append("=true");
		}

		response.setContentType(getResponseContentType());
		response.getWriter().print(responseValue.toString());
		response.flushBuffer();
	}


	/**
	 * The verifier services to use.
	 *
	 * @return The verifier services to use.
	 */
	public OAuthVerifierServices getVerifierServices()
	{
		return verifierServices;
	}


	/**
	 * The verifier services to use.
	 *
	 * @param verifierServices The verifier services to use.
	 */
	@Autowired
	public void setVerifierServices( OAuthVerifierServices verifierServices )
	{
		this.verifierServices = verifierServices;
	}
}