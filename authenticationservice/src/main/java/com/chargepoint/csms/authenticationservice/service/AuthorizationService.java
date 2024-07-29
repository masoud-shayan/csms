package com.chargepoint.csms.authenticationservice.service;

import com.chargepoint.csms.common.type.message.payload.AuthorizationRequest;
import com.chargepoint.csms.common.type.message.payload.AuthorizationStatusResponse;

public interface AuthorizationService {

    AuthorizationStatusResponse authorizeDriverAndStation(AuthorizationRequest authorizationRequest);
}
