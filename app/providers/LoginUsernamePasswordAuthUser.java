package providers;

import com.feth.play.module.pa.providers.password.DefaultUsernamePasswordAuthUser;

public class LoginUsernamePasswordAuthUser extends
		DefaultUsernamePasswordAuthUser {

	
	private static final long serialVersionUID = 1L;

	final static long SESSION_TIMEOUT = 24 * 14 * 3600;
	
	private long expiration;

	
	public LoginUsernamePasswordAuthUser(final String email) {
		this(null, email);
	}

	public LoginUsernamePasswordAuthUser(final String clearPassword,
			final String email) {
		super(clearPassword, email);

		expiration = System.currentTimeMillis() + 1000 * SESSION_TIMEOUT;
	}

	@Override
	public long expires() {
		return expiration;
	}

}
