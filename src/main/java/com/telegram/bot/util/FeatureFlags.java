package com.telegram.bot.util;

public class FeatureFlags {

    public static final boolean ENABLE_USERNAME_VALIDATION = getEnvFlag("FEATURE_USERNAME_VALIDATION", true);
    public static final boolean ENABLE_EMOJI_CLEANER = getEnvFlag("EMOJI_CLEANER", false);

    private static boolean getEnvFlag(String envVar, boolean defaultValue) {
        String value = System.getenv(envVar);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }
}
