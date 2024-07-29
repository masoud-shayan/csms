# CSMS (charging station management system)

## Overview

Welcome to **CSMS!** \
This application provides an endpoint designed to send an authorization request from the Transaction-Service to the
Authentication-Service and return the result to initiate the charging process. \
The details are based on my assumptions from the task description file, so if my assumptions are incorrect, please bear
with me :)

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Spring Boot 3.3.0

### REST API

|            Endpoint                 |  Method  |    Req. body    | Status |   Resp. body    | Description                                                       |
|:--------------------------------:|:--------:|:---------------:|:------:|:---------------:|:------------------------------------------------------------|
| `/api/v1/transaction/authorize`  |  `POST`  | AuthorizeReqDto |  200   | AuthorizeResDto | send an authorization request to the Authentication-Service |

To provide a clear understanding of the data structure, here are the JSON representations for the request and response
bodies:

AuthorizeReqDto: 
```json
{
"stationUuid": "string",
"driverIdentifier": {
"id": "string"
}
}
```

AuthorizeResDto: 
```json
{
"authorizationStatus": "string"
}
```

To provide further clarity, here are the whitelisted values for the fields "stationUuid" and "driverIdentifier" in the
request body:

|               stationUuid                |
|:---------------------------------------|
| `d704751f-ac8a-427c-bcc0-307ee9060465` |
| `e1936295-92c2-434d-962b-ab4a10665510` |
| `862b7720-7690-4eb0-a821-42a303c4cc2d` |
| `22ac9d93-975f-462a-8df7-f51c6544b6eb` |
| `4fd2782b-e5e1-448f-aa8f-a6c39a2192a9` |

| driverIdentifier                                                       | State       |
|:--------------------------------------------------------------------|:------------|
| 'x9GVf9jqvjG8Wyue2gdatnUOmQ'                                        | ALLOWED     |
| '4GregflbJTJNYBoKnsi9eZ6KA'                                         | NOT_ALLOWED |
| 'EstvNPBP35fPPZVgqaUXhUDpoDBDow7HYP'                                | ALLOWED     |
| 'G3BMTysF5p7OSrCKw5ejcgoft6k3aZCcotgYOp6Eb2KNnU'                    | NOT_ALLOWED |
| 'yZoNAHipspQJjyoP3WuXB'                                             | NOT_ALLOWED |
| 'CUXMcMQRmLdNFYunkdfnIzhXUeZxlm7lfxhrNSVZ9w6JtwHpRBWoRVejRDQY3sKSJ' | ALLOWED     |
| 'ABuNwjgdBEY1y8ydwIpLSQgKWi4F6vnFLVUeI1C6g9Od'                      | NOT_ALLOWED |
| 'bWvRcamZgK99kAJ4abqdVggBGgvZXHWvFuekFa'                            | ALLOWED     |
| 'SNGi2kU2Je7kIpi1OVmq0Mp4NpPGM8JSk8Y7'                              | NOT_ALLOWED |
| 'bk5Qj8kI5o4tkfc2EUwDaJFU55Hk3KKdCf0TLuZz5mcUvPkX4yyk81UjW1zB'      | ALLOWED     |

### API Documentation

To explore the available APIs and see how to interact with them, please refer to the Swagger documentation:

[Swagger UI](http://localhost:8050/swagger-ui.html)

The Swagger UI provides a user-friendly interface to test and understand the APIs.

## Installation

Follow the steps below to install and set up the project:

1. Clone the repo
   ```sh
   git clone https://github.com/masoud-shayan/csms.git
   ```
2. Navigate to the project root directory
   ```sh
   cd csms
   ```
3. Install the dependencies
   ```sh
   ./mvnw clean install
   ```

## Run

To run the project, it is required to have Docker installed on the target machine. \
Before running the services, you need to build the Docker images for the associated services. This step ensures that all
necessary components are properly containerized and ready for deployment.

1. **To run in a production environment:**
   ```sh
   docker build -t transaction-service .\transactionservice\
   docker build -t authentication-service .\authenticationservice\
   docker-compose -f docker-compose-prod.yml up
   ```
2. **To run in a development environment:**
   ```sh
   docker-compose -f docker-compose-dev.yml up
   ./mvnw -f .\authenticationservice\ spring-boot:run
   ./mvnw -f .\transactionservice\ spring-boot:run
   ```

## Monitoring

In the production environment, a Kafka dashboard console is available for monitoring and managing Kafka topics. The
dashboard is accessible through the following link: [Kafka Dashboard Console](http://localhost:8090)
