package fr.zeteox.command;

import fr.zeteox.BotConfig;
import fr.zeteox.util.EmbedHelper;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class PrefixCommand implements ICommand {

    Logger logger = LoggerFactory.getLogger(PrefixCommand.class);
    private MessageEmbed errorMessage = EmbedHelper.createEmbed(
            "Prefix change error",
            "Use the command like this: `" + BotConfig.PREFIX + "lavenderPrefix <newPrefix>`",
            Color.RED);

    @Override
    public String getName() {
        return "lavenderPrefix";
    }

    @Override
    public String getDescription() {
        return "Change the prefix, usage: \n`" +
                BotConfig.PREFIX + "lavenderPrefix <newPrefix>`";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
       if (args.length != 1) {
           event.getMessage().replyEmbeds(errorMessage).queue();
           return;
       }

        BotConfig.PREFIX = args[0];
        logger.info("Changin prefix to " + BotConfig.PREFIX);
       event.getMessage().reply("Prefix changed !!! New prefix: " + BotConfig.PREFIX).queue();
    }
}
