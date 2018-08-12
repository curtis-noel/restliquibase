This project requires a local postgres db, which can be instatiated via docker:

docker run --name restliquibase -p 5432:5432 -e POSTGRES_DB=restliquibase -e POSTGRES_PASSWORD=securepassword -d postgres

run the application with ./gradlew bootRun

adding a user:
curl -i -X POST -H "Content-Type:application/json" -d '{  "name" : "Test" , "id" : "79D54"}' http://localhost:8080/users

confirming post:
curl http://localhost:8080/users/79D54