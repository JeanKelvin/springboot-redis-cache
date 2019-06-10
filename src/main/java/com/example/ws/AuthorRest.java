package com.example.ws;

import com.example.exception.AuthorNotFoundException;
import com.example.model.Author;
import com.example.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorRest {

    private Logger log = LoggerFactory.getLogger(AuthorNotFoundException.class);

    private final AuthorService authorService;

    @Autowired
    public AuthorRest(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Cacheable(value = "author-single", key = "#id")
    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable("id") String id) throws AuthorNotFoundException {
        return authorService.getAuthorById(id);
    }

    @CachePut(value = "author-single", key = "#auth.id")
    @PutMapping()
    public Author updatePostById(@RequestBody Author author) {
        log.info("update author with id{}", author.getId());
        authorService.updateAuthor(author);
        return author;
    }

    @CacheEvict(value = "author-single", key = "#id")
    @DeleteMapping("{id}")
    public void deleteAuthorById(@PathVariable String id) {
        log.info("delete author with id {}", id);
        authorService.deleteAuthor(id);
    }
}
