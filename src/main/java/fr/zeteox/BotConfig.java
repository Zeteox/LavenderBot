package fr.zeteox;

import io.github.cdimascio.dotenv.Dotenv;

public class BotConfig {
    public final static String TOKEN = Dotenv.load().get("TOKEN");
    public static String PREFIX = Dotenv.load().get("PREFIX") != null ? Dotenv.load().get("PREFIX") : "!";
    public final static String JOKE_API_URL = "https://official-joke-api.appspot.com/random_joke";
}
