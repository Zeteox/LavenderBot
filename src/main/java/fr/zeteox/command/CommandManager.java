package fr.zeteox.command;

import fr.zeteox.BotConfig;
import fr.zeteox.util.EmbedHelper;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    final static Logger logger = LoggerFactory.getLogger(CommandManager.class);
    private static CommandManager instance;
    private final Map<String, ICommand> commands = new HashMap<>();

    private CommandManager() {
        loadCommands();
    }

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;
    }

    private void loadCommands() {
        String packageName = "fr.zeteox.command";
        String packagePath = packageName.replace('.', '/');
        URL resource = Thread.currentThread().getContextClassLoader().getResource(packagePath);

        if (resource == null) {
            logger.error("Package introuvable : " + packageName);
            return;
        }

        File folder = new File(resource.getFile());
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".class"));

        if (files == null) {
            logger.error("Aucune commandes trouvée au chemin: " + resource.getFile());
            return;
        }

        for (File file : files) {
            String className = packageName + "." + file.getName().replace(".class", "");
            try {
                Class<?> pClass = Class.forName(className);

                if (pClass.isInterface()) continue;

                if (ICommand.class.isAssignableFrom(pClass)) {
                    ICommand command = (ICommand) pClass.getDeclaredConstructor().newInstance();
                    registerCommand(command);
                }
            } catch (Exception e) {
                logger.error("Impossible de charger : " + className);
                e.printStackTrace();
            }
        }
    }

    private void registerCommand(ICommand command) {
        commands.put(command.getName().toLowerCase(), command);
        logger.info("Commande enregistrée : " + command.getName());
    }

    public void dispatch(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();

        if (message.isBlank()) return;

        String[] parts = message.substring(BotConfig.PREFIX.length()).trim().split("\\s+");
        String commandName = parts[0].toLowerCase();

        String[] args = new String[parts.length - 1];
        System.arraycopy(parts, 1, args, 0, args.length);

        ICommand command = commands.get(commandName);
        if (command != null) {
            logger.info("Command used : " + commandName);
            command.execute(event, args);
        } else {
            logger.error("Unknown command : " + commandName);
            event.getMessage().replyEmbeds(EmbedHelper.createEmbed(
                    "Unknown command",
                    "La commande `" + commandName + "` n'est pas valide.\n" +
                    "**Utilise `!help` pour voir toutes les commandes disponibles**",
                    Color.RED
                )
            ).queue();
        }
    }

    public Map<String, ICommand> getCommands() {
        return commands;
    }
}