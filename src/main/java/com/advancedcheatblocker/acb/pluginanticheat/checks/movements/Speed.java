package com.advancedcheatblocker.acb.pluginanticheat.checks.movements;

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

public class Speed implements Listener{
    private double lastDisc;
    private boolean lastOnGround;
    @EventHandler
    public void onMoveSpeed(PlayerMoveEvent event){
        Player p = event.getPlayer();
        if(!PlayerUtil.canBypass( p ) && ChecksManager.speedc ){
            double diatX = event.getTo().getX() - event.getFrom().getX();
            double diatZ = event.getTo().getZ() - event.getFrom().getZ();
            double dist = (diatX * diatX) + (diatZ * diatZ);
            double disa = event.getTo().distance( event.getFrom( ) );
            double lastDiat = this.lastDisc;
            this.lastDisc = dist;

            boolean onGround = p.isOnGround();
            boolean lastOnGround = this.lastOnGround;
            this.lastOnGround = onGround;

            float friction  = 0.91F;
            double ahLastDist = lastDiat * friction;
            double equaIneaa = dist - ahLastDist;
            double acEq = equaIneaa * 138;
            if(!onGround && !lastOnGround && !GroundChecker.isOnGroundMath( event.getTo().getY() ) && disa > 0.54){
                if(acEq >= 1.0  && !p.isFlying() ){
                    FlagUtil.sendFlag( p,CheckNames.SpeedC);
                    if(ChecksManager.speedcFlag){
                        event.setTo( event.getFrom() );
                    }
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player p = event.getPlayer( );
        if(!PlayerUtil.canBypass( p ) && ChecksManager.speeda){
            double x = p.getVelocity().getX();
            double z = p.getVelocity().getZ();
            if(!GroundChecker.isGroundAround2( event.getTo( ) ) )return;
            Location StandingBlock = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() - 1.0D, p.getLocation().getZ());
            if((x >= 0.24 || z >= 0.24) && !(StandingBlock.getBlock().getType() == Material.ICE || StandingBlock.getBlock().getType().getData().getName().contains("ICE"))) {
                FlagUtil.sendFlag( p,CheckNames.SpeedA);
                if(ChecksManager.speedaFlag){
                    event.setTo( event.getFrom() );
                }
            }
        }

    }
    @EventHandler
    public void onMove2(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if( !PlayerUtil.canBypass( p ) && ChecksManager.speedb){
            double speed = e.getTo().distance( e.getFrom() );
            double speedWalk = p.getWalkSpeed();
            if(! GroundChecker.isOnGroundMath( e.getTo().getY() ) && !GroundChecker.isOnGroundMath( e.getFrom().getY() )){
                speedWalk = p.getFlySpeed();
            }

            //p.sendMessage( "speed: "+speed+"\nWalkSpeed: "+speedWalk );

            if(e.getTo().getY() == e.getFrom().getY() && speed > speedWalk+0.5 && !p.isFlying()){
                FlagUtil.sendFlag( p,CheckNames.SpeedB );
                if(ChecksManager.speedbFlag){
                    e.setTo( e.getFrom() );
                }
            }
        }
    }
}
