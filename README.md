# Entry
BackEnd project for registration and login
## Installation
### PostgreSQL
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