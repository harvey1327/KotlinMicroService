GRANT ALL PRIVILEGES ON DATABASE sparkservice TO sparkuser;
CREATE TABLE usertable(
user_id serial PRIMARY KEY,
user_name VARCHAR (50) NOT NULL,
user_email VARCHAR (100) UNIQUE NOT NULL
);
