Start the application (port 8080 should be available)

db file: `~/h2test`

Open your terminal, use curl to test the APIs. To be able to work with strings, one should do authorization first:

`curl -X POST http://localhost:8080/auth/signin -H "Content-Type:application/json" -d "{\"username\":\"user\", \"password\":\"password\"}"`

`{
  "username" : "user",
  "token" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMMTU5MjczNDI2NSwiZXhwIjoxNTkyNzM3ODY1fQ.71ZwQfB7Jd9ujHQ3olFaKP3IYGzwhqmF4nEnMfSQePs"
}`

Then pass returned token to strings API:

`curl --data '{"value": "asddd"}' "http://localhost:8080/strings/add" -H "Content-Type: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMMTU5MjczNDI2NSwiZXhwIjoxNTkyNzM3ODY1fQ.71ZwQfB7Jd9ujHQ3olFaKP3IYGzwhqmF4nEnMfSQePs"`

`curl "http://localhost:8080/strings/avg" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMMTU5MjczNDI2NSwiZXhwIjoxNTkyNzM3ODY1fQ.71ZwQfB7Jd9ujHQ3olFaKP3IYGzwhqmF4nEnMfSQePs"`

`curl "http://localhost:8080/strings/count" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMMTU5MjczNDI2NSwiZXhwIjoxNTkyNzM3ODY1fQ.71ZwQfB7Jd9ujHQ3olFaKP3IYGzwhqmF4nEnMfSQePs"`

