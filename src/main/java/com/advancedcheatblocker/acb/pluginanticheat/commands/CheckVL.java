package com.advancedcheatblocker.acb.pluginanticheat.commands;

import com.advancedcheatblocker.acb.pluginanticheat.checks.CheckNames;
import com.advancedcheatblocker.acb.pluginanticheat.utils.FlagUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckVL implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
//        if(sender.hasPermission( "advancedcheatblocker.showvl" )){
//            if(args.length == 0){
//
//            }else if(args.length == 1){
//                if( Bukkit.getPlayer( args[0]) !=null){
//                    Player p = Bukkit.getPlayer( args[0]);
//
//                }else{
//                    sender.sendMessage( "§7[§cACB§7]§c The Player is offline!" );
//                }
//            }
//        }
        // make it please
        sender.sendMessage( "§cThe Developer is hard to working on this command. please wait! " );
        return true;
    }
}
