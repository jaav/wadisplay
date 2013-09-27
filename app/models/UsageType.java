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
@Table(name = "usage_type")
public class UsageType extends Model {

	private static final long serialVersionUID = 6118131101601457811L;

	
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

	
	
	public static Model.Finder<Long,UsageType> find = new Model.Finder(Long.class, UsageType.class);
	
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
	
	public static Map<String,String> options() {
	    LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
	   
	   /* options.put("premium usage type", "premium usage type");
	    options.put("usage discovered", "usage discovered");
	    options.put("experimental", "experimental");
	    options.put("regular usage", "regular usage");
	    options.put("problamatic usage", "problamatic usage");
	   */ 
	    List<UsageType> findList = UsageType.find.orderBy("name").findList();
	    System.out.println("fid list======"+findList);
	    if(findList.size()<=0){
	   	 
	   	 return options;
	    }
		for(UsageType c: findList) {
	        options.put(c.id.toString(), c.name);
	    }
	    return options;
	}
	
	
}
