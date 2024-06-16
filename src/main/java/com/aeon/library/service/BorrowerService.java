package com.aeon.library.service;

import com.aeon.library.exception.Catch;
import com.aeon.library.exception.Error;
import com.aeon.library.model.dto.request.CreateBorrowerReq;
import com.aeon.library.model.dto.request.FindByKeywordReq;
import com.aeon.library.model.entity.Borrower;
import com.aeon.library.repo.BorrowerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BorrowerService {

    @Autowired
    private BorrowerRepo borrowerRepo;

    public Borrower findById(long id) {
        return borrowerRepo.findById(id).orElseThrow(() -> Catch.entityNotFound(Borrower.class, id));
    }

    public List<Borrower> findAll(FindByKeywordReq req) {
        return borrowerRepo.findAll(req);
    }

    public Borrower register(CreateBorrowerReq req) {
        if (borrowerRepo.findFirstByEmail(req.getEmail()).isPresent())
            throw Catch.invalidRequest(Error.Msg.EMAIL_EXISTS);

        Borrower borrower = new Borrower();
        borrower.setName(req.getName());
        borrower.setEmail(req.getEmail());
        return borrowerRepo.save(borrower);
    }
}
