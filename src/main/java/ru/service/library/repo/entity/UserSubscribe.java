package ru.service.library.repo.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="subscribe")
public class UserSubscribe {

    public UserSubscribe() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String type;

    @ManyToMany(mappedBy = "subscribes")
    private Set<Book> books = new HashSet<>();

    @OneToOne
    private UserReader userReader;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSubscribe that = (UserSubscribe) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public UserReader getUserReader() {
        return userReader;
    }

    public void setUserReader(UserReader userReader) {
        this.userReader = userReader;
    }
}
