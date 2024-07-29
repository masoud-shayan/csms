package com.chargepoint.csms.transactionservice.charging.controller;

import com.chargepoint.csms.transactionservice.charging.dto.AuthorizeReqDto;
import com.chargepoint.csms.transactionservice.charging.dto.DriverIdentifierDto;

public interface AuthorizeReqDtoGenerator {

    String VALID_KNOWN_STATION_ID = "e1936295-92c2-434d-962b-ab4a10665510";
    String INVALID_DRIVER_ID = "Vmq0Mp4NpP";
    String VALID_UNKNOWN_DRIVER_ID = "yZoNAHipspQJjZxlm7lfxhrNSVZ9w";
    String VALID_KNOWN_NOT_ALLOWED_DRIVER_ID = "SNGi2kU2Je7kIpi1OVmq0Mp4NpPGM8JSk8Y7";
    String VALID_KNOWN_ALLOWED_DRIVER_ID = "EstvNPBP35fPPZVgqaUXhUDpoDBDow7HYP";


    static AuthorizeReqDto generateBy_validKnownStationId_invalidDriverId() {

        return AuthorizeReqDto.builder()
                .stationUuid(VALID_KNOWN_STATION_ID)
                .driverIdentifier(new DriverIdentifierDto(INVALID_DRIVER_ID))
                .build();
    }

    static AuthorizeReqDto generateBy_validKnownStationId_validUnkownDriverId() {

        return AuthorizeReqDto.builder()
                .stationUuid(VALID_KNOWN_STATION_ID)
                .driverIdentifier(new DriverIdentifierDto(VALID_UNKNOWN_DRIVER_ID))
                .build();
    }

    static AuthorizeReqDto generateBy_validKnownStationId_validKnownNotAllowedDriverId() {

        return AuthorizeReqDto.builder()
                .stationUuid(VALID_KNOWN_STATION_ID)
                .driverIdentifier(new DriverIdentifierDto(VALID_KNOWN_NOT_ALLOWED_DRIVER_ID))
                .build();
    }

    static AuthorizeReqDto generateBy_validKnownStationId_validKnownAllowedDriverId() {

        return AuthorizeReqDto.builder()
                .stationUuid(VALID_KNOWN_STATION_ID)
                .driverIdentifier(new DriverIdentifierDto(VALID_KNOWN_ALLOWED_DRIVER_ID))
                .build();
    }
}
