package com.aeon.library.controller;

import com.aeon.library.mapper.BookMapper;
import com.aeon.library.model.dto.request.CreateBookReq;
import com.aeon.library.model.dto.response.BookRes;
import com.aeon.library.model.dto.response.ResultList;
import com.aeon.library.model.entity.Book;
import com.aeon.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/book")
@Validated
@Slf4j
@Transactional
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @Operation(summary = "Register New Book", description = "This API use to register new book")
    @PostMapping("")
    public ResponseEntity<BookRes> create(@Valid @RequestBody CreateBookReq req) {
        return ResponseEntity.ok(bookMapper.toRes(bookService.create(req)));
    }

    @Operation(summary = "Find All Books", description = "This API use to find all books")
    @GetMapping("")
    public ResponseEntity<ResultList<BookRes>> findAll() {
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(new ResultList<>(books.size(), books.stream().map(bookMapper::toRes).toList()));
    }

    @Operation(summary = "Borrow Book", description = "This API use to borrow a book on behalf a borrower")
    @PostMapping("/{bookId}/borrow/{borrowerId}")
    public ResponseEntity<BookRes> borrow(@PathVariable long bookId, @PathVariable long borrowerId) {
        return ResponseEntity.ok(bookMapper.toRes(bookService.borrow(bookId, borrowerId)));
    }

    @Operation(summary = "Return Book", description = "This API use to return a book")
    @PostMapping("/{bookId}/return")
    public ResponseEntity<BookRes> borrow(@PathVariable long bookId) {
        return ResponseEntity.ok(bookMapper.toRes(bookService.returnBook(bookId)));
    }
}
