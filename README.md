# ShopaholicX

## Steps to Run the Project:

### Run these commands before to setup the env

docker run -d --name mysql_product_service -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 mysql:5.7.43

docker exec -it mysql_product_service mysql -u root -p 

type root

CREATE DATABASE productdb;

CREATE DATABASE orderdb;

## Run each microservice in the following manner

1. Run Service Registry
2. Run Config Server
3. Run Product Service
4. Run Order Service


## Extra commands to view data from backend

USE productdb/orderdb;

SHOW TABLES;

SELECT * FROM <Table_name>;