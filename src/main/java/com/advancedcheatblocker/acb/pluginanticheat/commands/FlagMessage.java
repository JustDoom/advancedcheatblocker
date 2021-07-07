package com.advancedcheatblocker.acb.pluginanticheat.commands;

import com.advancedcheatblocker.acb.pluginanticheat.utils.FlagUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlagMessage implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
        if(sender instanceof Player){
            Player player = ( (Player) sender ).getPlayer();
            if(player.hasPermission( "acb.flagmessage" )){
                if( FlagUtil.FlagMsg.get( player ) !=null ){
                    boolean a = !FlagUtil.FlagMsg.get( player );
                    if(a){
                        player.sendMessage( "§7[§cACB§7]§9 Flag Message is enabled. " );
                    }else{
                        player.sendMessage( "§7[§cACB§7]§9 Flag Message is disabled. " );
                    }
                    FlagUtil.FlagMsg.put( player ,a  );
                }else{
                    FlagUtil.FlagMsg.put( player,true );
                }
            }
        }
        return true;
    }
}
