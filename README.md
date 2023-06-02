# ngrp-wiremock-server
The purpose of this is to stand up a wiremock server so that we can mock external calls in the dev.

## steps to run the mock server

`1. docker build -t wiremockserver .`

`2. docker run -p8080:8080 wiremockserver`
