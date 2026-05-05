package fr.zeteox.command;

import fr.zeteox.BotConfig;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PingCommand implements ICommand {
    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getDescription() {
        return "Respond to the ping command with a... pong?";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        event.getMessage().reply( "Les lavandes (Lavandula en latin) sont un genre de plantes à fleurs de la famille des Lamiaceae[1],[2], anciennement nommées Labiées.\n" +
                "\n" +
                "Ce sont des arbrisseaux dicotylédones, à fleurs le plus souvent mauves ou violettes disposées en épis, dont la plupart des espèces très odorantes, sont largement utilisées dans toutes les branches de la parfumerie, en particulier le lavandin (Lavandula ×intermedia). Elles poussent surtout sur les sols calcaires secs et ensoleillés, à l'exception de Lavandula stoechas, qui préfère les sols siliceux.\n" +
                "\n" +
                "Toutes les lavandes sont des plantes mellifères, c'est-à-dire très recherchées par les abeilles. (Pong!)").queue();
    }
}
