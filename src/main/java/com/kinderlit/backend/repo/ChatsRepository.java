package com.kinderlit.backend.repo;

import com.kinderlit.backend.config.DynamoDBConfig;
import com.kinderlit.backend.entity.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ChatsRepository {

        @Autowired
        public DynamoDbEnhancedClient dynamoDbEnhancedClient;

        public Chat saveChat(Chat chat) {
            try {
                DynamoDbTable<Chat> chatTable = dynamoDbEnhancedClient.table("devchats", TableSchema.fromBean(Chat.class));
                chatTable.putItem(chat);
            } catch (DynamoDbException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
            return chat;
        }
}
