package com.advancedcheatblocker.acb.pluginanticheat.checks.worlds;

import com.advancedcheatblocker.acb.pluginanticheat.checks.CheckNames;
import com.advancedcheatblocker.acb.pluginanticheat.checks.combats.KillAura;
import com.advancedcheatblocker.acb.pluginanticheat.utils.FlagUtil;
import com.advancedcheatblocker.acb.pluginanticheat.utils.PlayerUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class Scaffold implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Player p = event.getPlayer();
        if(! PlayerUtil.canBypass( p )){
            double yaw = KillAura.yaw;
            double pitch = KillAura.pitch;
            if(yaw >= 10 || pitch >= 10){
                //need to fix it :)
                event.setCancelled( true );
                FlagUtil.sendFlag( p , CheckNames.ScaffoldA);
            }
        }
    }
}
