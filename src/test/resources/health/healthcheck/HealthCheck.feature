@smoke
Feature: Health Check

Scenario: Verify /health endpoint
Given url 'https://app-sf-tenderhub-dev.azurewebsites.net/health'
When method get
Then status 200
And match response.status == 'ok'


