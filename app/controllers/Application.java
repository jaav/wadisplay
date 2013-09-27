package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.Computer;
import models.ConversationType;
import models.Origin;
import models.Presentation;
import models.Product;
import models.ProductQuestion;
import models.QuestionType;
import models.Refer;
import models.RegistrationForm;
import models.Relation;
import models.SocialContext;
import models.UsageType;
import models.User;
import play.data.Form;


import play.mvc.Controller;
import play.mvc.Http.Session;
import play.mvc.Result;
import providers.MyUsernamePasswordAuthProvider;
import providers.MyUsernamePasswordAuthProvider.MyLogin;
import views.html.index;
import views.html.login;
import views.html.dashboard;

import views.html.signup;
import views.html.editsignup;
import views.html.overviewusers;
import views.html.sendmessage;
import play.Routes;
import views.html.helper.form;

import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.providers.password.UsernamePasswordAuthProvider;
import com.feth.play.module.pa.user.AuthUser;


public class Application extends Controller {

	public static final Form<RegistrationForm> REGISTRATION_FORM = form(RegistrationForm.class);
	public static final String FLASH_MESSAGE_KEY = "message";
	public static final String FLASH_ERROR_KEY = "error";
	public static final String USER_ROLE = "user";
	
	public static Result index() {
		System.out.println("in Application index");
		return ok(dashboard.render() );
	
	}
	
	public static String getNameWithW(final Session session){
		User localUser = getLocalUser(session);
		if(localUser !=null) {
			
			String name = localUser.name;
			return name.toUpperCase().contains("W") ? "Hello SuperUser" : name;
		}
		else
		{
			return "";
		}
				
	}

	public static User getLocalUser(final Session session) {
		final AuthUser currentAuthUser = PlayAuthenticate.getUser(session);
		final User localUser = User.findByAuthUserIdentity(currentAuthUser);
		return localUser;
	}

	public static Result login() {
		return ok(login.render(MyUsernamePasswordAuthProvider.LOGIN_FORM));
	}
	
	public static Result sendMessage(){
		return ok(sendmessage.render(REGISTRATION_FORM));
	}
	
	public static Result sendUserMessage(){
		return ok(sendmessage.render(REGISTRATION_FORM));
	}
	
	@com.avaje.ebean.annotation.Transactional(readOnly=true)
	public static Result register() {
		return ok(signup.render(REGISTRATION_FORM));
	}
	
