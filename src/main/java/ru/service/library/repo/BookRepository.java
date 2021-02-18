package ru.service.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.service.library.repo.entity.Book;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
