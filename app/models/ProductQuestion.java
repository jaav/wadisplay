package models;

import java.io.Serializable;
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
@Table(name = "product_question")
public class ProductQuestion extends Model implements Serializable{

	private static final long serialVersionUID = 2854313505055601984L;

	
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
	
	
	public String question;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public ProductQuestion saveUser(ProductQuestion productQuestion) {
		try {
			productQuestion.save();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productQuestion;
	}
	public static Model.Finder<Long,ProductQuestion> find = new Model.Finder(Long.class, ProductQuestion.class);
	public static Map<String,String> options() {
	    LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
	   
	    List<ProductQuestion> findList = ProductQuestion.find.orderBy("question").findList();
	    System.out.println("fid list======"+findList);
	    if(findList.size()<=0){
	   	 
	   	 return options;
	    }
		for(ProductQuestion c: findList) {
	        options.put(c.id.toString(), c.question);
	    }
	    return options;
	}
	
	

	
}