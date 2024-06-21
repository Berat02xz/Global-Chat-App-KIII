package org.example.rtchat.Controller;

import org.example.rtchat.Model.Message;
import org.example.rtchat.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class MessageController {
    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessageController(MessageRepository messageRepository, SimpMessagingTemplate messagingTemplate) {
        this.messageRepository = messageRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping
    public String getChatPage(Model model) {
        List<Message> messages = messageRepository.findTop20ByOrderByTimestampDesc();
        model.addAttribute("messages", messages);
        return "chat";
    }

    @PostMapping("/send")
    @MessageMapping("/chat") // Handles WebSocket messages from "/app/chat"
    @SendTo("/topic/messages") // Sends messages to WebSocket topic "/topic/messages"
    public Message sendMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now());
        messageRepository.save(message);
        List<Message> messages = messageRepository.findTop20ByOrderByTimestampDesc();
        if (messages.size() > 20) {
            messageRepository.deleteAll(messages.subList(20, messages.size()));
        }
        return message;
    }
}
