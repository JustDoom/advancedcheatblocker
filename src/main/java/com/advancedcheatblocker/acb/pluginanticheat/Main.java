package com.advancedcheatblocker.acb.pluginanticheat;

import com.advancedcheatblocker.acb.pluginanticheat.checks.movements.Fly;
import com.advancedcheatblocker.acb.pluginanticheat.checks.movements.NoSlowDown;
import com.advancedcheatblocker.acb.pluginanticheat.checks.movements.Speed;
import com.advancedcheatblocker.acb.pluginanticheat.checks.movements.Step;
import com.advancedcheatblocker.acb.pluginanticheat.checks.player.NoFall;
import com.advancedcheatblocker.acb.pluginanticheat.checks.player.Timer;
import com.advancedcheatblocker.acb.pluginanticheat.commands.ACB;
import com.advancedcheatblocker.acb.pluginanticheat.commands.CheckVL;
import com.advancedcheatblocker.acb.pluginanticheat.commands.FlagMessage;
import com.advancedcheatblocker.acb.pluginanticheat.utils.FlagUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin implements Listener{
    public static boolean AntiCheatStatus = true;
    public static String version = "b0.2";
    public static boolean allowbypass = false;
    public static boolean sendflagtoconsole = true;
    public static FileConfiguration config;
    public static Plugin plugin;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        if( !Bukkit.getVersion( ).contains( "1.8" )){
            System.out.println( "The Plugin is only tested in 1.8.x!" );
        }
        allowbypass = config.getBoolean( "allow-bypass" );
        sendflagtoconsole = config.getBoolean( "send-flag-messaage-to-console" );
        plugin = this;

        register( this );
        register( new Fly() );
        register( new Speed() );
        register( new NoSlowDown() );
        register( new NoFall() );
        register( new Step() );
        register( new Timer() );
        //getCommand( "acbvl" ).setExecutor( new CheckVL() );
        /*
          acbvl:
    usage: /vl <player>
    permission: advancedcheatblocker.showvl

    removed from plugin.yml cuz developing command.
         */
        getCommand( "acbflag" ).setExecutor( new FlagMessage() );
        getCommand( "acb" ).setExecutor( new ACB() );
    }

//    @EventHandler
//    public void onJoin(PlayerJoinEvent e){
//        FlagUtil.WhenJoinCall( e.getPlayer());
//    }
//    @EventHandler
//    public void onQuit(PlayerQuitEvent e){
//        FlagUtil.WhenQuitCall( e.getPlayer() );
//    }
//
    public void register(Listener listener){
        getServer().getPluginManager().registerEvents( listener , this );
    }
}
