package providers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.LinkedAccount;
import models.User;
import play.Application;
import play.data.Form;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.i18n.Messages;
import play.mvc.Call;
import play.mvc.Http.Context;

import com.feth.play.module.mail.Mailer.Mail.Body;
import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.providers.password.UsernamePasswordAuthProvider;
import com.feth.play.module.pa.providers.password.UsernamePasswordAuthUser;

public class LoginNamePasswordAuthProvider
		extends
		UsernamePasswordAuthProvider<String, LoginUsernamePasswordAuthUser, LoginPasswordAuthUser, LoginNamePasswordAuthProvider.Login, LoginNamePasswordAuthProvider.Signup> {

	private static final String SETTING_KEY_VERIFICATION_LINK_SECURE = SETTING_KEY_MAIL
			+ "." + "verificationLink.secure";
	private static final String SETTING_KEY_PASSWORD_RESET_LINK_SECURE = SETTING_KEY_MAIL
			+ "." + "passwordResetLink.secure";
	private static final String SETTING_KEY_LINK_LOGIN_AFTER_PASSWORD_RESET = "loginAfterPasswordReset";

	private static final String EMAIL_TEMPLATE_FALLBACK_LANGUAGE = "en";

	@Override
	protected List<String> neededSettingKeys() {final List<String> needed = new ArrayList<String>(super.neededSettingKeys());
		/*needed.add(SETTING_KEY_VERIFICATION_LINK_SECURE);
		needed.add(SETTING_KEY_PASSWORD_RESET_LINK_SECURE);
		needed.add(SETTING_KEY_LINK_LOGIN_AFTER_PASSWORD_RESET);*/
		return null;
	}

	public static LoginNamePasswordAuthProvider getProvider() {
		return (LoginNamePasswordAuthProvider) PlayAuthenticate.getProvider(UsernamePasswordAuthProvider.PROVIDER_KEY);
	}

	public static class MyIdentity {

		public MyIdentity() {
		}

		public MyIdentity(final String email) {
			this.email = email;
		}

		@Required
		@Email
		public String email;

	}

	public static class Login extends MyIdentity implements	com.feth.play.module.pa.providers.password.UsernamePasswordAuthProvider.UsernamePassword {

		@Required
		@MinLength(5)
		public String password;

		@Override
		public String getEmail() {
			return email;
		}

		@Override
		public String getPassword() {
			return password;
		}
	}

	public static class Signup extends Login {
		

		/*@Required
		@MinLength(5)
		public String repeatPassword;

		@Required
		public String name;

		public String validate() {
			if (password == null || !password.equals(repeatPassword)) {
				return Messages.get("playauthenticate.password.signup.error.passwords_not_same");
			}
			
		}*/
		
	}

	public static final Form<Signup> SIGNUP_FORM = form(Signup.class);
	public static final Form<Login> LOGIN_FORM = form(Login.class);

	public LoginNamePasswordAuthProvider(Application app) {
		super(app);
	}

	protected Form<Signup> getSignupForm() {
		return SIGNUP_FORM;
	}

	protected Form<Login> getLoginForm() {
		return LOGIN_FORM;
	}

	@Override
	protected com.feth.play.module.pa.providers.password.UsernamePasswordAuthProvider.SignupResult signupUser(final LoginPasswordAuthUser user) {
		final User u = User.findByUsernamePasswordIdentity(user);
		if (u != null) {
			if (u.emailValidated) {
				// This user exists, has its email validated and is active
				return SignupResult.USER_EXISTS;
			} else {
				// this user exists, is active but has not yet validated its
				// email
				return SignupResult.USER_EXISTS_UNVERIFIED;
			}
		}
		// The user either does not exist or is inactive - create a new one
		@SuppressWarnings("unused")
		final User newUser = User.create(user);
		// Usually the email should be verified before allowing login, however
		// if you return
		// return SignupResult.USER_CREATED;
		// then the user gets logged in directly
		return SignupResult.USER_CREATED_UNVERIFIED;
	}

	@Override
	protected com.feth.play.module.pa.providers.password.UsernamePasswordAuthProvider.LoginResult loginUser(
			final LoginUsernamePasswordAuthUser authUser) {
		final User u = User.findByUsernamePasswordIdentity(authUser);
		if (u == null) {
			return LoginResult.NOT_FOUND;
		} else {
			if (!u.emailValidated) {
				return LoginResult.USER_UNVERIFIED;
			} else {
				for (final LinkedAccount acc : u.linkedAccounts) {
					if (getKey().equals(acc.providerKey)) {
						if (authUser.checkPassword(acc.providerUserId,
								authUser.getPassword())) {
							// Password was correct
							return LoginResult.USER_LOGGED_IN;
						} else {
							// if you don't return here,
							// you would allow the user to have
							// multiple passwords defined
							// usually we don't want this
							return LoginResult.WRONG_PASSWORD;
						}
					}
				}
				return LoginResult.WRONG_PASSWORD;
			}
		}
	}

	@Override
	protected Call userExists(final UsernamePasswordAuthUser authUser) {
		return null;
	}

	@Override
	protected Call userUnverified(final UsernamePasswordAuthUser authUser) {
		return null;
	}

	@Override
	protected LoginPasswordAuthUser buildSignupAuthUser(
			final Signup signup, final Context ctx) {
		return null;
	}

	@Override
	protected LoginUsernamePasswordAuthUser buildLoginAuthUser(
			final Login login, final Context ctx) {
		return new LoginUsernamePasswordAuthUser(login.getPassword(),login.getEmail());
	}
	

	@Override
	protected LoginUsernamePasswordAuthUser transformAuthUser(final LoginPasswordAuthUser authUser, final Context context) {
		return new LoginUsernamePasswordAuthUser(authUser.getEmail());
	}

	@Override
	protected String getVerifyEmailMailingSubject(
			final LoginPasswordAuthUser user, final Context ctx) {
		return null;
	}

	@Override
	protected String onLoginUserNotFound(final Context context) {
		context.flash().put(controllers.Application.FLASH_ERROR_KEY,Messages.get("playauthenticate.password.login.unknown_user_or_pw"));
		return super.onLoginUserNotFound(context);
	}

	@Override
	protected Body getVerifyEmailMailingBody(final String token,
			final LoginPasswordAuthUser user, final Context ctx) {

	
		return null;
	}

	

	@Override
	protected String generateVerificationRecord(
			final LoginPasswordAuthUser user) {
		return null;
	}

	
}
