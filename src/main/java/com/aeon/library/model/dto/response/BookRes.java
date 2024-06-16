package com.aeon.library.model.dto.response;

import lombok.Data;

@Data
public class BookRes extends BaseRes {
    private String ISBN;
    private String title;
    private String author;
    private boolean borrowed;
    private BorrowerRes borrower;
}
