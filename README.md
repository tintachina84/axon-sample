# Getting Started

## Run docker container

### MySQL
```bash
docker run -d --rm --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_ROOT_PASSWORD=root -v ~/Project/data/mysql:/var/lib/mysql mysql
```
데이터베이스와 테이블은 어플리케이션 기동 시 자동으로 생성된다.

### Axon Server
```bash
docker run -d --rm --name axonserver -p 18024:8024 -p 18124:8124 -e axoniq.axonserver.devmode.enabled=true axoniq/axonserver
```
-p 18024:8024: Axon Server Web UI

-p 18124:8124: Axon Server REST API

### OpenAPI page
http://localhost:19080/swagger-ui/index.html
