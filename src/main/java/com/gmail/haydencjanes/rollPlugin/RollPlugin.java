package com.gmail.haydencjanes.rollPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RollPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    private int rollDie(int number) {
        return (int) (Math.random() * number) + 1;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("roll")) {
            int number = 0;
            if (args.length == 1) {
                try {
                    number = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED + "Invalid! Roll a proper number.");
                    return true;
                }
                if (number >= 1) {
                    int rollDie = rollDie(number);
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        for (Entity entity : player.getNearbyEntities(10, 10, 10)) {
                            if (entity instanceof Player) {
                                Player players = (Player) entity;
                                players.sendMessage(ChatColor.GOLD + player.getDisplayName() + " rolled " + ChatColor.GREEN + rollDie + ChatColor.YELLOW + " (out of " + number + ")");
                            }
                        } // End of 'FOR LOOP'
                    }
                    sender.sendMessage(ChatColor.GOLD + "You rolled " + ChatColor.GREEN + rollDie + ChatColor.YELLOW + " (out of " + number + ")");
                    return true;
                } else {
                    sender.sendMessage(ChatColor.RED + "Invalid! Your number must be positive.");
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid! Must have a single argument /roll #");
                return true;
            }
        } else {
            return false;
        }
    }
}
