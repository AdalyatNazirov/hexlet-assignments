package exercise.controller;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.repository.TaskRepository;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskMapper taskMapper;


    @GetMapping(path = "")
    public List<TaskDTO> index() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::map)
                .toList();
    }

    @GetMapping(path = "/{id}")
    public TaskDTO show(@PathVariable long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task with id " + id + " not found"));

        return taskMapper.map(task);
    }

    @PostMapping(path = "")
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public TaskDTO create(@RequestBody TaskCreateDTO taskDto) {
        var task = taskMapper.map(taskDto);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @PutMapping(path = "/{id}")
    public TaskDTO update(@PathVariable long id, @RequestBody TaskUpdateDTO taskDto) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task with id " + id + " not found"));
        taskMapper.update(taskDto, task);
        if (taskDto.getAssigneeId() != null) {
            var user = userRepository.findById(taskDto.getAssigneeId()).orElseThrow(
                    () -> new RuntimeException("User with id " + id + " not found")
            );
            task.setAssignee(user);
        }
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task with id " + id + " not found"));
        taskRepository.delete(task);
    }
    // END
}
