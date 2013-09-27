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
@Table(name = "origin")
public class Origin extends Model implements Serializable{

	private static final long serialVersionUID = 7458404085617554606L;

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
	
	
	@Column(name = "name")
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public static Model.Finder<Long,Origin> find = new Model.Finder(Long.class, Origin.class);
	public static Map<String,String> options() {
	    LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
	   
	  /*  options.put("organisation1", "organisation1");
	    options.put("organisation2", "organisation2");*/
	    
	    
	    List<Origin> findList = Origin.find.orderBy("name").findList();
	    System.out.println("fid list======"+findList);
	    if(findList.size()<=0){
	   	 
	   	 return options;
	    }
		for(Origin c: findList) {
	        options.put(c.id.toString(), c.name);
	    }
	    return options;
	}

}
