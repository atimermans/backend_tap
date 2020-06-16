package proyecto_final.backend;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TaskRepository extends CrudRepository<Task, Integer> {

	List<Task> findByList(String list);
	Optional<Task> findByTaskTitle(String taskTitle);
}