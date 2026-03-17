package com.telegram.bot;

import com.telegram.bot.sticker.DeleteStickerService;
import com.telegram.bot.username.UsernameValidatorService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBot extends TelegramLongPollingBot {

    private final DeleteStickerService schedulerService = new DeleteStickerService();
    private final UsernameValidatorService usernameValidator = new UsernameValidatorService();

    @Override
    public void onUpdateReceived(Update updateFromTelegramMessenger) {
        if (!updateFromTelegramMessenger.hasMessage()) {
            return;
        }

        checkBotStatus(updateFromTelegramMessenger);
        handleNewMembers(updateFromTelegramMessenger);
        handleSticker(updateFromTelegramMessenger);
    }

    private void checkBotStatus(Update update) {
        if (!update.getMessage().hasText()) {
            return;
        }

        String text = update.getMessage().getText();

        if ("/status@Groups_Moderation_Bot".equalsIgnoreCase(text)) {
            sendActiveStatus(update.getMessage().getChatId());
        } else {

        }
    }

    private void sendActiveStatus(Long chatId) {
        try {
            SendMessage message = new SendMessage();
            message.setChatId(chatId.toString());
            message.setText("✅ Bot is running");

            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleNewMembers(Update update) {
        usernameValidator.handleUsernameValidation(update, this);
    }

    private void handleSticker(Update update) {
        if (!update.getMessage().hasSticker()) {
            return;
        }
        Long chatId = update.getMessage().getChatId();
        Integer messageId = update.getMessage().getMessageId();
        System.out.println("Sticker received. Scheduling deletion...");
        schedulerService.scheduleDeletion(this, chatId, messageId);
    }

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }
}
