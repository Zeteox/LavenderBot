package fr.zeteox.command;

import fr.zeteox.BotConfig;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JokeCommand implements ICommand {
    @Override
    public String getName() {
        return "joke";
    }

    @Override
    public String getDescription() {
        return "Reply a joke from the official Joke API";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(BotConfig.JOKE_API_URL))
                        .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject json = new JSONObject(response.body());
            event.getMessage().reply( json.getString("setup") + "..." + "\n" + json.getString("punchline") ).queue();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
