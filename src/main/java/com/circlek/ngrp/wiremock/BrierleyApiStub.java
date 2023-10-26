package com.circlek.ngrp.wiremock;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class BrierleyApiStub {
    void createStubs() {
        // Do not remove this as this is required for K8s livenessProbe
        stubFor(get(urlEqualTo("/status")).willReturn(aResponse().withStatus(200)));

        // Brierley Auth API
        stubFor(post(urlEqualTo("/identity/api/v1/auth/token"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(getFileAsString("brierley/auth/auth_response.json"))));

        // Brierley Member Search API - Partially Enrolled Member
        stubFor(post(urlEqualTo("/member/api/v1/members/search"))
                .withRequestBody(equalToJson(getFileAsString("brierley/member_search/member_search_request_+14942613424.json"), true, true))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(getFileAsString("brierley/member_search/member_search_response_+14942613424.json"))));

        // Brierley Member Search API - Fully Enrolled Member
        stubFor(post(urlEqualTo("/member/api/v1/members/search"))
                .withRequestBody(equalToJson(getFileAsString("brierley/member_search/member_search_request_+16623027817.json"), true, true))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(getFileAsString("brierley/member_search/member_search_response_+16623027817.json"))));

        // Evaluate Discount API - loyalty card A returns a fuel discount
        stubFor(post(urlEqualTo("/transactions/api/v1/transactions/evaluateDiscounts"))
                .withRequestBody(equalToJson(getFileAsString("brierley/evaluate_discounts/evaluate_discounts_request_cardA_8018782603539326790533.json"), true, true))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(getFileAsString("brierley/evaluate_discounts/evaluate_discounts_response_cardA_8018782603539326790533.json"))));
    }

    @SneakyThrows
    private String getFileAsString(String file) {
        return IOUtils.toString(Objects.requireNonNull(getClass().getResourceAsStream(file)), StandardCharsets.UTF_8);
    }
}
