package models;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotBlank;

import play.db.ebean.Model;

@Entity
@Table(name = "question_type")
public class QuestionType extends Model {

	private static final long serialVersionUID = -3331661204573671895L;

	
	
	@Column(name = "name")
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id")
	public Long id;

	@Version
	@Column(name = "last_modified")
	public Timestamp lastModified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}
	public static Model.Finder<Long,QuestionType> find = new Model.Finder(Long.class, QuestionType.class);
	public static Map<String,String> options() {
	    LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
	   
	    /*options.put("info/help", "info/help");
	    options.put("advice", "advice");
	    options.put("info and advice", "info and advice");
	    */
	    
	    List<QuestionType> findList = QuestionType.find.orderBy("name").findList();
	    System.out.println("fid list======"+findList);
	    if(findList.size()<=0){
	   	 
	   	 return options;
	    }
		for(QuestionType c: findList) {
	        options.put(c.id.toString(), c.name);
	    }
	    return options;
	}
	public QuestionType(String name,Long id){
		this.id=id;
		this.name=name;
	}
	public QuestionType(){
		
	}
}
