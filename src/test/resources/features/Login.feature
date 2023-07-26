Feature: Verify Login INF

  @Login
  Scenario: Login to INF with valid username and password
    Given "Actor" Navigate successfully to Login Page on "staging"
    When Input valid account with "OPS" role
    And User click login button

