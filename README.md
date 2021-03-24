# Entry
BackEnd project for registration and login

## Installation
#### PostgreSQL
Run DB in docker. Port to access `4444`
```bash
docker run -d -p 4444:5432 \
           --restart=on-failure:10 \
           --name entry \
           -e POSTGRES_PASSWORD=password \
           -e POSTGRES_DB=registration \
           -v fnsnv-fns-volume:/var/lib/postgresql/data \
           postgres
```
#### [MailDev](https://github.com/maildev/maildev)
```bash
docker run -p 1080:80 -p 1025:25 maildev/maildev
```

## Usage
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
#### Sign In
[Click](http://localhost:8080)

Login: email

Password: password
