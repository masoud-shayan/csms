package com.chargepoint.csms.transactionservice.charging.dto;

import com.chargepoint.csms.common.type.message.payload.AuthorizationStatusResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizeResDto {

    private AuthorizationStatusResponse authorizationStatus;
}
