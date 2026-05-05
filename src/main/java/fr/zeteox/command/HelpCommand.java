package fr.zeteox.command;

import fr.zeteox.BotConfig;
import fr.zeteox.util.EmbedHelper;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class HelpCommand implements ICommand {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Return all available commands";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        event.getMessage().replyEmbeds(EmbedHelper.createEmbed("Commands list", getCommandList(), Color.MAGENTA)).queue();
    }

    private String getCommandList() {
        StringBuilder stringBuilder = new StringBuilder();
        String[] keys = CommandManager.getInstance().getCommands().keySet().toArray(new String[0]);
        for (int i = 0; i < CommandManager.getInstance().getCommands().size(); i++) {
            ICommand command = CommandManager.getInstance().getCommands().get(keys[i]);
            stringBuilder.append("**").append(BotConfig.PREFIX).append(command.getName()).append("** - ").append(command.getDescription()).append("\n");
        }

        return stringBuilder.toString();
    }
}
