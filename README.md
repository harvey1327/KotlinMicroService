# Kotlin Spark

MicroService to be written with spark framework and kotlin

## Working endpoints:

### Get
http://localhost:4567/account/all
curl localhost:4567/account/all

### Post
http://localhost:4567/account/create
curl --header "Content-Type: application/json" --request POST --data '{ "name" : "AccountOne" }' localhost:4567/account/create

### Patch
http://localhost:4567/account/update/
curl --header "Content-Type: application/json" --request PATCH --data '{ "name" : "AccountOne" "id" : "1" }' localhost:4567/account/update

### Delete
http://localhost:4567/account/delete
curl --header "Content-Type: application/json" --request DELETE --data '{ "name" : "AccountOne" "id" : "1" }' localhost:4567/account/delete

##PostgreSQL
docker run --name sparkdb -p 5432:5432 -d sparkdb
