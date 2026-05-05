package fr.zeteox;

import fr.zeteox.command.CommandManager;
import fr.zeteox.listener.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    void main (String[] args) {
        JDA api = JDABuilder.createDefault(BotConfig.TOKEN).enableIntents(GatewayIntent.MESSAGE_CONTENT).build(); ;
        System.out.println("----------- Loading Commands -----------");
        CommandManager.getInstance();
        System.out.println("----------- Loaded Commands -----------");
        api.addEventListener(new CommandListener());
    }
}
