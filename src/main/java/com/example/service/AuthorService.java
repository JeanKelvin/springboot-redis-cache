package com.example.service;

import com.example.exception.AuthorNotFoundException;
import com.example.model.Author;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private List<Author> authors;

    @PostConstruct
    public void init() {
        authors = new ArrayList<>();
        authors.add(new Author("1", "Robert C. Martin"));
        authors.add(new Author("2", "Martin Fowler"));
        authors.add(new Author("3", "Akira Hirasawa"));

    }

    public void updateAuthor(Author author) {
        authors = authors.stream().filter(a -> !a.getId().equals(author.getId())).collect(Collectors.toList());
        authors.add(author);
    }

    public void deleteAuthor(String authorId) {
        authors = authors.stream().filter(a -> !a.getId().equals(authorId)).collect(Collectors.toList());
    }

    public Author getAuthorById(String authorId) throws AuthorNotFoundException {
        return authors.stream()
                .filter(a -> a.getId().equals(authorId))
                .findFirst()
                .orElseThrow(() ->  new AuthorNotFoundException("Cannot find post with id:" + authorId));
    }
}
