@smoke
Feature: Category Constants API


  Scenario: Get all categories successfully
    Given url 'https://app-sf-tenderhub-dev.azurewebsites.net/constants/categories'
    When method get
    Then status 200
    And match response.message == 'Categories retrieved successfully'
    And match response.count == 91
#    And match response == '#[]'
#    And match each response ==
#    """
#    {
#      id: '#number',
#      name: '#string',
#      parentId: '#number?',
#      isActive: '#boolean'
#    }
#    """
#
#  Scenario: Validate category hierarchy
#    Given url categoriesEndpoint
#    When method get
#    Then status 200
#    And assert response.length > 0
#  # Check for valid parent-child relationships
#    And def parentIds = $[?(@.parentId)].parentId
#    And def categoryIds = $[*].id
#    And match parentIds == '#[]'
#
#  Scenario: Filter active categories only
#    Given url categoriesEndpoint
#    When method get
#    Then status 200
#  # Verify all returned categories are active
#    And def activeCategories = $[?(@.isActive == true)]
#    And def inactiveCategories = $[?(@.isActive == false)]
#    And match activeCategories == '#[]'
#
#  Scenario: Category data validation
#    Given url categoriesEndpoint
#    When method get
#    Then status 200
#    And assert response.length > 0
#  # Validate no empty or null names
#    And match response[*].name == '#[] ^[0]'
#    And assert karate.filter(response, function(x){ return !x.name }).length == 0