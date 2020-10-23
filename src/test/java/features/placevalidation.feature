Feature: Validate placeApi's

Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
  Given Add place payload with "<name>" "<language>" "<address>"
  When user calls "ADDPLACEAPI" with "Post" http request
  Then the API call got success with status code 200
  And "status" in response is "OK"
  And "scope" in response is "APP"
  And verify place_id created maps to "<name>" using "GETPLACEAPI"

  Examples:
    | name | language | address  |
    |name1 | language1 | address1 |
    |name2 | language2 | address2 |



