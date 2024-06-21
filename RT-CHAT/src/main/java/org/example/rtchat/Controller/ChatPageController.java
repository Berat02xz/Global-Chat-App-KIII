package org.example.rtchat.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatPageController {

    @GetMapping("/chat")
    public String getChatPage(Model model) {
        return "chat"; // Return the name of the view template (e.g., chat.html)
    }
}
