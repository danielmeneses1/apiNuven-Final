FROM ubuntu:latest
LABEL authors="daniel"

ENTRYPOINT ["top", "-b"]

FROM mysql:latest

COPY ./init.sql /docker-entrypoint-initdb.d/