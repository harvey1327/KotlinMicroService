FROM postgres:latest

ENV POSTGRES_USER ktoruser
ENV POSTGRES_PASSWORD password
ENV POSTGRES_DB ktorservice

COPY init.sql /docker-entrypoint-initdb.d/
