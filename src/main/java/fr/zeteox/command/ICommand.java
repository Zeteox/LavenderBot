package fr.zeteox.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface ICommand {
    String getName();
    String getDescription();
    void execute(MessageReceivedEvent event, String[] args);
}
