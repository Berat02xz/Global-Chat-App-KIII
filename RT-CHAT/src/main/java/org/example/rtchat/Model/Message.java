package org.example.rtchat.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(name = "message") // This maps to the 'message' column in the database
    private String content; // Renamed from 'message' to 'content' to match frontend expectations
    private LocalDateTime timestamp;

    @Column(name = "replied")
    private Long repliedMessageId; // Store the ID of the message being replied to


    public Message() {
    }

    public Message(String username, String content, LocalDateTime timestamp) {
        this.username = username;
        this.content = content;
        this.timestamp = timestamp;
    }

}
