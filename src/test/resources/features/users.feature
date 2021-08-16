Feature: User validations

  @CreateUsersValid
  Scenario Outline: TC <id> - Verify if users are being successfully added using CreateUserAPI
    Given The "createUser" payload is loaded
    And The request body contains next params
      | gender | <gender> |
      | status | <status> |
      | email  | <email>  |
    When User calls "CreateUserAPI" with "POST" http request
    Then The API call is success with status code 201
    And Verify userId created maps to "<email>" using "GetUserAPI"

    Examples:
      | id | gender | status   | email                   |
      | 01 | male   | active   | testUser7@gmail.com      |
      | 02 | male   | inactive | userAccount2@hotmail.com |
      | 03 | female | active   | test12345@yahoo.com      |
      | 04 | female | inactive | 123_Account1@outlook.es  |