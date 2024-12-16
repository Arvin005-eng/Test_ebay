Feature: eBay


  @tag1
  Scenario: AddToCart
    Given Open the browser and navigate to url
    And get screenshot
    When Searches for "Book" on eBay
    And Select first search result
    And get screenshot
    And Switch to window using index 1
    And Add iteam to cart
    And get screenshot
    Then Verify the cart has been updated

    
     @tag1
  Scenario Outline: ApiValidation
   Given Get Api response using BaseURL "https://api.coindesk.com/v1/bpi"
   And Verify the response contains "<BPI1>"
   And Verify the response contains "<BPI2>"
   And Verify the response contains "<BPI3>"
   And Verify the GBP description equals "<Description>"
   
   Examples: 
   |BPI1|BPI2|BPI3|Description|
   |USD|GBP|EUR|British Pound Sterling|