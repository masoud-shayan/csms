package com.chargepoint.csms.transactionservice.charging.controller;

import com.chargepoint.csms.common.type.message.payload.AuthorizationStatusResponse;
import com.chargepoint.csms.transactionservice.charging.dto.AuthorizeReqDto;
import com.chargepoint.csms.transactionservice.charging.dto.AuthorizeResDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class TransactionControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    @Test
    void when_InvalidIdentifierIsSent_Then_AuthorizeEndpointReturnsInvalid() throws Exception {

        AuthorizeReqDto authorizeReqDto = AuthorizeReqDtoGenerator.generateBy_validKnownStationId_invalidDriverId();

        MvcResult mvcResult = callAuthorizeEndpointByMockMvc(authorizeReqDto);

        AuthorizeResDto authorizeResActualResult = mapper.readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
        });

        assertThat(authorizeResActualResult.getAuthorizationStatus()).isEqualTo(AuthorizationStatusResponse.INVALID);
    }

    @Test
    void when_ValidUnknownIdentifierIsSent_Then_AuthorizeEndpointReturnsUnknown() throws Exception {

        AuthorizeReqDto authorizeReqDto = AuthorizeReqDtoGenerator.generateBy_validKnownStationId_validUnkownDriverId();

        MvcResult mvcResult = callAuthorizeEndpointByMockMvc(authorizeReqDto);

        AuthorizeResDto authorizeResActualResult = mapper.readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
        });

        assertThat(authorizeResActualResult.getAuthorizationStatus()).isEqualTo(AuthorizationStatusResponse.UNKNOWN);
    }

    @Test
    void when_ValidKnownNotAllowedIdentifierIsSent_Then_AuthorizeEndpointReturnsUnknown() throws Exception {

        AuthorizeReqDto authorizeReqDto = AuthorizeReqDtoGenerator.generateBy_validKnownStationId_validKnownNotAllowedDriverId();

        MvcResult mvcResult = callAuthorizeEndpointByMockMvc(authorizeReqDto);

        AuthorizeResDto authorizeResActualResult = mapper.readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
        });

        assertThat(authorizeResActualResult.getAuthorizationStatus()).isEqualTo(AuthorizationStatusResponse.REJECTED);
    }

    @Test
    void when_ValidKnownAllowedIdentifierIsSent_Then_AuthorizeEndpointReturnsUnknown() throws Exception {

        AuthorizeReqDto authorizeReqDto = AuthorizeReqDtoGenerator.generateBy_validKnownStationId_validKnownAllowedDriverId();

        MvcResult mvcResult = callAuthorizeEndpointByMockMvc(authorizeReqDto);

        AuthorizeResDto authorizeResActualResult = mapper.readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
        });

        assertThat(authorizeResActualResult.getAuthorizationStatus()).isEqualTo(AuthorizationStatusResponse.ACCEPTED);
    }

    private MvcResult callAuthorizeEndpointByMockMvc(AuthorizeReqDto authorizeReqDto) throws Exception {
        return this.mockMvc
                .perform(post("/api/v1/transaction/authorize")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(authorizeReqDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

}