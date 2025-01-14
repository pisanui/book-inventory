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

Sequence Diagram: https://mermaid.live/view#pako:eNrNU8GO0zAQ_ZWRryRiD3DJoVJLclgJCpsuHFCkahrPbiwcT9aeBFVV_x0nLodFlNNqhS8Zy8_vvYnfnFTLmlShAj2N5FoqDT567BsHcbXWkJN8tXpzYP6RGzfFLftjHshPpqUCPqC1sP5yCwMHgRkVYDIIb5cysWxZCHgiD9dY6lk83t-wPsJJjFjKAEfp2GcwjAdrQke6RKFzokQrsMDABKB-kCOkg3n9XSWPbaR-Zj0ZvQNPYWAXCH4a6aATGSAIyhjg3c0NbNblvq7uvla7-8RNNkKTq1eXffYTZvUohNbo-IWWnTZi2P0PXtCS0-hf1Mr7aOV2e1_V2_XH_a6qv1X1vqrrz_XFlNOpuCK1WpWbAnY40YKINoVB8GDT_pLScjN7up7QxeN8_E-tSJH_0deiieFZyFWmevI9Gh1H7zQzNko66qlRRSw1PeBopVGNO0dozBzvjq5VhfiRMuV5fOxU8YDxNTI1Djq-w2Vuf0MoJoL9pzTby4hnakD3nbm_XDz_AusoXuE




# To get book by author
curl  -X GET \
  'localhost:8080/books?author=tom' \
  --header 'Accept: */*'

Sequence Diagram : https://mermaid.live/view#pako:eNqNUk1PwzAM_StWxI1WcNipEkOMahIHNsTHBVVCWeO2EWlcEmdomvbfyZYNofEhcrJf7PeebK9FTQpFITy-BbQ1llq2TvaVhfhqo9FyPh6fLohec22XMSW3yj26pa6xgGtpDFzd3UCLDNsiD4sVyMAdOVhqCWc78DIhFyfrFGwS_8-sUa-cFDDVVn2j1BZYLgymj8RSTvLY8pvDe-TgLBjtGajZ9aU2aRgmMQNLDA0FqxL-h7Eok0bySevQD2Q9wrvmDjrmATxLDh5G5yOYzR9fpvOnWZmY0Xj8j0Z-JPLVOzDt17LntEpkokfXS63iHtdbuBLcYY-VKGKosJHBcCUqu4mlcZD0sLK1KNgFzISj0HaiaGQ0l4kwKMmHIziUoNLR4m06lN29ZGKQ9pmo3zduPgDiDMiJ