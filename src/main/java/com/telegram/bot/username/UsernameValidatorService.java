package com.telegram.bot.username;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class UsernameValidatorService {

    public void handleUsernameValidation(Update update, AbsSender sender) {
        if (update.hasMessage() && update.getMessage().getNewChatMembers() != null) {
            update.getMessage().getNewChatMembers().forEach(user -> {

                String name = user.getFirstName();
                if (user.getLastName() != null) {
                    name += " " + user.getLastName();
                }

                if (isInvalidName(name)) {
                    sendWarning(sender, update.getMessage().getChatId(), name);
                }
            });
        }
    }

    private boolean isInvalidName(String name) {
        if (name.length() < 3) return true;
        if (!name.matches(".*[a-zA-Z].*")) return true;
        return false;
    }

    private void sendWarning(AbsSender sender, Long chatId, String name) {
        try {
            SendMessage msg = new SendMessage();
            msg.setChatId(chatId.toString());
            msg.setText("⚠️ Suspicious name detected: " + name);

            sender.execute(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}