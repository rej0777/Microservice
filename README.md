# Microservice 3 aplikacje 




Product

User



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
