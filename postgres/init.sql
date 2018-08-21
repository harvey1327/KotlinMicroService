GRANT ALL PRIVILEGES ON DATABASE ktorservice TO ktoruser;
CREATE TABLE account(
"id" serial PRIMARY KEY,
"name" VARCHAR (50) NOT NULL
);