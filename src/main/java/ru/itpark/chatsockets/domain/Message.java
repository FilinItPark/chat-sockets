package ru.itpark.chatsockets.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "messages")
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    private Chat chat;

    @Column(nullable = false)
    private LocalDate createdAt = LocalDate.now();
}
