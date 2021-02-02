package one.goranson.bokker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

@Configuration
public class AwsConfigurator {

    @Value("${aws.s3.endpoint:http://localhost:4566}")
    private String awsS3Endpoint;
    @Value("${aws.s3.region:us-east-1}")
    private String awsS3Region;

    @Bean
    public AWSCredentials awsCredentials() {
        return new BasicAWSCredentials("foo", "bar");
    }

    @Bean
    public AmazonS3 amazonS3(AWSCredentials credentials) {
        return AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder
                                .EndpointConfiguration(awsS3Endpoint, awsS3Region))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    @Bean
    public AmazonSQS amazonSQSAsync(AWSCredentials credentials) {
        return AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder
                                .EndpointConfiguration(awsS3Endpoint, awsS3Region))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

}
