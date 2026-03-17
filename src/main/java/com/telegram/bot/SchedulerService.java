package com.telegram.bot;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

public class SchedulerService {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void scheduleDeletion(AbsSender sender, Long chatId, Integer messageId) {
        scheduler.schedule(() -> {
            try {
                DeleteMessage deleteMessage = new DeleteMessage(chatId.toString(), messageId);
                sender.execute(deleteMessage);
                System.out.println("Deleted message: " + messageId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, BotConfig.DELETE_DELAY, TimeUnit.SECONDS);
    }
}
