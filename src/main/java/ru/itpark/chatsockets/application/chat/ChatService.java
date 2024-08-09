package ru.itpark.chatsockets.application.chat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.itpark.chatsockets.application.chat.contracts.ChatMapper;
import ru.itpark.chatsockets.application.chat.contracts.CreateChatCommand;
import ru.itpark.chatsockets.domain.Chat;
import ru.itpark.chatsockets.infra.repositories.ChatRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatMapper mapper;

    public List<Chat> getList() {
        return chatRepository.findAll();
    }

    public Chat getOne(UUID id) {
        Optional<Chat> chatOptional = chatRepository.findById(id);
        return chatOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
    }

    public Chat create(CreateChatCommand command) {
        final Chat entity = mapper.toEntity(command);

        return chatRepository.save(entity);
    }
}
