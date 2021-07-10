package com.advancedcheatblocker.acb.pluginanticheat.checks.combats;

import com.advancedcheatblocker.acb.pluginanticheat.Main;
import com.advancedcheatblocker.acb.pluginanticheat.checks.CheckNames;
import com.advancedcheatblocker.acb.pluginanticheat.utils.FlagUtil;
import com.advancedcheatblocker.acb.pluginanticheat.utils.PlayerUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class KillAura implements Listener{
    double yaw = 0;
    double pitch = 0;
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player p = event.getPlayer();
        if(! PlayerUtil.canBypass( p )){
            double yf = event.getFrom().getYaw();
            double yt = event.getTo().getYaw();
            double pf = event.getTo().getPitch();
            double pt = event.getFrom().getPitch();
            if(yf < yt){
                yaw = yt - yf;
            }else if(yt < yf){
                yaw = yf - yt;
            }
            if(pf < pt){
                pitch = pt - pf;
            }else if(pt < pf){
                pitch = pf - pt;
            }
        }
    }
    HashMap<Player,Integer>ka = new HashMap<>();
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player){
            Player damager = ( (Player) event.getDamager() ).getPlayer();
            if(!PlayerUtil.canBypass( damager )){

                if(yaw >= 10 || pitch >= 10){
                    if(ka.get( damager ) !=null){
                        ka.put( damager , ka.get( damager ) + 1 );
                        if(ka.get( damager ) >= 5){
                            ka.put( damager ,0);
                            FlagUtil.sendFlag( damager , CheckNames.KillAura );
                        }
                    }else{
                        ka.put( damager , 1 );
                        new BukkitRunnable() {
                            public void run() {
                                ka.put( damager , null );
                            }
                        }.runTaskLater( Main.plugin, 20L);
                    }
                }
            }
        }
    }
}