	public static Result registerUser() {
		
		RegistrationForm registration=new RegistrationForm();
		Map<String, String> newData = new HashMap<String, String>();
		
		System.out.println("inside registration =======================");
		Map<String, String[]> formUrlEncoded = Controller. request().body().asFormUrlEncoded();
		System.out.println("formUrlEncoded======="+formUrlEncoded.keySet());
		Form<RegistrationForm> regForm = form(RegistrationForm.class).bindFromRequest();
		System.out.println("regForm values====="+regForm);
		System.out.println("regForm============"+regForm.data());
		//Long id = null;
		ConversationType conversationType=new ConversationType();
		Long id=conversationType.getId();
		System.out.println("id of conversation type==="+id);
		QuestionType questionType=new QuestionType();
		Presentation presentation=new Presentation();
		UsageType usageType=new UsageType();
		Origin origin=new Origin();
		
	    List<Product> products = new ArrayList<Product>();
	    List<Relation> relations = new ArrayList<Relation>();
	    List<Refer> refers = new ArrayList<Refer>();
	    List<SocialContext> socialContexts= new ArrayList<SocialContext>();
	    List<ProductQuestion> productQuestions = new ArrayList<ProductQuestion>();

	    for (String key : formUrlEncoded.keySet()) {
	        String[] values = formUrlEncoded.get(key);
	        System.out.println("values======================"+Arrays.toString(values));
	        
	        for (String val : values) {
	  System.out.println("val values==="+val);
	            if ("products.id".equals(key)) products.add(Product.find.ref(Long.valueOf(val)));
	            if ("relations.id".equals(key)) relations.add(Relation.find.ref(Long.valueOf(val)));
	            if ("socialContexts.id".equals(key)) socialContexts.add(SocialContext.find.ref(Long.valueOf(val)));
	            if ("productQuestions.id".equals(key)) productQuestions.add(ProductQuestion.find.ref(Long.valueOf(val)));
	            if ("refers.id".equals(key)) refers.add(Refer.find.ref(Long.valueOf(val)));
	           if ("conversationType.id".equals(key)) registration.conversationType=ConversationType.find.ref(Long.valueOf(val));
	           if ("presentation.id".equals(key)) registration.presentation=Presentation.find.ref(Long.valueOf(val));
	           if ("tpUsageType.id".equals(key)) registration.tpUsageType=UsageType.find.ref(Long.valueOf(val));
	           if ("questionType.id".equals(key)) registration.questionType=QuestionType.find.ref(Long.valueOf(val));
	           if ("origin.id".equals(key)) registration.origin=Origin.find.ref(Long.valueOf(val));
	           
	            
	           
	         
	          
	        }
	    }
	 
	// Form<Registration> form = form(Registration.class).bindFromRequest();
	 
	//System.out.println("form values==="+form);
	   
	   //regForm.get();
	    boolean hasErrors = regForm.hasErrors();
	    System.out.println("has errors====="+hasErrors);
	 
	
	    
	   // registration.conversationType=ConversationType.find.ref(con)
	    registration.date=regForm.get().date;
	    registration.age=regForm.get().age;
	    registration.duration=regForm.get().duration;
	    registration.timer=regForm.get().timer;
	    registration.gender=regForm.get().gender;
	    registration.hasViolence=regForm.get().hasViolence;
	    registration.location=regForm.get().location;
	    registration.question=regForm.get().question;
	    registration.tpAge=regForm.get().tpAge;
	    registration.tpGender=regForm.get().tpGender;
	    registration.zipCode=regForm.get().zipCode;
	    
	    
	    registration.products=products;
	    registration.relations=relations;
	    registration.refers=refers;
	    registration.socialContexts=socialContexts;
 	    registration.productQuestions=productQuestions;
	 
	              /* registration.questionType=QuestionType.find.ref(Long.valueOf(questionType.id));
	              registration.presentation=Presentation.find.ref(Long.valueOf(presentation.id));
	              registration.tpUsageType=UsageType.find.ref(Long.valueOf(usageType.id));
	              registration.origin=Origin.find.ref(Long.valueOf(origin.id));*/
	System.out.println("registration alskdj======="+registration);
	try{
		registration.save();
		registration.saveManyToManyAssociations("products");
		registration.saveManyToManyAssociations("relations");
		registration.saveManyToManyAssociations("refers");
		registration.saveManyToManyAssociations("socialContexts");
		registration.saveManyToManyAssociations("productQuestions");
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	
	 System.out.println("registration values====="+registration.getGender());
	 flash("success", "Computer " + registration.getGender() + " has been created");
	 
		return ok(overviewusers.render(RegistrationForm.listRegistrations()));
	}
	
	public static Result getRegistrationById(Long id){
		
	Form<RegistrationForm> form=form(RegistrationForm.class).fill(RegistrationForm.find.ref(id));
	System.out.println("form values====="+form);
		return ok(editsignup.render(form));
	}
	public static Result overview() {
		List<RegistrationForm> listRegistrations = RegistrationForm.listRegistrations();
	for (RegistrationForm registration : listRegistrations) {
		System.out.println("registration values====="+registration.getDate());
		System.out.println("registration values====="+registration.getTimer());
		System.out.println("registration values====="+registration.getId());
		System.out.println("registration values====="+registration.getQuestion());
	}
		System.out.println("list of values ====="+listRegistrations);
		return ok(overviewusers.render(listRegistrations));
	}
	
	@play.db.ebean.Transactional
	public static Result saveProducts(){
		 Form<Product> form = form(Product.class).bindFromRequest();
		 System.out.println("form values======"+form);
		
		 if(form.hasErrors()) {
	         return badRequest(signup.render(REGISTRATION_FORM));
	     }
		 form.get().save();
		 flash("success", "Computer " + form.get().getName() + " has been created");
		return redirect(routes.Application.register());
	}
	
	@play.db.ebean.Transactional
	public static Result saveRelations(){
		 Form<Relation> form = form(Relation.class).bindFromRequest();
		 System.out.println("form values======"+form);
		
		 if(form.hasErrors()) {
	         return badRequest(signup.render(REGISTRATION_FORM));
	     }
		 form.get().save();
		 flash("success", "Computer " + form.get().getName() + " has been created");
		return redirect(routes.Application.register());
	}
	
	
	@play.db.ebean.Transactional
	public static Result saveProductQuestions(){
		 Form<ProductQuestion> form = form(ProductQuestion.class).bindFromRequest();
		 System.out.println("form values======"+form);
		
		 if(form.hasErrors()) {
	         return badRequest(signup.render(REGISTRATION_FORM));
	     }
		 form.get().save();
		 flash("success", "Computer " + form.get().getQuestion() + " has been created");
		return redirect(routes.Application.register());
	}
	
	@play.db.ebean.Transactional
	public static Result saveSocialContext(){
		 Form<SocialContext> form = form(SocialContext.class).bindFromRequest();
		 System.out.println("form values======"+form);
		
		 if(form.hasErrors()) {
	         return badRequest(signup.render(REGISTRATION_FORM));
	     }
		 form.get().save();
		 flash("success", "Computer " + form.get().getName() + " has been created");
		return redirect(routes.Application.register());
	}
	
	@play.db.ebean.Transactional
	public static Result saveReferers(){
		 Form<Refer> form = form(Refer.class).bindFromRequest();
		 System.out.println("form values======"+form);
		System.out.println("inside refers");
		 if(form.hasErrors()) {
	         return badRequest(signup.render(REGISTRATION_FORM));
	     }
		 form.get().save();
		 flash("success", "Computer " + form.get().getName() + " has been created");
		return redirect(routes.Application.register());
	}
	
	public static Result doLogin() {
		com.feth.play.module.pa.controllers.Authenticate.noCache(response());
		final Form<MyLogin> filledForm = MyUsernamePasswordAuthProvider.LOGIN_FORM
				.bindFromRequest();
		if (filledForm.hasErrors()) {
			
			return badRequest(login.render(filledForm));
		} else {
			
			return UsernamePasswordAuthProvider.handleLogin(ctx());
		}
	}
	
	
}