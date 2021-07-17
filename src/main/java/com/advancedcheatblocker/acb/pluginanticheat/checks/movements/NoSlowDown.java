package com.advancedcheatblocker.acb.pluginanticheat.checks.movements;

import com.advancedcheatblocker.acb.pluginanticheat.checks.CheckNames;
import com.advancedcheatblocker.acb.pluginanticheat.utils.FlagUtil;
import com.advancedcheatblocker.acb.pluginanticheat.utils.PlayerUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class NoSlowDown implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(! PlayerUtil.canBypass( e.getPlayer()) ){
            if(e.getPlayer().isSprinting() && e.getPlayer().isBlocking()){
                FlagUtil.sendFlag( e.getPlayer() , CheckNames.NoSlowDown);
            }
            if(e.getPlayer().isSneaking() && e.getPlayer().isSprinting()){
                FlagUtil.sendFlag( e.getPlayer() , CheckNames.NoSlowDown);
                

            }
        }
    }
}
