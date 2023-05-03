curl -X 'POST' \
  'http://localhost:8080/messages/sendEmail' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "from": "intec.brussel@mail.be",
  "to": "yilmaz@mail.be",
  "subject": "Validation",
  "body": "123465"
}'