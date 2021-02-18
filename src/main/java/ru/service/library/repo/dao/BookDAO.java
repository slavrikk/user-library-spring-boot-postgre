package ru.service.library.repo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.service.library.repo.BookRepository;
import ru.service.library.repo.entity.Book;

import java.util.*;

@Service
public class BookDAO {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        Book savedBook;
        try {
            savedBook =  bookRepository.save(book);
        } catch (Exception e) {
            return null;
        }
        return savedBook;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> selectBookById(int id) {
        Optional<Book> selectedBook;
        Book book;
        try {
            selectedBook = bookRepository.findById(id);
            book = selectedBook.get();
        } catch (Exception e) {
            return null;
        }
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        return bookList;
    }

    public boolean deleteBookById(Integer id) {
        try {
            bookRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}
