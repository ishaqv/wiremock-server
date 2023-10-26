package com.circlek.ngrp.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import lombok.extern.slf4j.Slf4j;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@Slf4j
public class WireMockMain {

    public static void main(String[] args) {
        try {
            log.info("WireMockServer is going to start");
            WireMockServer wireMockServer = new WireMockServer(wireMockConfig()
                    .port(8080)
                    .httpsPort(8443));
            wireMockServer.start();
            log.info("WireMockServer started");
            log.info("WireMockServer loading stubs");
            new BrierleyApiStub().createStubs();
            log.info("All stubs are created and server is ready to accept requests");
        } catch (Exception exception) {
            log.error("An unexpected error occurred", exception);
        }
    }
}