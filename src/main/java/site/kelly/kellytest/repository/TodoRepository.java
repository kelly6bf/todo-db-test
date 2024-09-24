package site.kelly.kellytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import site.kelly.kellytest.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
