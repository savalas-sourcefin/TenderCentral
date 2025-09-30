@smoke
Feature: Province Constants API


  Scenario: Get all provinces successfully
    Given url 'https://app-sf-tenderhub-dev.azurewebsites.net/constants/provinces'
    When method get
    Then status 200
    And match response.success == true
    And match response.message == 'Provinces retrieved successfully'
    And match response.count == 10
    And match response.data[*].name contains 'Gauteng', 'Western Cape', 'KwaZulu-Natal', 'Free State', 'Limpopo', 'Mpumalanga', 'Northern Cape','North West', 'National'


#    """
#    {
#      id: '#number'#    """
#
#  Scenario: Validate province data structure
#    Given url provincesEndpoint
#    When method get
#    Then status 200
#    And assert response.length > 0
#    And match response[0].name == '#string'
#    And match response[0].id == '#number'
#
#  Scenario: Response time validation for provinces
#    Given url provincesEndpoint
#    When method get
#    Then status 200
#    And assert responseTime < 3000
