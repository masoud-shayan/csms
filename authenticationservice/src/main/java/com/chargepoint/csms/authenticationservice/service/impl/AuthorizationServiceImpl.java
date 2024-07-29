package com.chargepoint.csms.authenticationservice.service.impl;

import com.chargepoint.csms.authenticationservice.service.AuthorizationService;
import com.chargepoint.csms.common.type.message.payload.AuthorizationRequest;
import com.chargepoint.csms.common.type.message.payload.AuthorizationStatusResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {


    @Override
    public AuthorizationStatusResponse authorizeDriverAndStation(AuthorizationRequest authRequest) {

        if (!StationWhitelist.isValid(authRequest.getStationUuid()) || !DriverWhitelist.isValid(authRequest.getDriverIdentifier()))
            return AuthorizationStatusResponse.INVALID;

        if (!StationWhitelist.isExist(authRequest.getStationUuid()) || !DriverWhitelist.isExist(authRequest.getDriverIdentifier()))
            return AuthorizationStatusResponse.UNKNOWN;

        if (!DriverWhitelist.isAllowed(authRequest.getDriverIdentifier())) {
            return AuthorizationStatusResponse.REJECTED;
        } else {
            return AuthorizationStatusResponse.ACCEPTED;
        }
    }
}
