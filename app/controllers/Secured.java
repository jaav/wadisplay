package controllers;

import play.mvc.Result;
import play.mvc.Security;
import play.mvc.Http.Context;
import controllers.Application;

public class Secured extends Security.Authenticator{

	@Override
	public String getUsername(Context arg0) {
		
		return arg0.session().get("email");

	
	}
@Override
public Result onUnauthorized(Context arg0) {
	
	return redirect(routes.Application.login());
}
}
