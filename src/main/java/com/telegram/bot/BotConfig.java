package com.telegram.bot;

public class BotConfig {
    public static final String BOT_TOKEN = System.getenv("BOT_TOKEN");
    public static final String BOT_NAME = System.getenv("BOT_NAME");

    // Time in seconds after which sticker will be deleted
    public static final int DELETE_DELAY = 60;
}