package com.iesvi.chatbackend.domain.repository;

import com.iesvi.chatbackend.domain.model.ChatMessage;
import com.iesvi.chatbackend.domain.model.MessageStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository
        extends MongoRepository<ChatMessage, String> {

    long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);
}