package com.chargepoint.csms.transactionservice.charging.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorizeReqDto {

    @NotBlank
    private String stationUuid;

    @Valid
    private DriverIdentifierDto driverIdentifier;
}
