package providers;

import providers.LoginNamePasswordAuthProvider.Signup;

import com.feth.play.module.pa.providers.password.UsernamePasswordAuthUser;
import com.feth.play.module.pa.user.NameIdentity;

public class LoginPasswordAuthUser extends UsernamePasswordAuthUser
		implements NameIdentity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String name;

	

	
	public LoginPasswordAuthUser(final String password) {
		super(password, null);
		name = null;
	}

	@Override
	public String getName() {
		return name;
	}
}
