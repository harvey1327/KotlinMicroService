GRANT ALL PRIVILEGES ON DATABASE sparkservice TO sparkuser;
CREATE TABLE accounts(
"id" serial PRIMARY KEY,
"name" VARCHAR (50) NOT NULL
);