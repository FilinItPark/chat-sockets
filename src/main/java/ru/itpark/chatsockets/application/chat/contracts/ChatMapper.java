package ru.itpark.chatsockets.application.chat.contracts;

import org.mapstruct.Mapper;
import ru.itpark.chatsockets.domain.Chat;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    Chat toEntity(CreateChatCommand createChatCommand);

    CreateChatCommand toDto(Chat chat);

    @org.mapstruct.BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    Chat partialUpdate(CreateChatCommand createChatCommand, @org.mapstruct.MappingTarget Chat chat);
}