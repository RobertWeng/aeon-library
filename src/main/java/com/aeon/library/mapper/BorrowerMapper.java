package com.aeon.library.mapper;

import com.aeon.library.model.dto.response.BorrowerRes;
import com.aeon.library.model.entity.Borrower;
import org.springframework.stereotype.Component;

@Component
public class BorrowerMapper {

    public BorrowerRes toRes(Borrower borrower) {
        BorrowerRes res = new BorrowerRes();
        res.setId(borrower.getId());
        res.setName(borrower.getName());
        res.setEmail(borrower.getEmail());
        return res;
    }
}
