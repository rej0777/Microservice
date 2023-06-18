# Microservice 3 aplikacje 




## Product
 Stream for observing new product 
http://localhost:8091/product/stream

add new product http://localhost:8091/product
Body raw>json 
{    "description": "produkt5" ,    "price": 1246}

## User



##  Order

http://localhost:8080/order
Body Json 

{    "productId":"644ce1829746d217a521eaa5",    "userId": 1}

Response
{
    "orderId": 1,
    "userId": 1,
    "productId": "644ce1829746d217a521eaa5",
    "amount": 124,
    "status": "COMPLETED"
}
-------------
http://localhost:8080/order/user/1
Return
List of all orders per user
