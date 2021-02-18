package ru.service.library.dto;

import lombok.Getter;
import lombok.Setter;
import ru.service.library.repo.entity.Book;

@Getter
@Setter
public class BookDTO {

    public BookDTO(Book book) {
        this.author = book.getAuthor();
        this.id =book.getId();
        this.name = book.getName();
        this.year = book.getYear();
    }

    private Integer id;

    private String name;

    private String author;

    private Integer year;

    public BookDTO getBookDto(Book book){
        return new BookDTO(book);
    }

}
