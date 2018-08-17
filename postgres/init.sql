GRANT ALL PRIVILEGES ON DATABASE sparkservice TO sparkuser;
CREATE TABLE account(
"id" serial PRIMARY KEY,
"name" VARCHAR (50) NOT NULL
);