package com.aeon.library.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateBookReq {
    @NotBlank
    private String ISBN;

    @NotBlank
    private String title;

    @NotBlank
    private String author;
}
