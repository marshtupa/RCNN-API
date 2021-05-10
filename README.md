# RCNN-API
BackEnd for use Mask-RCNN

## Installation
#### PostgreSQL
Run DB in docker. Port to access `4444`
```bash
docker run -d -p 4444:5432 \
           --restart=on-failure:10 \
           --name rcnn-api \
           -e POSTGRES_PASSWORD=password \
           -e POSTGRES_DB=registration \
           postgres
```
#### [MailDev](https://github.com/maildev/maildev)
```bash
docker run -p 1080:80 -p 1025:25 maildev/maildev
```

## Usage

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/10960260-465361c6-c298-4dfd-9d63-f397870bd8cd?action=collection%2Ffork&collection-url=entityId%3D10960260-465361c6-c298-4dfd-9d63-f397870bd8cd%26entityType%3Dcollection%26workspaceId%3Dd886d45c-314d-450c-ba0b-dad7300104a3)

#### Sign Up
To complete SignUp lookup at [MailDev UI](http://192.168.99.100:1080/#/) and click at verification link 
```bash
curl --location --request POST 'localhost:8080/api/v1/registration' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Bogdan",
    "lastName": "Marshtupa",
    "email": "marshtupa@mtuci.ru",
    "password": "password"
}'
```

#### User controller

```bash
curl -u marshtupa@mtuci.ru:password http://localhost:8080/api/v1/user
```

```bash
curl -u marshtupa@mtuci.ru:password http://localhost:8080/api/v1/user/1
```
