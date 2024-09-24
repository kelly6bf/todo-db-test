package site.kelly.kellytest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import site.kelly.kellytest.service.TodoService;

@RestController
public class TestController {

    private final TodoService todoService;

    public TestController(final TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/api/health-check")
    public String healthCheck() {
        return "제발 집에 가자!!!!!";
    }

    @GetMapping("/api/todos/{id}")
    public Todo index(@PathVariable("id") Long id) {

        return todoService.getTodo(id);
    }

    @GetMapping("/api/todos")
    public List<Todo> index() {

        return todoService.getTodos();
    }

    @PostMapping("/api/todos")
    public Todo createTodo(@RequestBody CreateTodoRequest request) {
        return todoService.createTodo(request.title(), request.description());
    }

    @PutMapping("/api/todos/{id}")
    public Todo updateTodo(@PathVariable("id") Long id, @RequestBody UpdateTodoRequest request) {
        return todoService.updateTodo(id, request.title(), request.description());
    }

    @DeleteMapping("/api/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
