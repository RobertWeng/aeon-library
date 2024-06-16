package com.aeon.library.controller;

import com.aeon.library.mapper.BorrowerMapper;
import com.aeon.library.model.dto.request.CreateBorrowerReq;
import com.aeon.library.model.dto.request.FindByKeywordReq;
import com.aeon.library.model.dto.response.BorrowerRes;
import com.aeon.library.model.dto.response.ResultList;
import com.aeon.library.model.entity.Borrower;
import com.aeon.library.service.BorrowerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/borrower")
@Validated
@Slf4j
@Transactional
public class BorrowerController {

    @Autowired
    private BorrowerService borrowerService;

    @Autowired
    private BorrowerMapper borrowerMapper;

    @Operation(summary = "Register New Borrower", description = "This API use to register a new borrower")
    @PostMapping("")
    public ResponseEntity<BorrowerRes> register(@Valid @RequestBody CreateBorrowerReq req) {
        return ResponseEntity.ok(borrowerMapper.toRes(borrowerService.register(req)));
    }

    @Operation(summary = "Find All Borrower", description = "This API use to find all borrower based on keyword")
    @GetMapping("")
    public ResponseEntity<ResultList<BorrowerRes>> findAll(FindByKeywordReq req) {
        List<Borrower> borrowers = borrowerService.findAll(req);
        return ResponseEntity.ok(new ResultList<>(borrowers.size(), borrowers.stream().map(borrowerMapper::toRes).toList()));
    }


}
