package fr.zeteox;

import io.github.cdimascio.dotenv.Dotenv;

public class BotConfig {
    public static String TOKEN = Dotenv.load().get("TOKEN");
}
