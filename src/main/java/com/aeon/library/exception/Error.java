package com.aeon.library.exception;

import java.text.MessageFormat;

public class Error {

    public enum Code {
        FORBIDDEN,
        NOT_FOUND,
        INVALID_REQUEST,
        INVALID_STATE,
        INTERNAL_SERVER_ERROR
    }

    public enum Msg {
        FORBIDDEN("Forbidden"),
        NOT_FOUND("{0} `{1}` not found"),
        INTERNAL_SERVER_ERROR("Application error, please try again later"),
        EMAIL_EXISTS("This email is already exist"),
        DIFFERENT_BOOK("Book has different author or title with this ISBN number"),
        BOOK_BORROWED("Book borrowed");

        private final String body;

        Msg(String body) {
            this.body = body;
        }

        public String getBodyWithParameter(Object... args) {
            return MessageFormat.format(body, args);
        }
    }
}
