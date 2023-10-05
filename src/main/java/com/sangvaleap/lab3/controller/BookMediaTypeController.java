package com.sangvaleap.lab3.controller;

import com.sangvaleap.lab3.domain.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping(value = {"/books"}, produces = "application/cs.miu.edu-v2+json")
public class BookMediaTypeController {

    List<Book> bookList = new ArrayList<Book>(Arrays.asList(
            new Book(1, "Book A", "1111-1111", 100),
            new Book(2, "Book B", "2222-2222", 200),
            new Book(3, "Book C", "3333-3333", 300),
            new Book(4, "Book D", "4444-4444", 400)
    ));

    @GetMapping
    public List<Book> getBooks() {
        return bookList;
    }

    @GetMapping(value = {"/{id}"})
    public Book getBookById(@PathVariable int id) throws Exception {
        Optional<Book> book = bookList
                .stream()
                .filter(b -> b.getId() == id)
                .findFirst();
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new Exception("Not Found");
        }
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addBook(@RequestBody Book book) {
        bookList.add(book);
    }

    @PutMapping(value = {"/{id}"})
    public Book updateBook(@PathVariable int id, @RequestBody Book book) throws Exception {

        OptionalInt index = getBookIndex(id);
        if (index.isPresent()) {
            bookList.set(index.getAsInt(), book);
            return bookList.get(index.getAsInt());
        } else {
            throw new Exception("Not found");
        }
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int id) {
        bookList = bookList.stream().filter(b -> b.getId() != id).collect(Collectors.toList());
    }

    private OptionalInt getBookIndex(int id) {
        return IntStream.range(0, bookList.size())
                .filter(i -> bookList.get(i).getId() == id)
                .findFirst();
    }
}
