package com.chargepoint.csms.authenticationservice.service.impl;

import java.util.Map;

public interface DriverWhitelist {

    Map<String, CardState> DRIVERS_IDENTIFIERS =
            Map.of("x9GVf9jqvjG8Wyue2gdatnUOmQ", CardState.ALLOWED,
                    "4GregflbJTJNYBoKnsi9eZ6KA", CardState.NOT_ALLOWED,
                    "EstvNPBP35fPPZVgqaUXhUDpoDBDow7HYP", CardState.ALLOWED,
                    "G3BMTysF5p7OSrCKw5ejcgoft6k3aZCcotgYOp6Eb2KNnU", CardState.NOT_ALLOWED,
                    "yZoNAHipspQJjyoP3WuXB", CardState.NOT_ALLOWED,
                    "CUXMcMQRmLdNFYunkdfnIzhXUeZxlm7lfxhrNSVZ9w6JtwHpRBWoRVejRDQY3sKSJ", CardState.ALLOWED,
                    "ABuNwjgdBEY1y8ydwIpLSQgKWi4F6vnFLVUeI1C6g9Od", CardState.NOT_ALLOWED,
                    "bWvRcamZgK99kAJ4abqdVggBGgvZXHWvFuekFa", CardState.ALLOWED,
                    "SNGi2kU2Je7kIpi1OVmq0Mp4NpPGM8JSk8Y7", CardState.NOT_ALLOWED,
                    "bk5Qj8kI5o4tkfc2EUwDaJFU55Hk3KKdCf0TLuZz5mcUvPkX4yyk81UjW1zB", CardState.ALLOWED
            );


    static boolean isExist(String key) {
        return DRIVERS_IDENTIFIERS.containsKey(key);
    }

    static boolean isAllowed(String key) {
        return CardState.ALLOWED == DRIVERS_IDENTIFIERS.getOrDefault(key, CardState.NOT_ALLOWED);
    }

    static boolean isValid(String driverIdentifier) {
        return driverIdentifier.length() >= 20 && driverIdentifier.length() <= 80;
    }
}
