# metro-application

## How to run:

### 1. Using Docker, execute following commands in root folder:


- `docker build -t ecommerce/metro-application .`
- `docker run -p 8080:8080 ecommerce/metro-application`
- go to localhost:8080/products

### 2. Using IntelliJ:

- after cloning the project go into the root folder and execute `mvn clean install`
- from Edit Configurations, create new SpringBootApplication, point to the main class and run
- go to localhost:8080/products
