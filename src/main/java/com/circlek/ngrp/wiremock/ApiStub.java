package com.circlek.ngrp.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class ApiStub {
    void createStubs() {
        //Do not remove this as this is required for K8s livenessProbe
        stubFor(get(urlEqualTo("/status")).willReturn(aResponse().withStatus(200)));

        //replace with your own stub
        stubFor(get(urlEqualTo("/api/get/example"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\": \"Hello, WireMock\"}")));

        //replace with your own stub
        stubFor(post(urlEqualTo("/api/post/example"))
                .withRequestBody(containing("{\"id\": \"100\"}"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"response\": \"success\"}")));
    }
}
