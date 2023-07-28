Feature: Login INF

  @Post @Login
  Scenario: Login with valid username and password
    Given Send Api Login with username and password "validAccount"
    Then Verify Login success with status code is 200