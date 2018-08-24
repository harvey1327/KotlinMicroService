# Kotlin Ktor with Expose

MicroService to be written with ktor framework and kotlin, postgreSQL database used with Expose

## Working endpoints:

### Get
```
http://localhost:4567/account/all
curl localhost:4567/account/all

http://localhost:4567/account/1
curl localhost:4567/account/1
```
### Post
```
http://localhost:4567/account/create
curl --header "Content-Type: application/json" --request POST --data '{ "name" : "AccountOne" }' localhost:4567/account/create
```
### Put
```
http://localhost:4567/account/update/
curl --header "Content-Type: application/json" --request PUT --data '{ "name" : "AccountOne", "id" : "1" }' localhost:4567/account/update
```
### Delete
```
http://localhost:4567/account/delete/1
curl --header "Content-Type: application/json" --request DELETE localhost:4567/account/delete/1
```
##Running App
```
./gradlew clean build
docker-compose up ktordb (wait for connection to be stable as ktorservice will fail otherwise, should probably sort this at some point)
docker-compose up ktorservice
```
