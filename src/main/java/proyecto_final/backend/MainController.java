package proyecto_final.backend;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // Esta clase es un controlador
@RequestMapping(path="/v1") // Las url's empezaran por "/v1/"
public class MainController {
	  @Autowired
	  private TaskRepository taskRepository;

  @PostMapping(path="/task") // Peticiones POST
  public @ResponseBody String addNewTask(@RequestParam String taskTitle, @RequestParam String taskDescription, @RequestParam String deadLine, @RequestParam String list,
		  @RequestParam String priority, @RequestParam String status) {
	
	Optional<Task> optional = taskRepository.findByTaskTitle(taskTitle);
	if (optional.isPresent()) {
		return "Task with that name already exists";
	}
	  
	Task n = new Task();
    n.setTaskTitle(taskTitle);
    n.setTaskDescription(taskDescription);
    n.setDeadLine(LocalDate.parse(deadLine));
    n.setList(list);
    TaskPriority taskPriority = TaskPriority.fromString(priority);
    if (taskPriority == null) {
    	return "Invalid Priority";
    }
    n.setPriority(taskPriority);
    TaskStatus taskStatus = TaskStatus.fromString(status);
    if (taskStatus == null) {
    	return "Invalid Status";
    }
    n.setStatus(taskStatus);
    taskRepository.save(n);
    return "Task Saved";
    
  }
  
  @GetMapping(path="/task")
  public @ResponseBody Iterable<Task> getTasks(@RequestParam(required=false) String list){
	  if (list == null) {
		  return taskRepository.findAll();
	  }
	  return taskRepository.findByList(list);
  }

  @DeleteMapping(path="/task") // Peticiones DEL
  public @ResponseBody String removeTask(@RequestParam String taskTitle) {
	  
	  Optional<Task> optional = taskRepository.findByTaskTitle(taskTitle);
	  if (optional.isPresent()) {
		  taskRepository.deleteById(optional.get().getId());
		  return "Task deleted";
	  }
	  return "Task does not exist";
    
  }
  
  @PutMapping(path="/task") // Peticiones POST
  public @ResponseBody String editTask(@RequestParam String taskTitle, @RequestParam(required=false) String newTaskTitle, @RequestParam(required=false) String taskDescription, 
		  @RequestParam(required=false) String deadLine, @RequestParam(required=false) String list, @RequestParam(required=false) String priority, 
		  @RequestParam(required=false) String status) {
	
	Optional<Task> optional = taskRepository.findByTaskTitle(taskTitle);
	if (!optional.isPresent()) {
		return "Task with that name does not exist";
	}
	  
	Task n = optional.get();
	if (newTaskTitle != null) {
		n.setTaskTitle(newTaskTitle);
    }
	if (taskDescription != null) {
		n.setTaskDescription(taskDescription);
	}
	if (deadLine != null) {
		n.setDeadLine(LocalDate.parse(deadLine));
	}
	if (list != null) {
		n.setList(list);
	}
	if (priority != null) {
	    TaskPriority taskPriority = TaskPriority.fromString(priority);
	    if (taskPriority == null) {
	    	return "Invalid Priority";
	    }
	    n.setPriority(taskPriority);
	}
	if (status != null) {
	    TaskStatus taskStatus = TaskStatus.fromString(status);
	    if (taskStatus == null) {
	    	return "Invalid Status";
	    }
	    n.setStatus(taskStatus);
	}
    taskRepository.save(n);
    return "Task Modified";
    
  }
}