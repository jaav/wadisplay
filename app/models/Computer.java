package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.format.Formats;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
@Table(name="computers")
public class Computer extends Model{
	
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long id;
    
    @Required
    public String name;
    
    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date introduced;
    
    @Formats.DateTime(pattern="yyyy-MM-dd")
    public Date discontinued;

    public Computer saveComputer() {
       Computer computer=new Computer();
       computer.save();
       return computer;
        
    }
}
