package ru.itpark.chatsockets.presentation.hub;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.itpark.chatsockets.domain.Chat;
import ru.itpark.chatsockets.domain.Message;
import ru.itpark.chatsockets.infra.repositories.ChatRepository;
import ru.itpark.chatsockets.infra.repositories.MessageRepository;

import java.util.UUID;

@AllArgsConstructor
@Component
@CrossOrigin(origins = "*")
@Controller
public class MessageHub {

    private static final Logger log = LoggerFactory.getLogger(MessageHub.class);
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    @MessageMapping("/chat/{chatId}")
    @SendTo("/topic/messages/{chatId}")
    @CrossOrigin("*")
    public Message send(@DestinationVariable("chatId") UUID chatId, @Payload Message message) {
        log.debug("Get Message: {}", message);

        final Chat chat = chatRepository.findById(chatId).orElseThrow(EntityNotFoundException::new);

        message.setChat(chat);

        messageRepository.save(message);

        return message;
    }
}
