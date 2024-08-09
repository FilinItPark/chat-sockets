package ru.itpark.chatsockets.application.chat.contracts;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * DTO for {@link ru.itpark.chatsockets.domain.Chat}
 */
@Data
public class CreateChatCommand {
    private String title;
    private String description;
}