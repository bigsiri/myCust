package com.bigeye.mycust.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bigeye.mycust.SpringMVCConstants;

public final class CommonValidationUtils {

	private static final Log LOG = LogFactory.getLog(CommonValidationUtils.class);

	private CommonValidationUtils() {
	}

	/**
	 * This method is used to validate phone number
	 * 
	 * @param phone
	 *            The phone number
	 * 
	 * @return True if the phone number is valid, false otherwise
	 */
	public static boolean validatePhoneNumber(String phone) {
		final String phoneNumberPattern = "(\\d-)?(\\d{3}-)?\\d{3}-\\d{4}";
		return phone.matches(phoneNumberPattern);
	}

	public static boolean validatePostalCode(String postalCode, String countryCode) {
		boolean valid = false;
		Pattern pattern = null;
		Matcher matcher = null;
		if (countryCode.equals(SpringMVCConstants.UNITED_STATES)) {
			pattern = Pattern.compile("[0-9]{5}(-[0-9]{4})?");
			matcher = pattern.matcher(postalCode);
		} else if (countryCode.equals(SpringMVCConstants.CANADA)) {
			pattern = Pattern.compile("[ABCEGHJKLMNPRSTVXY]\\d[A-Z]\\d[A-Z]\\d");
			matcher = pattern.matcher(postalCode);
		}

		if (matcher != null) {
			valid = matcher.matches();
		}

		return valid;
	}

	public static boolean validateEmail(String email) {
		boolean valid = false;

		final Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
		final Matcher matcher = pattern.matcher(StringUtils.trimToEmpty(email));
		valid = matcher.matches();

		if (LOG.isDebugEnabled()) {
			LOG.debug("Email <" + email + "> Pattern <.+@.+\\.[a-z]+> == " + valid);
		}
		return valid;
	}

}
