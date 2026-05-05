package fr.zeteox;

import io.github.cdimascio.dotenv.Dotenv;

import java.net.URL;

public class BotConfig {
    public static String TOKEN = Dotenv.load().get("TOKEN");
    public static String PREFIX = "!";
    public static String JOKE_API_URL = "https://official-joke-api.appspot.com/random_joke";
}
