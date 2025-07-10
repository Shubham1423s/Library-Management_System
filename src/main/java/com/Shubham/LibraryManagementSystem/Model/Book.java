package com.Shubham.LibraryManagementSystem.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.security.PrivateKey;

@Entity
@Table(name = "Books")
@NoArgsConstructor
@AllArgsConstructor

@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

     @Column
    private String title;
     @Column
    private String author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
