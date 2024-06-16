package com.aeon.library.exception;

import com.aeon.library.exception.Error.Code;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonIgnoreProperties({"cause", "stackTrace", "localizedMessage", "suppressed"})
public class Catch extends RuntimeException {

    private Error.Code code;

    @JsonIgnore
    private HttpStatus httpStatus;

    public Catch(HttpStatus httpStatus, Error.Code code, Error.Msg msg, Object... args) {
        super(msg.getBodyWithParameter(args));
        this.httpStatus = httpStatus;
        this.code = code;
    }

    public static Catch entityNotFound(Class entity, Object id) {
        return new Catch(HttpStatus.NOT_FOUND, Error.Code.NOT_FOUND, Error.Msg.NOT_FOUND, entity.getSimpleName(), id.toString());
    }

    public static Catch invalidRequest(Error.Msg msg, Object... args) {
        return new Catch(HttpStatus.BAD_REQUEST, Code.INVALID_REQUEST, msg, args);
    }

}
