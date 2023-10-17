# waa-lab3

Create a bookstore application which has CRUD operations. 

The Book domain model which attributes: Id(int), title(string), isbn(string), price(double).

1. GET /books - return a List of books

2. GET /books/{id} - return a single book by Id

3. POST /books - add a new book

4. PUT /books/{id} - update a book by id

5. DELETE /books/{id} - delete a book by id

Since we haven't learnt persistence layer, so you can hardcoded book values in controller, we'll change it after we learnt Spring Data.

6. Add 4 types of version. You can choose each method to use 1 type of versioning, or refactor to have 4 different BookController, each 1 is 1 versioning style.
   
+ Media type versioning (a.k.a “content negotiation” or “accept header”)
  - @GetMapping(value = "/{id}", produces = "application/cs.miu.edu-v2+json")
  - Header: Accept: application/cs.miu.edu-v1+json
+ (Custom) header versioning
  - @GetMapping(value = "", headers = "X-API-VERSION=2")
  - Header: X-API-VERSION: 2
+ URI versioning
  - http://localhost:8080/v1/products
+ Request Parameter versioning
  - http://localhost:8080/products?version=1
+ When you finish, test using Postman or other tools you like.
