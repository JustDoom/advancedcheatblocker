package com.advancedcheatblocker.acb.pluginanticheat.commands;

import com.advancedcheatblocker.acb.pluginanticheat.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ACB implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
        sender.sendMessage( "§cACB§7(§cAdvancedCheatBlocker§7)§9 is new Anti-Cheat for Minecraft 1.8.x Spigot!\n§eVer: "+ Main.version );

        return true;
    }
}
