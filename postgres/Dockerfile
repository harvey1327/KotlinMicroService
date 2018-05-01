FROM postgres:latest

ENV POSTGRES_USER sparkuser
ENV POSTGRES_PASSWORD password
ENV POSTGRES_DB sparkservice

COPY init.sql /docker-entrypoint-initdb.d/
