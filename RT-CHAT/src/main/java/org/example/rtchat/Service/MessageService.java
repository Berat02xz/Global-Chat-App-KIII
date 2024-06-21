package org.example.rtchat.Service;

import org.example.rtchat.Model.Message;
import org.example.rtchat.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message getMessageById(Long id) {
        return messageRepository.findById(id).orElse(null); // Return the message if found, otherwise null
    }

    public List<Message> findLast10Messages() {
        return messageRepository.findTop10ByOrderByTimestampDesc();
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}
