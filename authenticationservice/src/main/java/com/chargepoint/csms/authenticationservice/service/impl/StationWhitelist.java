package com.chargepoint.csms.authenticationservice.service.impl;

import java.util.Set;

public interface StationWhitelist {

    Set<String> STATIONS_UUIDS =
            Set.of("d704751f-ac8a-427c-bcc0-307ee9060465",
                    "e1936295-92c2-434d-962b-ab4a10665510",
                    "862b7720-7690-4eb0-a821-42a303c4cc2d",
                    "22ac9d93-975f-462a-8df7-f51c6544b6eb",
                    "4fd2782b-e5e1-448f-aa8f-a6c39a2192a9"
            );


    static boolean isExist(String value) {
        return STATIONS_UUIDS.contains(value);
    }


    static boolean isValid(String stationUuid) {
        return stationUuid.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
    }
}
