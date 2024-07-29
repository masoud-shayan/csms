package com.chargepoint.csms.transactionservice.charging.controller;

import com.chargepoint.csms.transactionservice.charging.dto.AuthorizeReqDto;
import com.chargepoint.csms.transactionservice.charging.dto.AuthorizeResDto;
import com.chargepoint.csms.transactionservice.charging.service.AuthorizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final AuthorizationService authorizationService;


    @PostMapping("/authorize")
    public AuthorizeResDto authorization(@RequestBody @Valid AuthorizeReqDto dto) {
        return authorizationService.authorizeDriver(dto);
    }

}
