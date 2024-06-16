package com.aeon.library.service;

import com.aeon.library.exception.Catch;
import com.aeon.library.exception.Error;
import com.aeon.library.model.dto.request.CreateBookReq;
import com.aeon.library.model.entity.Book;
import com.aeon.library.model.entity.Borrower;
import com.aeon.library.repo.BookRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BorrowerService borrowerService;

    public Book findById(long id) {
        return bookRepo.findById(id).orElseThrow(() -> Catch.entityNotFound(Book.class, id));
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Book create(CreateBookReq req) {
        bookRepo.findFirstByISBN(req.getISBN()).ifPresent(book -> {
            if (!book.getTitle().equals(req.getTitle()) || !book.getAuthor().equals(req.getAuthor()))
                throw Catch.invalidRequest(Error.Msg.DIFFERENT_BOOK);
        });

        Book book = new Book();
        book.setAuthor(req.getAuthor());
        book.setTitle(req.getTitle());
        book.setISBN(req.getISBN());
        return bookRepo.save(book);
    }

    public Book borrow(long bookId, long borrowerId) {
        Book book = findById(bookId);
        Borrower borrower = borrowerService.findById(borrowerId);
        if (book.isBorrowed())
            throw Catch.invalidRequest(Error.Msg.BOOK_BORROWED);

        book.setBorrowed(true);
        book.setBorrower(borrower);
        return bookRepo.save(book);
    }

    public Book returnBook(long bookId) {
        Book book = findById(bookId);
        book.setBorrowed(false);
        book.setBorrower(null);
        return bookRepo.save(book);
    }
}
