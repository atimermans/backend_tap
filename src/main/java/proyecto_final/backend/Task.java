package proyecto_final.backend;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity // This tells Hibernate to make a table out of this class
public class Task {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  private String taskTitle = "";
  private String taskDescription = "";
  private LocalDate deadLine;
  private TaskStatus status;
  private TaskPriority priority;
  private String list = "";

  	public Integer getId() {
	  	return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public LocalDate getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(LocalDate deadLine) {
		this.deadLine = deadLine;
	}
	public TaskStatus getStatus() {
		return status;
	}
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	public TaskPriority getPriority() {
		return priority;
	}
	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
}