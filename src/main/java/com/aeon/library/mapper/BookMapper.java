package com.aeon.library.mapper;

import com.aeon.library.model.dto.response.BookRes;
import com.aeon.library.model.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    @Autowired
    private BorrowerMapper borrowerMapper;

    public BookRes toRes(Book book) {
        BookRes res = new BookRes();
        res.setId(book.getId());
        res.setTitle(book.getTitle());
        res.setAuthor(book.getAuthor());
        res.setISBN(book.getISBN());
        res.setBorrowed(book.isBorrowed());
        if (book.getBorrower() != null) {
            res.setBorrower(borrowerMapper.toRes(book.getBorrower()));
        }
        return res;
    }
}
