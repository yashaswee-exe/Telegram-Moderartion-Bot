package com.telegram.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StickerCleanerBot extends TelegramLongPollingBot {

    private final SchedulerService schedulerService = new SchedulerService();

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasSticker()) {

            Long chatId = update.getMessage().getChatId();
            Integer messageId = update.getMessage().getMessageId();

            System.out.println("Sticker received. Scheduling deletion...");

            schedulerService.scheduleDeletion(this, chatId, messageId);
        }
    }

    @Override
    public String getBotUsername() {
        return BotConfig.STICKER_DELETE_BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.STICKER_DELETE_BOT_TOKEN;
    }
}
