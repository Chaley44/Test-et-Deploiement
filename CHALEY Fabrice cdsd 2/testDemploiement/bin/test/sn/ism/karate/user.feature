Feature: Test User API

  Scenario: Get all users
    Given url 'http://localhost:8080/users'
    When method GET
    Then status 200
    And match response == [
      { id: 1, name: 'Jordane', email: 'john@doe.com', password: '123456' },
      { id: 2, name: 'Jane', email: 'jane@doe.com', password: '123456' },
      { id: 3, name: 'Bob', email: 'bob@doe.com', password: '123456' }
    ]

  Scenario: Get a user by id
    Given url 'http://localhost:8080/users/1'
    When method GET
    Then status 200
    And match response == { id: 1, name: 'Jordane', adresse: 'john@doe.com', password: '123456' }

  Scenario: Create a new user
    Given url 'http://localhost:8080/users'
    And request { id: 4, name: 'Fabrice', adresse: 'fabrice@doe.com', password: '123456' }
    When method POST
    Then status 200
    And match response == { id: 4, name: 'Nathan', adresse: 'nathan@doe.com', password: '123456' }

    Scenario: Update a user
    Given url 'http://localhost:8080/users/1'
    And request { id: 1, name: 'Jordan', adresse: 'jordan@doe.com', password: '123456' }
    When method put
    Then status 200
    And match response.name == 'Jordan'

  Scenario: Delete a user
    Given url 'http://localhost:8080/users/1'
    When method delete
    Then status 200