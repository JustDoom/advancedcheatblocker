package com.advancedcheatblocker.acb.pluginanticheat.checks.player;

import com.advancedcheatblocker.acb.pluginanticheat.Main;
import com.advancedcheatblocker.acb.pluginanticheat.checks.CheckNames;
import com.advancedcheatblocker.acb.pluginanticheat.utils.FlagUtil;
import com.advancedcheatblocker.acb.pluginanticheat.utils.PlayerUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Timer implements Listener {
    HashMap<Player,Integer>timer = new HashMap<>();
    HashMap<Player, Location>loc = new HashMap<>();
    HashMap<Player,Boolean>back = new HashMap<>();
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player p = event.getPlayer();
        if(! PlayerUtil.canBypass( p)){
            if(timer.get( p ) != null){
                timer.put(  p , 1 + timer.get( p ) );
                if(timer.get( p  ) > 20){
                    back.put( p , true );
                    FlagUtil.sendFlag( p, CheckNames.Timer );
                }
            }else{
                if(back.get( p  ) !=null && back.get( p )){
                    if(loc.get( p  ) !=null){
                        p.teleport( loc.get( p ) );
                    }
                }

                timer.put( p , 0 );
                back.put( p, false );
                loc.put( p, event.getFrom() );
                new BukkitRunnable() {
                    public void run() {
                        timer.put( p, null );
                        back.put( p , null );
                        loc.put( p , null );
                    }
                }.runTaskLater( Main.plugin, 20L);
            }
        }
    }
}
