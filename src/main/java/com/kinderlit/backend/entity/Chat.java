package com.kinderlit.backend.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;


@DynamoDbBean
public class Chat {
    private String userId;
    private Long timestamp;
    private String role;

    private String content;

    @DynamoDbPartitionKey
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDbSortKey
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @DynamoDbAttribute("role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @DynamoDbAttribute("content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
