package com.chargepoint.csms.transactionservice.charging.service.impl;

import com.chargepoint.csms.common.type.message.base.AuthorizationRequestMessage;
import com.chargepoint.csms.common.type.message.base.EventMessage;
import com.chargepoint.csms.common.type.message.payload.AuthorizationRequest;
import com.chargepoint.csms.common.type.message.payload.AuthorizationStatusResponse;
import com.chargepoint.csms.transactionservice.charging.dto.AuthorizeReqDto;
import com.chargepoint.csms.transactionservice.charging.dto.AuthorizeResDto;
import com.chargepoint.csms.transactionservice.charging.service.AuthorizationService;
import com.chargepoint.csms.transactionservice.charging.message.service.RequestReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final RequestReplyService requestReplyService;


    @Override
    public AuthorizeResDto authorizeDriver(AuthorizeReqDto dto) {

        EventMessage<?> messageResponse = requestReplyService.send(AuthorizationRequestMessage.builder()
                .body(new AuthorizationRequest(dto.getStationUuid(), dto.getDriverIdentifier().getId()))
                .build());


        return AuthorizeResDto.builder()
                .authorizationStatus((AuthorizationStatusResponse) messageResponse.getBody())
                .build();
    }
}
