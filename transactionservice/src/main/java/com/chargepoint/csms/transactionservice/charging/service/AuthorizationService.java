package com.chargepoint.csms.transactionservice.charging.service;

import com.chargepoint.csms.transactionservice.charging.dto.AuthorizeReqDto;
import com.chargepoint.csms.transactionservice.charging.dto.AuthorizeResDto;

public interface AuthorizationService {

    AuthorizeResDto authorizeDriver(AuthorizeReqDto dto);
}
