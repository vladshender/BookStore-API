package com.example.controller;

import com.example.dto.BookDto;
import com.example.dto.BookSearchParameters;
import com.example.dto.CreateBookRequestDto;
import com.example.dto.UpdateBookDto;
import com.example.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public BookDto createBook(@RequestBody CreateBookRequestDto bookDto) {
        return bookService.save(bookDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.deletedById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable Long id, @RequestBody UpdateBookDto updateBookDto) {
        bookService.updateBookDetails(id, updateBookDto.getAuthor(), updateBookDto.getTitle(),
                updateBookDto.getDescription());
    }

    @GetMapping("/search")
    public List<BookDto> search(BookSearchParameters bookSearchParameters) {
        return bookService.search(bookSearchParameters);
    }
}
