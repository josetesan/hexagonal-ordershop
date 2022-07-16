### HOW-TO
Run this command before running the application:
```shell
docker run -p 5432:5432 --rm --name postgres -e POSTGRES_PASSWORD=mypassword postgres:14-alpine
``` 