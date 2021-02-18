package ru.service.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.service.library.dto.request.BookRq;
import ru.service.library.dto.response.BookRs;
import ru.service.library.repo.dao.BookDAO;
import ru.service.library.repo.entity.Book;
import ru.service.library.dto.Status;
import ru.service.library.services.DataTransfer;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/book")
public class BookController {

    @Autowired
    BookDAO bookDAO;

    @Autowired
    DataTransfer dataTransfer;

    @GetMapping()
    public @ResponseBody
    BookRs findAll() {
        Status status = new Status("Success");
        return new BookRs(status,
                dataTransfer.transferBooksFromDB(bookDAO.findAllBooks()));
    }

    @PostMapping()
    public @ResponseBody
    BookRs postUser(@RequestBody BookRq newBook){
        Book book = new Book();
        book.setAuthor(newBook.getAuthor());
        book.setName(newBook.getName());
        book.setYear(newBook.getYear());
       Book savedBook =  bookDAO.addBook(book);
       if(savedBook != null){
        Status status = new Status("Success");
        List<Book> books = new ArrayList<>();
        books.add(savedBook);
        return new BookRs(status,
                dataTransfer.transferBooksFromDB(books));
       }
       return new BookRs(new Status("ERROR WITH SAVE"),null);
    }

    @GetMapping("/{id}")
    public BookRs findOne(@PathVariable Integer id) {
        Status status;
        List<Book> selectedBook = bookDAO.selectBookById(id);
        if (selectedBook != null) {
            status = new Status("Success");
            return new BookRs(status,
                    dataTransfer.transferBooksFromDB(selectedBook));
        }
        status = new Status("Book Not Found");

        return new BookRs(status,null);
    }

    @DeleteMapping("/{id}")
    public Status delUsers(@PathVariable Integer id) {
        Status status;
        boolean result = bookDAO.deleteBookById(id);
        if (result) {
            return new Status( "Success");

        }
        return new Status( "Book Not Found");
    }

}
