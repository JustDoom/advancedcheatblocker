package com.advancedcheatblocker.acb.pluginanticheat.checks.worlds;

import com.advancedcheatblocker.acb.pluginanticheat.Main;
import com.advancedcheatblocker.acb.pluginanticheat.checks.CheckNames;
import com.advancedcheatblocker.acb.pluginanticheat.checks.combats.KillAura;
import com.advancedcheatblocker.acb.pluginanticheat.utils.ChecksManager;
import com.advancedcheatblocker.acb.pluginanticheat.utils.FlagUtil;
import com.advancedcheatblocker.acb.pluginanticheat.utils.PlayerUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Scaffold implements Listener {
    HashMap<Player,Integer>Scaffold = new HashMap<>();
    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Player p = event.getPlayer();
        if(! PlayerUtil.canBypass( p ) && ChecksManager.scaffold){
            double yaw = KillAura.yaw;
            double pitch = KillAura.pitch;
            if(yaw >= 180 || pitch >= 22){
                if(Scaffold.get( p ) != null){
                    Scaffold.put(  p , 1 + Scaffold.get( p ) );
                    if(Scaffold.get( p  ) >= 5){
                        FlagUtil.sendFlag( p , CheckNames.ScaffoldA);
                        if(ChecksManager.scaffoldFlag){
                            event.setCancelled( true );
                        }
                    }
                }else{

                    Scaffold.put( p , 0 );
                    new BukkitRunnable() {
                        public void run() {
                            Scaffold.put( p, null );
                        }
                    }.runTaskLater( Main.plugin, 20L);
                }

            }
        }
    }
}
