package fr.zeteox;

import fr.zeteox.listener.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    void main (String[] args) {
        JDA api = JDABuilder.createDefault(BotConfig.TOKEN).enableIntents(GatewayIntent.MESSAGE_CONTENT).build(); ;
        api.addEventListener(new CommandListener());
    }
}
