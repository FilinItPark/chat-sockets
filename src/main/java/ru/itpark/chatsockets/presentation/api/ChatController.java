package ru.itpark.chatsockets.presentation.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itpark.chatsockets.application.chat.ChatService;
import ru.itpark.chatsockets.application.chat.contracts.CreateChatCommand;
import ru.itpark.chatsockets.domain.Chat;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chats")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping
    public List<Chat> getList() {
        return chatService.getList();
    }


    @GetMapping("/{id}")
    public Chat getOne(@PathVariable UUID id) {
        return chatService.getOne(id);
    }

    @PostMapping
    public Chat create(@RequestBody CreateChatCommand chat) {
        return chatService.create(chat);
    }
}
