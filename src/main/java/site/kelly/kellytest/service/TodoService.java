package site.kelly.kellytest.service;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import site.kelly.kellytest.Todo;
import site.kelly.kellytest.repository.TodoRepository;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(final TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo getTodo(final Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재 하지 않습니다. - " + id));
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public Todo createTodo(final String title, final String description) {
        final Todo todo = new Todo(null, title, description, LocalDateTime.now());
        return todoRepository.save(todo);
    }

    @Transactional
    public Todo updateTodo(final Long id, final String title, final String description) {
        final Todo savedTodo = todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재 하지 않습니다. - " + id));
        savedTodo.setTitle(title);
        savedTodo.setDescription(description);

        return savedTodo;
    }

    public void deleteTodo(final Long id) {
        todoRepository.deleteById(id);
    }
}
