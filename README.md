# Termed E2E Tests

Termed is a web-based vocabulary and metadata editor. 

This project contains end-to-end tests for Termed (API + UI).

## Running

mvn test

## Configuration

By default, tests are run against local Termed instance running on `http://localhost:8000/`
with default username and password.

To configure different host, user etc., following system properties are supported:
```
termed.protocol
termed.username
termed.password
termed.host
termed.port
termed.path
```
