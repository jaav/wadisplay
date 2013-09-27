package models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotBlank;

import play.db.ebean.Model;

@Entity
@Table(name = "conversation_type")
public class ConversationType extends Model implements Serializable {

	private static final long serialVersionUID = -1001103173437286136L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id")
	public Long id;
	
	
	
	@Column(name = "name")
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

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
	public  ConversationType  saveUser(){
		ConversationType type=new ConversationType();
		type.save();
		return type;
	}
	public static Model.Finder<Long,ConversationType> find = new Model.Finder(Long.class, ConversationType.class);
	public static Map<String,String> options() {
	    LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
	   
	   /* options.put("fake call", "fake call");
	    options.put("sex caller", "sex caller");
	    options.put("silence", "silence");
	    options.put("wrong number", "wrong number");
	    options.put("e-mail", "e-mail");*/
	    
	    List<ConversationType> findList = ConversationType.find.orderBy("name").findList();
	    System.out.println("fid list======"+findList);
	    if(findList.size()<=0){
	   	 
	   	 return options;
	    }
		for(ConversationType c: findList) {
	        options.put(c.id.toString(), c.name);
	    }
	    return options;
	}
	
	public ConversationType(String name,Long id){
		this.id=id;
		this.name=name;
	}
	public ConversationType(){
	}
}
