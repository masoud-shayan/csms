package com.chargepoint.csms.authenticationservice.service.impl;

import com.chargepoint.csms.common.type.message.payload.AuthorizationRequest;
import com.chargepoint.csms.common.type.message.payload.AuthorizationStatusResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceTest {

    @InjectMocks
    private AuthorizationServiceImpl authorizationService;

    private AuthorizationRequest authRequest = new AuthorizationRequest("stationUuid", "driverIdentifier");

    @Test
    public void when_InvalidIdentifierIsPresent_Then_authorizeDriverAndStationReturnsInvalid() {

        try (MockedStatic<StationWhitelist> stationWhitelistMock = Mockito.mockStatic(StationWhitelist.class);
             MockedStatic<DriverWhitelist> driverWhitelistMock = Mockito.mockStatic(DriverWhitelist.class)) {

            stationWhitelistMock.when(() -> StationWhitelist.isValid(authRequest.getStationUuid())).thenReturn(true);
            driverWhitelistMock.when(() -> DriverWhitelist.isValid(authRequest.getDriverIdentifier())).thenReturn(false);

            AuthorizationStatusResponse actualAuthRes = authorizationService.authorizeDriverAndStation(authRequest);

            assertThat(actualAuthRes).isEqualTo(AuthorizationStatusResponse.INVALID);
        }

    }

    @Test
    public void when_ValidUnknownIdentifierIsPresent_Then_authorizeDriverAndStationReturnsUnknown() {

        try (MockedStatic<StationWhitelist> stationWhitelistMock = Mockito.mockStatic(StationWhitelist.class);
             MockedStatic<DriverWhitelist> driverWhitelistMock = Mockito.mockStatic(DriverWhitelist.class)) {

            stationWhitelistMock.when(() -> StationWhitelist.isValid(authRequest.getStationUuid())).thenReturn(true);
            stationWhitelistMock.when(() -> StationWhitelist.isExist(authRequest.getStationUuid())).thenReturn(true);
            driverWhitelistMock.when(() -> DriverWhitelist.isValid(authRequest.getDriverIdentifier())).thenReturn(true);
            driverWhitelistMock.when(() -> DriverWhitelist.isExist(authRequest.getDriverIdentifier())).thenReturn(false);

            AuthorizationStatusResponse actualAuthRes = authorizationService.authorizeDriverAndStation(authRequest);

            assertThat(actualAuthRes).isEqualTo(AuthorizationStatusResponse.UNKNOWN);
        }
    }

    @Test
    public void when_ValidKnownNotAllowedIdentifierIsPresent_Then_authorizeDriverAndStationReturnsRejected() {

        try (MockedStatic<StationWhitelist> stationWhitelistMock = Mockito.mockStatic(StationWhitelist.class);
             MockedStatic<DriverWhitelist> driverWhitelistMock = Mockito.mockStatic(DriverWhitelist.class)) {

            stationWhitelistMock.when(() -> StationWhitelist.isValid(authRequest.getStationUuid())).thenReturn(true);
            stationWhitelistMock.when(() -> StationWhitelist.isExist(authRequest.getStationUuid())).thenReturn(true);
            driverWhitelistMock.when(() -> DriverWhitelist.isValid(authRequest.getDriverIdentifier())).thenReturn(true);
            driverWhitelistMock.when(() -> DriverWhitelist.isExist(authRequest.getDriverIdentifier())).thenReturn(true);
            driverWhitelistMock.when(() -> DriverWhitelist.isAllowed(authRequest.getDriverIdentifier())).thenReturn(false);

            AuthorizationStatusResponse actualAuthRes = authorizationService.authorizeDriverAndStation(authRequest);

            assertThat(actualAuthRes).isEqualTo(AuthorizationStatusResponse.REJECTED);
        }
    }

    @Test
    public void when_ValidKnownAllowedIdentifierIsPresent_Then_authorizeDriverAndStationReturnsAccepted() {

        try (MockedStatic<StationWhitelist> stationWhitelistMock = Mockito.mockStatic(StationWhitelist.class);
             MockedStatic<DriverWhitelist> driverWhitelistMock = Mockito.mockStatic(DriverWhitelist.class)) {

            stationWhitelistMock.when(() -> StationWhitelist.isValid(authRequest.getStationUuid())).thenReturn(true);
            stationWhitelistMock.when(() -> StationWhitelist.isExist(authRequest.getStationUuid())).thenReturn(true);
            driverWhitelistMock.when(() -> DriverWhitelist.isValid(authRequest.getDriverIdentifier())).thenReturn(true);
            driverWhitelistMock.when(() -> DriverWhitelist.isExist(authRequest.getDriverIdentifier())).thenReturn(true);
            driverWhitelistMock.when(() -> DriverWhitelist.isAllowed(authRequest.getDriverIdentifier())).thenReturn(true);

            AuthorizationStatusResponse actualAuthRes = authorizationService.authorizeDriverAndStation(authRequest);

            assertThat(actualAuthRes).isEqualTo(AuthorizationStatusResponse.ACCEPTED);
        }
    }
}