# Dacke dev-stack
A dev-stack using Spring Boot, MySQL, Redis, Graylog, Prometheus, Grafana, ElasticSearch, 
AWS: S3, SQS, SNS, Kinesis (using localstack)

## Bokker
A Spring Boot application
Host: `localhost:8080`

## Logs

**Graylog**  
Host: `127.0.0.1:9000`  
Username: `admin`  
Password: `admin`  
Create a new input using the GELF TCP and default values  
This will also run an ElasticSearch and MongoDB container

Resources: https://dzone.com/articles/spring-boot2-graylog

## Database

**MySQL**  
Host: `localhost:3306`  
Database: `bokkerdb`  
Username: `bokker`  
Password: `bokkerpw`   

## Monitoring
**Prometheus**    
Host: `localhost:9090`  

**Grafana**  
Host: `localhost:3000`  
Username: `admin`  
Password: `admin` <-- first time password
    
Resources: https://github.com/Einsteinish/Docker-Compose-Prometheus-and-Grafana  

## Redis ##
Host: `localhost:6379`

## Localstack
https://github.com/localstack/localstack
Setup guide: https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2-mac.html
https://www.linkedin.com/pulse/using-localstack-test-sqs-sns-fabio-palumbo/?articleId=6653638519531073536

Example:
Create SNS topic:  
`aws --endpoint-url=http://localhost:4566 sns create-topic --name my_topic`    
`aws --endpoint-url=http://localhost:4566 sns list-topics`  

Create SQS queue:  
`aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name jobs`  
`aws --endpoint-url=http://localhost:4566 sqs list-queues`  

Create S3 bucket:  
`aws --endpoint-url=http://localhost:4566 s3api create-bucket --bucket my-bucket --region us-east-1`   
`aws --endpoint-url=http://localhost:4566 s3api list-buckets`  

Dashboard (deprecated)  
`http://localhost:8082/#!/infra`