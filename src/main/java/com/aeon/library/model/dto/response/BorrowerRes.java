package com.aeon.library.model.dto.response;

import lombok.Data;

@Data
public class BorrowerRes extends BaseRes {
    private String email;
    private String name;
}
