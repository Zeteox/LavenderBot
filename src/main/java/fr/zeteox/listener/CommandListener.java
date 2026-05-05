package fr.zeteox.listener;

import fr.zeteox.BotConfig;
import fr.zeteox.command.CommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener  extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if (event.getMessage().getContentRaw().startsWith(BotConfig.PREFIX)) {
            CommandManager commandManager = CommandManager.getInstance();
            commandManager.dispatch(event);
        }
    }
}
