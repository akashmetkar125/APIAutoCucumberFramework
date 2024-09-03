Feature: Validate Add Place API is working as expected

@AddPlace @Regression
Scenario Outline: To verify Place is added after calling Add Place API

Given Add payload of add place API with "<name>" "<language>" "<address>"
When  Hit "AddPlace" API with http "POST" request
Then  API call got success with status code 200
And   and "status" in response is "OK" 
And    and "scope" in response is "APP"
And Verify place Id created maps to "<name>" using "GetPlace" API

Examples:

  | name     | language  | address            |
  | AAHouse  | English   | Shahu Maharaj Nagar|
# | BBHouse  | Hindi     | Sahyog Nagar       |


@DeletePlace @Regression
Scenario: Verify Delete place API is working as expected

Given Delete Place Payload
When  Hit "DeletePlace" API with http "POST" request
Then  API call got success with status code 200