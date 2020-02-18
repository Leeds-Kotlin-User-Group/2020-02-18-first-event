#!/bin/bash

docker ps --filter "name=reactive-kotlin-demo" --format "{{.ID}}" | xargs -I {} docker rm -f {}
TMPDIR=$HOME/tmp docker-compose --project-name reactive-kotlin-demo -f ./local/docker-compose.yml up --build -d --force-recreate
export POSTGRES_PORT=`docker port reactive-kotlin-demo-postgres 5432 | cut -d ":" -f 2`

printf "POSTGRES_PORT=$POSTGRES_PORT\n"
