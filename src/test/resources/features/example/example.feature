Feature: Example feature
  As a user visiting the application
  I want to perform a sample action
  So that I can verify the system works correctly

  @smoke @regression
  Scenario: Verify home page loads correctly
    Given the user navigates to the home page
    When the user sees the main content
    Then the page title should be visible
