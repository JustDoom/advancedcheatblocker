package com.advancedcheatblocker.acb.pluginanticheat.checks.combats;

import com.advancedcheatblocker.acb.pluginanticheat.Main;
import com.advancedcheatblocker.acb.pluginanticheat.checks.CheckNames;
import com.advancedcheatblocker.acb.pluginanticheat.utils.ChecksManager;
import com.advancedcheatblocker.acb.pluginanticheat.utils.FlagUtil;
import com.advancedcheatblocker.acb.pluginanticheat.utils.PlayerUtil;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class KillAura implements Listener{
    public static double yaw = 0;
    public static double pitch = 0;
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

    @EventHandler
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent e) {
        if (/*e.getEntity() instanceof Player && */ e.getDamager() instanceof Player) {
            if(!PlayerUtil.canBypass(( (Player) e.getDamager() ).getPlayer() ) && ChecksManager.killaura){
                Player attack = (Player)e.getDamager();
                Entity receive = e.getEntity();
                Vector entloc = receive.getLocation().toVector();
                Vector damloc = attack.getLocation().toVector();
                Vector attackdir = entloc.subtract(damloc).setY(0).normalize();
                Vector hitdir = attack.getLocation().getDirection().setY(0).normalize();
                double angle = attackdir.angle(hitdir) / 6.283185307179586D * 360.0D;

                if(attack.getLocation().distance( receive.getLocation() ) > 1.8){
                    if (angle > 25) {
                        if(ka.get( attack) !=null){
                            ka.put( attack , ka.get(attack ) + 1 );
                            if(ka.get( attack ) >= 5){
                                ka.put( attack,0);
                                FlagUtil.sendFlag(attack , CheckNames.KillAura );
                                if(ChecksManager.killauraFlag){
                                    e.setCancelled( true);
                                }
                            }
                        }else{
                            ka.put( attack, 1 );
                            new BukkitRunnable() {
                                public void run() {
                                    ka.put( attack , null );
                                }
                            }.runTaskLater( Main.plugin, 20L);
                        }
                    }
                }
            }
        }
    }

    HashMap<Player,Integer>ka = new HashMap<>();
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player){
            Player damager = ( (Player) event.getDamager() ).getPlayer();
            if(!PlayerUtil.canBypass( damager ) && ChecksManager.killaura ){

                if(yaw >= 10 || pitch >= 10){
                    if(ka.get( damager ) !=null){
                        ka.put( damager , ka.get( damager ) + 1 );
                        if(ka.get( damager ) >= 5){
                            ka.put( damager ,0);
                            FlagUtil.sendFlag( damager , CheckNames.KillAura );
                            if(ChecksManager.killauraFlag){
                                event.setCancelled( true);
                            }
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
