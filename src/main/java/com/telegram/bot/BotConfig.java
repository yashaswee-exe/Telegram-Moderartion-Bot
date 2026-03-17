package com.telegram.bot;

public class BotConfig {
    public static final String STICKER_DELETE_BOT_TOKEN = System.getenv("STICKER_DELETE_BOT_TOKEN");
    public static final String STICKER_DELETE_BOT_NAME = System.getenv("STICKER_DELETE_BOT_NAME");

    // Time in seconds after which sticker will be deleted
    public static final int DELETE_DELAY = 60;
}