package fr.zeteox.command;

import fr.zeteox.BotConfig;
import fr.zeteox.util.EmbedHelper;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.messages.MessagePoll;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.utils.messages.MessagePollData;

import java.awt.*;
import java.time.Duration;
import java.util.List;

public class PollCommand implements ICommand {
    private MessageEmbed errorMessage = EmbedHelper.createEmbed(
            "Poll creation error",
            "Use the command like this: `" + BotConfig.PREFIX + "poll <subject> | <option 1> | <option 2>`",
            Color.RED);

    @Override
    public String getName() {
        return "poll";
    }

    @Override
    public String getDescription() {
        return "Create a poll, usage: \n`" +
                BotConfig.PREFIX + "poll <suject> | <option 1> | <option 2>`";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        if (args.length < 5) {
            event.getMessage().replyEmbeds(errorMessage).queue();
            return;
        }

        String fullInput = String.join(" ", args);
        String[] pollArgs = fullInput.split("\\|");

        for (int i = 0; i < pollArgs.length; i++) {
            pollArgs[i] = pollArgs[i].trim();
        }

        if (pollArgs.length != 3) {
            event.getMessage().replyEmbeds(errorMessage).queue();
            return;
        }

        event.getMessage().getChannel().sendMessagePoll(new MessagePollData(
                MessagePoll.LayoutType.DEFAULT,
                new MessagePoll.Question(pollArgs[0], null),
                List.of(
                        new MessagePoll.Answer(1, pollArgs[1], null, 0, false),
                        new MessagePoll.Answer(2, pollArgs[2], null, 0, false)
                ),
                Duration.ofHours(24),
                false
            )
        ).queue();
    }
}
