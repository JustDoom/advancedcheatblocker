package com.advancedcheatblocker.acb.pluginanticheat.utils;

import com.advancedcheatblocker.acb.pluginanticheat.Main;
import com.advancedcheatblocker.acb.pluginanticheat.checks.CheckNames;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class FlagUtil{
    public static HashMap<Player,Boolean>FlagMsg = new HashMap<>();
    public static HashMap<Player,Integer>ViolationsCount = new HashMap<>();

    //Please make it!

//    public static HashMap<Player,HashMap<CheckNames,Integer>>VL = new HashMap<>();

//    public static void WhenJoinCall(Player player){
//        HashMap<CheckNames,Integer>newmap = new HashMap<>();
//        VL.put( player , newmap );
//    }
//
//    public static Integer getVLofChecks(Player p,CheckNames cname){
//        return VL.get( p ).get( cname );
//    }
//
//    public static void WhenQuitCall(Player player){
//        VL.remove( player );
//    }



    public static void sendFlag(Player p,CheckNames cname){
        //VL.get( p ).put( cname ,  VL.get( p ).get( cname ) + 1 );
        if( Main.sendflagtoconsole){
            System.out.println( "[ACB] "+p.getName()+" has violated check "+cname );
        }
        for(Player ap:Bukkit.getOnlinePlayers()){
            if(FlagMsg.get( ap ) !=null && FlagMsg.get( ap)){
                ap.sendMessage(  "§7[§cACB§7]§9 "+p.getName()+"§f has violated check §c"+cname );

                if(Main.AutoPunish){
                    if(ViolationsCount.get( p ) !=null){
                        ViolationsCount.put( p , ViolationsCount.get( p ) + 1 );
                    }else {
                        ViolationsCount.put( p , 0 );
                    }
                    if(ViolationsCount.get(  p  ) !=null && ViolationsCount.get( p ) >= Main.PunishViolationsCount){
                        Bukkit.dispatchCommand( Bukkit.getConsoleSender() , Main.AutoPunishCmd.replace( "%%player%%" , p.getName() ) );
                    }
                }

            }
        }
    }
}
