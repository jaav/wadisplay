package security;

import models.User;
import play.mvc.Http;
import play.mvc.Result;
import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import be.objectify.deadbolt.core.models.Subject;

import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.user.AuthUserIdentity;

public class DeadboltHandler extends AbstractDeadboltHandler {

	@Override
	public Result beforeAuthCheck(final Http.Context context) {
		if (PlayAuthenticate.isLoggedIn(context.session())) {
			
			return null;
		} else {
			final String originalUrl = PlayAuthenticate.storeOriginalUrl(context);

			context.flash().put("error","You need to log in first, to view '" + originalUrl + "'");
			return redirect(PlayAuthenticate.getResolver().login());
		}
	}

	@Override
	public Subject getSubject(final Http.Context context) {
		final AuthUserIdentity u = PlayAuthenticate.getUser(context);
		
		return User.findByAuthUserIdentity(u);
	}

	@Override
	public DynamicResourceHandler getDynamicResourceHandler(
			final Http.Context context) {
		return null;
	}

	@Override
	public Result onAuthFailure(final Http.Context context,
			final String content) {
		
		return forbidden("Forbidden");
	}
}
