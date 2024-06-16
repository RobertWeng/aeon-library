package com.aeon.library.exception;

import com.aeon.library.exception.Error.Code;
import com.aeon.library.exception.Error.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;

@ControllerAdvice
@Slf4j
public class GlobalAdvice {

    @ExceptionHandler(Catch.class)
    public ResponseEntity<Catch> handleCatch(Catch e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Catch> handleException(Exception e) {
        log.error("Error: {}", getStackTrace(e));
        return handleCatch(new Catch(HttpStatus.INTERNAL_SERVER_ERROR, Code.INTERNAL_SERVER_ERROR, Msg.INTERNAL_SERVER_ERROR));
    }
}
