# book-inventory
To manage simple book inventory

# Step to start application
- install docker then start it
- start the application
- call api via postman or other tool that can call api


# CURL


# To create book
curl  -X POST \
  'localhost:8080/books' \
  --header 'Accept: */*' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "title": "tom's life 2",
  "author": "tom",
  "publishedDate": "2024-02-01"
}'

# To get book by author
curl  -X GET \
  'localhost:8080/books?author=tom' \
  --header 'Accept: */*'