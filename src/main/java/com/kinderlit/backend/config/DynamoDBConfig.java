package com.kinderlit.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {

    @Value("${AWS_ACCESS_KEY_ID}")
    private String dynamodbAccessKey;

    @Value("${AWS_SECRET_ACCESS_KEY}")
    private String dynamodbSecretKey;

    @Bean
    public DynamoDbEnhancedClient  dynamoDbEnhancedClient() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(dynamodbAccessKey,dynamodbSecretKey);
        StaticCredentialsProvider staticCredentials = StaticCredentialsProvider.create(credentials);
        Region region = Region.US_WEST_1;
        DynamoDbClient ddb = DynamoDbClient.builder()
                .region(region)
                .credentialsProvider(staticCredentials)
                .build();

        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(ddb)
                .build();
    }
}
