# ParcelDelivery(GUAVAPAY)

## Environment preparation:

> ***! NOTE:***
> 
> Add name resolution to your hosts (It is required by OAuth server.) 
> - run script `update-hosts.sh`
> 
>  or
> - add manually `127.0.0.1 guavapay-auth-server`


Run script `build-script.sh` on linux machine or prepare step by step as described above.

## Build steps:

- **Prepare environment**
```
$ cd docker/
$ docker-compose up 
```

- **Build maven project**

```
$ cd parcel-delivery-commons/
$ mvn clean install

$ cd ../parcel-delivery-kafka/
$ mvn clean install

$ cd ../identity-and-access-management-service/
$ mvn clean install

$ cd ../resource-server/
$ mvn clean install

$ cd ../delivery-service/
$ mvn clean install
```

## Startup

- start iam service
```
$ java -jar identity-and-access-management-service/service/target/identity-and-access-management-service-0.0.1-SNAPSHOT.jar
```
- start delivery service
```
$ java -jar delivery-service/service/target/delivery-service-0.0.1-SNAPSHOT.jar
```
## Data preporation

- application starts with default user of role `ADMIN` `{"name": "root_admin", "password": "root_admin"}` 
- provide basic OAuth2 client to IAM server using query:

```json
POST /api/registered-client
{
  "clientId": "parcel_delivery",
  "clientSecret": "parcel_delivery",
  "clientAuthenticationMethods": [
     "client_secret_basic"
  ],
  "authorizationGrantTypes": [
     "refresh_token",
     "authorization_code"
  ],
  "redirectUris": [
    "http://127.0.0.1:9091/authorized",
    "http://127.0.0.1:9091/login/oauth2/code/delivery-service"
  ],
  "scopes": [
    "delivery",
    "openid"
  ]
}
```


> ***! NOTE:*** 
> 
>  As for OAuth2 server redirection to `localhost` not allowed please use `127.0.0.1` during testing  