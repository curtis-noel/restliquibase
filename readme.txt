This project requires a local postgres db, which can be instatiated via docker:

docker run --name restliquibase -p 5432:5432 -e POSTGRES_DB=restliquibase -e POSTGRES_PASSWORD=securepassword -d postgres

run the application with ./gradlew bootRun

adding a user:
curl -i -X POST -H "Content-Type:application/json" -d '{  "name" : "Test" , "id" : "79D54"}' http://localhost:8080/users

confirming post:
curl http://localhost:8080/users/79D54

Docker packaging can be performed using ./gradlew docker

the container can then be run as follows. note the --link to establish connectivity with an existing postgresdb container.  note that the location of the liquibase change logs is referenced differently from a docker instance, so you need to set up a clean db for container based execution.

docker run -d \
    --name restliquibaseapp \
    --link restliquibase:restliquibasedb \
    -p 8080:8080 \
    -e DATABASE_HOST=restliquibasedb \
    -e DATABASE_PORT=5432 \
    -e DATABASE_NAME=restliquibase \
    -e DATABASE_USER=postgres \
    -e DATABASE_PASSWORD=securepassword \
    info.curtisnoel/restliquibase

from local machine

    +------+------------+--------------------------------+----------------------------+-----------------+------------+------------------------------------+---------------+------------+--------+-------------+------------+----------+-----------------+
| id   | author     | filename                       | dateexecuted               | orderexecuted   | exectype   | md5sum                             | description   | comments   | tag    | liquibase   | contexts   | labels   | deployment_id   |
|------+------------+--------------------------------+----------------------------+-----------------+------------+------------------------------------+---------------+------------+--------+-------------+------------+----------+-----------------|
| raw  | includeAll | db/changelog/changes/v0001.sql | 2018-08-12 17:52:31.195469 | 1               | EXECUTED   | 7:ac937c24c679b71ff64d9aec63ff1221 | sql           |            | <null> | 3.5.4       | <null>     | <null>   | 4110751179      |
+------+------------+--------------------------------+----------------------------+-----------------+------------+------------------------------------+---------------+------------+--------+-------------+------------+----------+-----------------+
SELECT 1
~

from docker container

+------+------------+-------------------------------------------------+----------------------------+-----------------+------------+------------------------------------+---------------+------------+--------+-------------+------------+----------+----
| id   | author     | filename                                        | dateexecuted               | orderexecuted   | exectype   | md5sum                             | description   | comments   | tag    | liquibase   | contexts   | labels   | dep
|------+------------+-------------------------------------------------+----------------------------+-----------------+------------+------------------------------------+---------------+------------+--------+-------------+------------+----------+----
| raw  | includeAll | BOOT-INF/classes/db/changelog/changes/v0001.sql | 2018-08-13 01:24:54.629401 | 1               | EXECUTED   | 7:ac937c24c679b71ff64d9aec63ff1221 | sql           |            | <null> | 3.5.4       | <null>     | <null>   | 412
+------+------------+-------------------------------------------------+----------------------------+-----------------+------------+------------------------------------+---------------+------------+--------+-------------+------------+----------+----
SELECT 1

todo: figure out if there is a way to synchronize this.
