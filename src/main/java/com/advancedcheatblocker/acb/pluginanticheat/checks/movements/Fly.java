package com.advancedcheatblocker.acb.pluginanticheat.checks.movements;

import com.advancedcheatblocker.acb.pluginanticheat.Main;
import com.advancedcheatblocker.acb.pluginanticheat.checks.CheckNames;
import com.advancedcheatblocker.acb.pluginanticheat.utils.FlagUtil;
import com.advancedcheatblocker.acb.pluginanticheat.utils.GroundChecker;
import com.advancedcheatblocker.acb.pluginanticheat.utils.PlayerUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Fly implements Listener{
    HashMap<Player,Integer>FlyE = new HashMap<>();

    @EventHandler
    public void onMove5(PlayerMoveEvent event){
        Player p = event.getPlayer();
        if(!PlayerUtil.canBypass( p )){
            Location from = event.getFrom();
            Location to = event.getTo();
            boolean isGround = GroundChecker.isOnGroundAround( from ) && GroundChecker.isOnGroundAround( to );
            if(to.getY() == from.getY()){
                if(!isGround){
                    if(FlyE.get( p ) !=null){
                        FlyE.put( p , FlyE.get( p ) + 1 );
                        if(FlyE.get( p ) >= 5){
                            FlyE.put( p , 0 );
                            FlagUtil.sendFlag( p , CheckNames.FlyE );
                        }
                    }else{
                        FlyE.put( p , 0 );
                        new BukkitRunnable() {
                            public void run() {
                                FlyE.put( p, null );
                            }
                        }.runTaskLater( Main.plugin, 20L);
                    }
                }
            }
            if(to.getY()+0.15D < from.getY()){
                if(p.isOnGround() && p.getVelocity().getY() >= 0.26){
                    if(FlyE.get( p ) !=null){
                        FlyE.put( p , FlyE.get( p ) + 1 );
                        if(FlyE.get( p ) >= 5){
                            FlyE.put( p , 0 );
                            FlagUtil.sendFlag( p , CheckNames.FlyE );
                        }
                    }else{
                        FlyE.put( p , 0 );
                        new BukkitRunnable() {
                            public void run() {
                                FlyE.put( p, null );
                            }
                        }.runTaskLater( Main.plugin, 20L);
                    }
                }
            }
        }
    }


    @EventHandler
    public void onMove4(PlayerMoveEvent event){
        Player p = event.getPlayer();
        if(!PlayerUtil.canBypass( p )){
            boolean isAG = GroundChecker.isOnGroundAround( event.getTo( ) );
            //boolean isMG = GroundChecker.isOnGroundMath( event.getTo().getY() ); not using rn for more reasons
            boolean Ground = isAG;
            if(!Ground){
                if(!p.isOnGround()){
                    double dist = event.getTo().distance( event.getFrom() );
                    if(dist >= 0.05D && p.getVelocity().getY() >= 0.26){

                        if(FlyE.get( p ) !=null){
                            FlyE.put( p , FlyE.get( p ) + 1 );
                            if(FlyE.get( p ) >= 5){
                                FlyE.put( p , 0 );
                                FlagUtil.sendFlag( p , CheckNames.FlyE );
                            }
                        }else{
                            FlyE.put( p , 0 );
                            new BukkitRunnable() {
                                public void run() {
                                    FlyE.put( p, null );
                                }
                            }.runTaskLater( Main.plugin, 20L);
                        }


                    }
                }else {
                    double dist = event.getTo().distance( event.getFrom() ) + 0.0987D;
                    if(dist >= 0.05D && p.getVelocity().getY() >= 0.26){
                        if(FlyE.get( p ) !=null){
                            FlyE.put( p , FlyE.get( p ) + 1 );
                            if(FlyE.get( p ) >= 5){
                                FlyE.put( p , 0 );
                                FlagUtil.sendFlag( p , CheckNames.FlyE );
                            }
                        }else{
                            FlyE.put( p , 0 );
                            new BukkitRunnable() {
                                public void run() {
                                    FlyE.put( p, null );
                                }
                            }.runTaskLater( Main.plugin, 20L);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player p = event.getPlayer( );
        if(!PlayerUtil.canBypass( p )){
            double v = Math.abs( p.getVelocity().getY() );
            if(p.getVehicle() == null ){
                if(v >= 0.26 && event.getFrom().getY() == event.getTo( ).getY() &&
                        !GroundChecker.isOnGroundAround( event.getTo( ) ) && !GroundChecker.isOnGroundMath( event.getTo( ).getY() )){
                    FlagUtil.sendFlag( p, CheckNames.FlyA);
                }
            }
        }
    }
    @EventHandler
    public void onMove1(PlayerMoveEvent event){
        Player p = event.getPlayer( );
        if(!PlayerUtil.canBypass( p )){
            double v = Math.abs( p.getVelocity().getY() );
            double y = event.getFrom().getY() - event.getTo().getY();
            if(p.getVehicle() == null ){
                if(v >= 0.26 &&
                        !GroundChecker.isOnGroundAround( event.getTo( ) ) && !GroundChecker.isOnGroundMath( event.getTo( ).getY() ) ){
                    if(event.getFrom().getY() > event.getTo( ).getY()){
                        if(y < 0.15D){
                            FlagUtil.sendFlag( p, CheckNames.FlyB);
                        }
                    }

                }
            }
        }
    }
    double lastYDown = 0;
    @EventHandler
    public void onMove3(PlayerMoveEvent event) {
        Player p = event.getPlayer( );
        if ( ! PlayerUtil.canBypass( p ) ) {
            double fromY = event.getFrom( ).getY();
            double toY = event.getTo().getY();
            if(fromY < toY){
                double data = toY - fromY;
                if(!(data <= 6.65D)){
                    if(!GroundChecker.isOnGroundMath( toY ) && !GroundChecker.isOnGroundMath( fromY )){
                        FlagUtil.sendFlag( p, CheckNames.FlyC);
                    }
                }
            }else if( toY < fromY){
                double data = toY - fromY;
                if(!GroundChecker.isOnGroundMath( toY ) && !GroundChecker.isOnGroundMath( fromY )){
                    if(data <= 0.124D){
                        double l = 0;
                        if(lastYDown > data){
                            l = lastYDown - data;
                        }else if(lastYDown < data ){
                            l = data - lastYDown;
                        }
                        if(!(lastYDown == 0)){
                            if(l < 0.02D && !GroundChecker.isOnGroundAround( event.getTo( ) )){
                                FlagUtil.sendFlag( p, CheckNames.FlyD);
                            }

                        }
                    }
                    lastYDown = data;
                }else if(!GroundChecker.isOnGroundAround( event.getTo( ) ) && !GroundChecker.isOnGroundAround( event.getFrom( ) )){
                    if(data <= 0.124D){
                        double l = 0;
                        if(lastYDown > data){
                            l = lastYDown - data;
                        }else if(lastYDown < data ){
                            l = data - lastYDown;
                        }
                        if(!(lastYDown == 0)){
                            if(l < 0.02D){
                                FlagUtil.sendFlag( p, CheckNames.FlyD);
                            }

                        }
                    }
                    lastYDown = data;
                }

            }
        }
    }
}
