@smoke
Feature: Department Constants API

  Scenario: Get all departments successfully
    Given url 'https://app-sf-tenderhub-dev.azurewebsites.net/constants/departments'
    When method get
    Then status 200
    And match response.message == 'Departments retrieved successfully'
    And match response.count == 877

#    And match response == '#[]'
#    And match each response ==
#    """
#    {
#      id: '#number',
#      name: '#string',
#      description: '#string?'
#    }
#    """
#
#  Scenario: Validate department data integrity
#    Given url departmentsEndpoint
#    When method get
#    Then status 200
#    And assert response.length > 0
#  # Verify no duplicate department names
#    And def names = $[*].name
#    And def uniqueNames = karate.distinct(names)
#    And assert names.length == uniqueNames.length
#
#  Scenario: Department response headers validation
#    Given url departmentsEndpoint
#    When method get
#    Then status 200
#    And match header Content-Type == 'application/json; charset=utf-8'
#    And match header Cache-Control == '#string'
