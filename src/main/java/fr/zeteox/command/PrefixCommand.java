package fr.zeteox.command;

import fr.zeteox.BotConfig;
import fr.zeteox.util.EmbedHelper;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

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

       try {
           updateEnvPrefix(args[0]);
           logger.info("Changin prefix to " + BotConfig.PREFIX);
           event.getMessage().reply("Prefix changed !!! New prefix: " + BotConfig.PREFIX).queue();
       } catch (Exception e) {
           event.getMessage().replyEmbeds(EmbedHelper.createEmbed("Prefix change error", "Could'nt change the prefix.", Color.RED)).queue();
       }
    }

    private void updateEnvPrefix(String newPrefix) throws IOException {
        File envFile = new File(".env");
        List<String> lines = Files.readAllLines(envFile.toPath());

        boolean found = false;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith("PREFIX=")) {
                lines.set(i, "PREFIX=" + newPrefix);
                found = true;
                break;
            }
        }
        if (!found) {
            lines.add("PREFIX=" + newPrefix);
        }

        Files.write(envFile.toPath(), lines);

        BotConfig.PREFIX = newPrefix;
    }
}
