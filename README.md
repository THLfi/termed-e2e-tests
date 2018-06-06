# Termed E2E Tests

Termed is a web-based vocabulary and metadata editor. 

This project contains end-to-end tests for Termed (API + UI).

## Running

mvn test

## Configurations

By default, tests are run against local Termed instance running on `http://localhost:8000/` with default username and password.

To configure different host, user etc., edit:
```
/src/test/resources/test.properties
```
with config like:
```
termed.protocol=http
termed.username=e2e-tester
termed.password=...
termed.host=<my-domain>
termed.port=8810
termed.path=/termed
```
