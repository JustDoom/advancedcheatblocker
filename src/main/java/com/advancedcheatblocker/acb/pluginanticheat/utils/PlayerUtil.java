package com.advancedcheatblocker.acb.pluginanticheat.utils;

import com.advancedcheatblocker.acb.pluginanticheat.Main;
import org.bukkit.entity.Player;
import java.util.HashMap;
public class PlayerUtil {
    public static HashMap<Player,Boolean>BypassMap = new HashMap<>();
    public static boolean canBypass(Player p){
        if( Main.allowbypass && p.hasPermission( "acb.bypass" )){
            return true;
        }else{
            if(BypassMap.get( p) !=null){
                return BypassMap.get( p );
            }else{
                BypassMap.put( p,false );
                return false;
            }
        }
    }
    public static void setBypass(Player p,boolean a){
        BypassMap.put( p,a );
    }
}
