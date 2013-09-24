package models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;



import org.hibernate.validator.constraints.NotBlank;

import com.avaje.ebean.Ebean;

import play.data.format.Formats;

import play.db.ebean.Model;


@SuppressWarnings("serial")
@Entity
@Table(name = "registrations")
public class Registration extends Model implements Serializable {

	
	@Id
	@Column(name = "id")
	public Long id;

	@Column(name="date")
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	public Date date; 
	
	@Column(name="timer")
	public String timer;

	@NotNull
	@Column(name = "duration")
	public Integer duration;

	
	@ManyToOne
	@JoinColumn(name = "conversation_type")
	public ConversationType conversationType;

	@NotBlank
	@Column(name = "question", length = 255)
	public String question;

	@ManyToOne
	@JoinColumn(name = "regUser")
	public User regUser;

	@NotNull
	@Column(name = "gender", length = 1)
	public String gender;

	@NotNull
	@Column(name = "age")
	public Integer age;

	@Column(name = "zipcode")
	public String zipCode;

	@Column(name = "location")
	public String location;

	
	@ManyToOne
	@JoinColumn(name = "presentation")
	public Presentation presentation;

	@ManyToOne
	@JoinColumn(name = "attitude")
	public Attitude attitude;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "product_map", joinColumns = @JoinColumn(name = "registration", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "product", referencedColumnName = "id"))
	public List<Product> products=new ArrayList<Product>();

	// @NotNull
	// @NotEmpty
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "product_question_map", joinColumns = @JoinColumn(name = "registration", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "product_question", referencedColumnName = "id"))
	public List<ProductQuestion> productQuestions=new ArrayList<ProductQuestion>();

	@ManyToOne
	@JoinColumn(name = "question_type")
	public QuestionType questionType;

	// @NotNull
	// @NotEmpty
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "relation_map", joinColumns = @JoinColumn(name = "registration", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "relation", referencedColumnName = "id"))
	public List<Relation> relations = new ArrayList<Relation>();

	// @NotNull
	// @NotEmpty
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "social_context_map", joinColumns = @JoinColumn(name = "registration", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "social_context", referencedColumnName = "id"))
	public List<SocialContext> socialContexts=new ArrayList<SocialContext>();

	// @NotNull
	// @NotEmpty
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "referrer_map", joinColumns = @JoinColumn(name = "registration", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "referrer", referencedColumnName = "id"))
	public List<Refer> refers=new ArrayList<Refer>();

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "task_map", joinColumns = @JoinColumn(name = "registration", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "task", referencedColumnName = "id"))
	public Set<Task> tasks;

	@Column(name = "interrupted")
	
	public Boolean interrupted;

	@Column(name = "has_violence")
	
	public Boolean hasViolence;

	
	@Column(name = "tp_gender", length = 1)
	public String tpGender;

	
	@Column(name = "tp_age")
	public Integer tpAge;

	
	@ManyToOne
	@JoinColumn(name = "tp_usage_type")
	public UsageType tpUsageType;

	@ManyToOne
	@JoinColumn(name = "origin")
	public Origin origin;

	
	
	
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public String getTimer() {
		return timer;
	}

	public void setTimer(String timer) {
		this.timer = timer;
	}

	

	public Integer getDuration() {
		
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public ConversationType getConversationType() {
		return conversationType;
	}

	public void setConversationType(ConversationType conversationType) {
		this.conversationType = conversationType;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public User getRegUser() {
		return regUser;
	}

	public void setRegUser(User regUser) {
		this.regUser = regUser;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Presentation getPresentation() {
		return presentation;
	}

	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}

	public Attitude getAttitude() {
		return attitude;
	}

	public void setAttitude(Attitude attitude) {
		this.attitude = attitude;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<ProductQuestion> getProductQuestions() {
		return productQuestions;
	}

	public void setProductQuestions(List<ProductQuestion> productQuestions) {
		this.productQuestions = productQuestions;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	public List<SocialContext> getSocialContexts() {
		return socialContexts;
	}

	public void setSocialContexts(List<SocialContext> socialContexts) {
		this.socialContexts = socialContexts;
	}

	public List<Refer> getReferers() {
		return refers;
	}

	public void setReferers(List<Refer> refer) {
		this.refers = refer;
	}

	public Boolean getInterrupted() {
		return interrupted;
	}

	public void setInterrupted(Boolean interrupted) {
		this.interrupted = interrupted;
	}

	public Boolean getHasViolence() {
		return hasViolence;
	}

	public void setHasViolence(Boolean hasViolence) {
		this.hasViolence = hasViolence;
	}

	public String getTpGender() {
		return tpGender;
	}

	public void setTpGender(String tpGender) {
		this.tpGender = tpGender;
	}

	public Integer getTpAge() {
		return tpAge;
	}

	public void setTpAge(Integer tpAge) {
		this.tpAge = tpAge;
	}

	public UsageType getTpUsageType() {
		return tpUsageType;
	}

	public void setTpUsageType(UsageType tpUsageType) {
		this.tpUsageType = tpUsageType;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	
	
	public Registration(){
		
	}

	/*@Transient
	public TimeValue getTime() {
		return TimeValue.of(epoch);
	}*/

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Registration(Long id,Date date,String timer,String question){
		this.id=id;
		this.date=date;
		this.timer=timer;
		this.question=question;
		
	}
	
	
	
	
	public Registration saveUser(Registration registration) {
		
		
		try {
			registration.save();
			registration.saveManyToManyAssociations("products");
			registration.saveManyToManyAssociations("refers");
			registration.saveManyToManyAssociations("productQuestions");
			registration.saveManyToManyAssociations("relations");
			registration.saveManyToManyAssociations("socialContexts");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return registration;
	}
	public static Model.Finder<Long,Registration> find = new Model.Finder(Long.class, Registration.class);
	
	public static List<Registration> listRegistrations() {
		List<Registration> findList = find.findList();
		for (Registration registration : findList) {
			System.out.println("registration values in Registration==="+registration.getQuestion());
			System.out.println("registration values in Registration==="+registration.getDate());
			System.out.println("registration values in Registration==="+registration.getTimer());
			System.out.println("registration values in Registration==="+registration.getId());
		}
		System.out.println("find liat values====="+findList);
        return findList;
    }
	
	
public static List<Registration> listAll(){
	Registration registration=new Registration();
	Registration registration1=new Registration();
	Registration registration2=new Registration();
	Registration registration3=new Registration();
	String string="12/12/2012";
	registration.setDuration(12);
	registration.setId(1222L);
	registration.setTimer("10:12");
	registration.setQuestion("what is agriculture");
	registration1.setDuration(12);
	registration1.setId(1222L);
	registration1.setTimer("12:12");
	registration1.setQuestion("what is humanity");
	registration2.setDuration(12);
	registration2.setId(1222L);
	registration2.setTimer("12:12");
	registration2.setQuestion("what is humanity sdkjfhslkdfgjksdfgjksdfjhsjjjjjjjfhghghghghggjfghkdfjghkfjghdkfjghdkfjghdkfjghdkfjghdkfgjhdkfgjhdfgkjh");
	registration3.setDuration(12);
	registration3.setId(1222L);
	registration3.setTimer("12:12");
	registration3.setQuestion("what is humanity");
	
	List<Registration> list=new ArrayList<Registration>();
	list.add(registration);
	list.add(registration1);
	list.add(registration2);
	list.add(registration3);
	System.out.println("list of registrations =========="+list);
	return list;
}



	

}
