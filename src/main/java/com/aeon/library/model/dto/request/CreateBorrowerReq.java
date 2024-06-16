package com.aeon.library.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateBorrowerReq {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;
}
