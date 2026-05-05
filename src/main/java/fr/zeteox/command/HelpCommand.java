package fr.zeteox.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class HelpCommand implements ICommand {
    @Override
    public String getName() {
        return "!help";
    }

    @Override
    public String getDescription() {
        return "Return an help message";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        MessageEmbed helpMessage = new EmbedBuilder().setColor(Color.MAGENTA).setTitle("Liste des commandes")
                .setDescription(getCommandList()).build();
        event.getMessage().replyEmbeds(helpMessage).queue();
    }

    private String getCommandList() {
        StringBuilder stringBuilder = new StringBuilder();
        String[] keys = CommandManager.getInstance().getCommands().keySet().toArray(new String[0]);
        for (int i = 0; i < CommandManager.getInstance().getCommands().size(); i++) {
            ICommand command = CommandManager.getInstance().getCommands().get(keys[i]);
            stringBuilder.append("**").append(command.getName()).append("** - ").append(command.getDescription()).append("\n");
        }

        return stringBuilder.toString();
    }
}
