package models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotBlank;

import play.db.ebean.Model;




@Entity
@Table(name = "task")
public class Task extends Model {

	private static final long serialVersionUID = -8132594827462746290L;

	
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
	
	@ManyToOne
	@JoinColumn(name = "creator")
	public User creator;

	@ManyToOne
	@JoinColumn(name = "previous_assignee")
	public User previousAssignee;

	@ManyToOne
	@JoinColumn(name = "assignee")
	public User assignee;

	@Column(name = "description")
	public String description;

	@Column(name = "creation_date")
	public Date creationDate;

	@Column(name = "due_date")
	public Date dueDate;

	@Column(name = "importance")
	public Integer importance;

	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	public TaskStatuses status;

	@Column(name = "task_type")
	@Enumerated(EnumType.ORDINAL)
	public MessageTypes taskType;

	@ManyToOne
	@JoinColumn(name = "registration")
	public RegistrationForm registration;

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getPreviousAssignee() {
		return previousAssignee;
	}

	public void setPreviousAssignee(User previousAssignee) {
		this.previousAssignee = previousAssignee;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getImportance() {
		return importance;
	}

	public void setImportance(Integer importance) {
		this.importance = importance;
	}

	public TaskStatuses getStatus() {
		return status;
	}

	public void setStatus(TaskStatuses status) {
		this.status = status;
	}

	public MessageTypes getTaskType() {
		return taskType;
	}

	public void setTaskType(MessageTypes taskType) {
		this.taskType = taskType;
	}

	public RegistrationForm getRegistration() {
		return registration;
	}

	public void setRegistration(RegistrationForm registration) {
		this.registration = registration;
	}

}
