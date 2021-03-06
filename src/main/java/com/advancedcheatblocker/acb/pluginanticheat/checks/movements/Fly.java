package com.advancedcheatblocker.acb.pluginanticheat.checks.movements;

import com.advancedcheatblocker.acb.pluginanticheat.Main;
import com.advancedcheatblocker.acb.pluginanticheat.checks.CheckNames;
import com.advancedcheatblocker.acb.pluginanticheat.utils.ChecksManager;
import com.advancedcheatblocker.acb.pluginanticheat.utils.FlagUtil;
import com.advancedcheatblocker.acb.pluginanticheat.utils.GroundChecker;
import com.advancedcheatblocker.acb.pluginanticheat.utils.PlayerUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Fly implements Listener{




    @EventHandler
    public void onMove2(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(!PlayerUtil.canBypass( p ) && ChecksManager.flyi){
            Location to= e.getTo();
            Location from = e.getFrom();
            double yDiff = to.getY() - from.getY();
            double yResult = yDiff - p.getVelocity().getY() ;
            if(p.isFlying() && GroundChecker.isGroundAround2( to  ))return;
            if(yResult >= 0.419 && Math.abs( p.getVelocity().getY() ) > 0.419) {
                FlagUtil.sendFlag( p , CheckNames.FlyI );
                if(ChecksManager.flyi){
                    e.setTo(from);
                }
            }
        }
    }


    HashMap<Player,Integer>FlyE = new HashMap<>();

    @EventHandler
    public void onMoveH(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(!PlayerUtil.canBypass( p ) && ChecksManager.flyh){
            if(!GroundChecker.isOnGroundMath( e.getTo().getY() )
                    && !GroundChecker.isOnGroundMath( e.getFrom().getY() )
                    && p.isOnGround()){
                double dist = e.getTo().distance( e.getFrom() ) ;

                if(dist > 0.3 && (e.getFrom().getY() == e.getTo().getY() || e.getFrom().getY()+0.2 > e.getTo().getY() ) && p.getVelocity().getY() > 0.01) {
                    FlagUtil.sendFlag( p , CheckNames.FlyH );
                    if(ChecksManager.flyhFlag){
                        e.setTo( e.getFrom( ) );
                    }
                }

            }
        }
    }
    @EventHandler
    public void onMove5(PlayerMoveEvent event){
        Player p = event.getPlayer();
        if(!PlayerUtil.canBypass( p ) && ChecksManager.flyg){
            Location from = event.getFrom();
            Location to = event.getTo();
            //boolean isGround = GroundChecker.isOnGroundAround( from ) && GroundChecker.isOnGroundAround( to );
            boolean isGround = GroundChecker.isGroundAround2( from ) && GroundChecker.isGroundAround2( to );
            if(to.getY() == from.getY()){
                if(!isGround && !GroundChecker.isStandingBoat( p )){
                    if(FlyE.get( p ) !=null){
                        FlyE.put( p , FlyE.get( p ) + 1 );
                        if(FlyE.get( p ) >= 5){
                            FlyE.put( p , 0 );
                            FlagUtil.sendFlag( p , CheckNames.FlyG );
                            if(ChecksManager.flygFlag){
                                event.setTo( from );
                            }
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
            if(to.getY()+0.15D < from.getY() && !GroundChecker.isStandingBoat( p )){
                if(p.isOnGround() && p.getVelocity().getY() >= 0.26){
                    if(FlyE.get( p ) !=null){
                        FlyE.put( p , FlyE.get( p ) + 1 );
                        if(FlyE.get( p ) >= 5){
                            FlyE.put( p , 0 );
                            FlagUtil.sendFlag( p , CheckNames.FlyG );
                            if(ChecksManager.flygFlag){
                                event.setTo( from );
                            }
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
    public void onMoveNew2(PlayerMoveEvent event){

        if(!PlayerUtil.canBypass( event.getPlayer() ) && ChecksManager.flyf){
            boolean isFly =
                    event.getPlayer( ).isOnGround()
                            && event.getPlayer().getWorld().getBlockAt(event.getPlayer().getLocation().subtract(0, 1, 0)).getType() == Material.AIR
                            && event.getPlayer().getWorld().getBlockAt(event.getPlayer().getLocation().subtract(0, 2, 0)).getType() == Material.AIR
                            && !(GroundChecker.isOnGroundMath( event.getTo().getY() ) && GroundChecker.isGroundAround2( event.getTo( ) ))
                            && !event.getPlayer().isFlying()
                            && event.getTo() != event.getFrom()
                            && event.getTo().distance( event.getFrom( ) ) > 0.19
                            && (event.getTo().getY() == event.getFrom( ).getY() || event.getTo().getY()+0.6 > event.getFrom().getY() || event.getTo().getY()+ 0.4 < event.getFrom().getY() );
            if(isFly){
                Player p = event.getPlayer( );
                if(FlyE.get( p ) !=null){
                    FlyE.put( p , FlyE.get( p ) + 3 );
                    if(FlyE.get( p ) >= 5){
                        FlyE.put( p , 0 );
                        FlagUtil.sendFlag( p , CheckNames.FlyF );
                        if(ChecksManager.flyfFlag){
                            event.setTo( event.getFrom( ));
                        }
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

    @EventHandler
    public void onMove4(PlayerMoveEvent event){
        Player p = event.getPlayer();
        if(!PlayerUtil.canBypass( p ) && ChecksManager.flye){
            //boolean isAG = GroundChecker.isOnGroundAround( event.getTo( ) );
            boolean isAG =  GroundChecker.isGroundAround2( event.getTo() );
            //boolean isMG = GroundChecker.isOnGroundMath( event.getTo().getY() ); not using rn for more reasons
            boolean Ground = isAG;
            if(!Ground && !GroundChecker.isStandingBoat( p )){
                if(!p.isOnGround()){
                    double dist = event.getTo().distance( event.getFrom() );
                    if(dist >= 0.05D && p.getVelocity().getY() >= 0.26){

                        if(FlyE.get( p ) !=null){
                            FlyE.put( p , FlyE.get( p ) + 1 );
                            if(FlyE.get( p ) >= 5){
                                FlyE.put( p , 0 );
                                FlagUtil.sendFlag( p , CheckNames.FlyE );
                                if(ChecksManager.flyeFlag){
                                    event.setTo( event.getFrom() );
                                }
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
                                if(ChecksManager.flyeFlag){
                                    event.setTo( event.getFrom() );
                                }
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
    HashMap<Player,Integer>FlyA = new HashMap<>();
    HashMap<Player,Integer>FlyB = new HashMap<>();
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player p = event.getPlayer( );
        if(!PlayerUtil.canBypass( p ) && ChecksManager.flya ){
            double v = Math.abs( p.getVelocity().getY() );
            if(p.getVehicle() == null ){
                if(v >= 0.26 && event.getFrom().getY() == event.getTo( ).getY() &&
                        !GroundChecker.isOnGroundAround( event.getTo( ) ) ){
                    //&& !GroundChecker.isOnGroundMath( event.getTo( ).getY() ) <= delete it why = bad for ground check

                    if(FlyA.get( p ) !=null){
                        FlyA.put( p , FlyA.get( p ) + 1 );
                        if(FlyA.get( p ) >= 5){
                            FlyA.put( p , 0 );
                            FlagUtil.sendFlag( p, CheckNames.FlyA);
                            if(ChecksManager.flyaFlag){
                                event.setTo( event.getFrom() );
                            }
                        }
                    }else{
                        FlyA.put( p , 0 );
                        new BukkitRunnable() {
                            public void run() {
                                FlyA.put( p, null );
                            }
                        }.runTaskLater( Main.plugin, 20L);
                    }


                }
            }
        }
    }
    @EventHandler
    public void onMove1(PlayerMoveEvent event){
        Player p = event.getPlayer( );
        if(!PlayerUtil.canBypass( p ) && ChecksManager.flyb){
            double v = Math.abs( p.getVelocity().getY() );
            double y = event.getFrom().getY() - event.getTo().getY();
            if(p.getVehicle() == null ){
                if(v >= 0.26 &&
                        !GroundChecker.isOnGroundAround( event.getTo( ) ) && !GroundChecker.isOnGroundMath( event.getTo( ).getY() ) ){
                    if(event.getFrom().getY() > event.getTo( ).getY() && !p.isFlying()){
                        if(y < 0.15D){

                            if(FlyB.get( p ) !=null){
                                FlyB.put( p , FlyB.get( p ) + 1 );
                                if(FlyB.get( p ) >= 5){
                                    FlyB.put( p , 0 );
                                    FlagUtil.sendFlag( p, CheckNames.FlyB);
                                    if(ChecksManager.flybFlag){
                                        event.setTo( event.getFrom() );
                                    }
                                }
                            }else{
                                FlyB.put( p , 0 );
                                new BukkitRunnable() {
                                    public void run() {
                                        FlyB.put( p, null );
                                    }
                                }.runTaskLater( Main.plugin, 20L);
                            }

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
                        if(ChecksManager.flyc){
                            FlagUtil.sendFlag( p, CheckNames.FlyC);
                            if(ChecksManager.flycFlag){
                                event.setTo( event.getFrom() );
                            }
                        }

                    }
                }
            }else if( toY < fromY){
                double data = toY - fromY;
                if(!GroundChecker.isOnGroundMath( toY ) && !GroundChecker.isOnGroundMath( fromY )  && !p.isFlying() && !GroundChecker.isStandingBoat( p )){
                    if(data <= 0.124D){
                        double l = 0;
                        if(lastYDown > data){
                            l = lastYDown - data;
                        }else if(lastYDown < data ){
                            l = data - lastYDown;
                        }
                        if(!(lastYDown == 0)){
                            if(l < 0.02D && !GroundChecker.isOnGroundAround( event.getTo( ) )){
                                if(ChecksManager.flyd){
                                    FlagUtil.sendFlag( p, CheckNames.FlyD);
                                    if(ChecksManager.flydFlag){
                                        event.setTo( event.getFrom() );
                                    }
                                }
                            }

                        }
                    }
                    lastYDown = data;
                }else if(!GroundChecker.isOnGroundAround( event.getTo( ) ) && !GroundChecker.isOnGroundAround( event.getFrom( ) )  && !p.isFlying() && !GroundChecker.isStandingBoat( p )){
                    if(data <= 0.124D){
                        double l = 0;
                        if(lastYDown > data){
                            l = lastYDown - data;
                        }else if(lastYDown < data ){
                            l = data - lastYDown;
                        }
                        if(!(lastYDown == 0)){
                            if(l < 0.02D){
                                if(ChecksManager.flyd){
                                    FlagUtil.sendFlag( p, CheckNames.FlyD);
                                    if(ChecksManager.flydFlag){
                                        event.setTo( event.getFrom() );
                                    }
                                }
                            }

                        }
                    }
                    lastYDown = data;
                }

            }
        }
    }
}
