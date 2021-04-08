Feature: Module Product  
  CRUD the product

  Scenario: End-to-End Testing Product
    Given I perform POST operation for "/products" with payload
    """
    {"name":"Chocho Crunch", "description":"Delicious Product","image":"https://nilaigizi.com/assets/images/produk/produk_1583131139.jpg","price":10000.0,"status":false}
    """
    And I perform GET operation for "/products" that I recently create
    And I perform PUT operation for "/products" that I recently created to include
    """
    {"discount_amount":100.0,"status":true}
    """ 
    Then I perform DELETE operation for "/products" that I recently updated