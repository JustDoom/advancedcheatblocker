package com.advancedcheatblocker.acb.pluginanticheat;

import com.advancedcheatblocker.acb.pluginanticheat.checks.CheckNames;
import com.advancedcheatblocker.acb.pluginanticheat.checks.combats.*;
import com.advancedcheatblocker.acb.pluginanticheat.checks.movements.*;
import com.advancedcheatblocker.acb.pluginanticheat.checks.player.*;
import com.advancedcheatblocker.acb.pluginanticheat.checks.worlds.*;
import com.advancedcheatblocker.acb.pluginanticheat.commands.*;
import com.advancedcheatblocker.acb.pluginanticheat.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin implements Listener{
    public static boolean AntiCheatStatus = true;
    public static String version = "b0.4.3";
    public static boolean AutoPunish = false;
    public static String AutoPunishCmd = "kick %%player%%";
    public static int PunishViolationsCount = 150;
    public static boolean allowbypass = false;
    public static boolean sendflagtoconsole = true;
    public static FileConfiguration config;
    public static Plugin plugin;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        plugin = this;

        System.out.println( "[AdvancedCheatBlocker] Our Official Discord: https://discord.gg/CWm29mnaM");

        if( !Bukkit.getVersion( ).contains( "1.8" )){
            System.out.println( "[WARNING] The Plugin is only tested in 1.8.x!" );
        }

        register( this );
        register( new Fly() );
        register( new Speed() );
        register( new NoSlowDown() );
        register( new NoFall() );
        register( new KillAura() );
        register( new Step() );
        register( new Timer() );
        register( new InvalidMove() );
        register(new Scaffold()  );


        System.out.println( "Loading config..." );
        ConfigLoad();
        System.out.println( "Loaded config!" );
        getCommand( "acbflag" ).setExecutor( new FlagMessage() );
        getCommand( "acb" ).setExecutor( new ACB() );
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        FlagUtil.FlagMsg.remove( player );
        PlayerUtil.BypassMap.remove( player );
        FlagUtil.ViolationsCount.remove( player );
    }

    public void ConfigLoad(){
        allowbypass = config.getBoolean( "allow-bypass" );
        sendflagtoconsole = config.getBoolean( "send-flag-messaage-to-console" );
        /*
        ChecksManager.setStatus( CheckNames.FlyA , true/false ); Enable/Disable Check.
        ChecksManager.setAction( CheckNames.FlyA , true/false ); Enable/Disable TeleportBack/RemoveHit/RemoveBlock.
        */
        AutoPunish = config.getBoolean("punish-settings.enable");
        AutoPunishCmd = config.getString("punish-settings.command");
        PunishViolationsCount = config.getInt( "punish-settings.punish-violations-count" );
        if(!(PunishViolationsCount > 0)){
            System.out.println( "[ERROR] Wrong Config. punish-violations-count | Value is not over than 0(minimum value is 1). so Value is set to 150." );
            PunishViolationsCount = 150;
        }

        ChecksManager.flya = config.getBoolean( "checks.Fly.A.enable" );
        ChecksManager.flyb = config.getBoolean( "checks.Fly.B.enable" );
        ChecksManager.flyc = config.getBoolean( "checks.Fly.C.enable" );
        ChecksManager.flyd = config.getBoolean( "checks.Fly.D.enable" );
        ChecksManager.flye = config.getBoolean( "checks.Fly.E.enable" );
        ChecksManager.speeda = config.getBoolean( "checks.Speed.A.enable" );
        ChecksManager.speedb = config.getBoolean( "checks.Speed.B.enable" );
        ChecksManager.speedc = config.getBoolean( "checks.Speed.C.enable" );
        ChecksManager.step = config.getBoolean( "checks.Step.enable" );
        ChecksManager.noslow = config.getBoolean( "checks.NoSlow.enable" );
        ChecksManager.nofall = config.getBoolean( "checks.NoFall.enable" );
        ChecksManager.invalidmove = config.getBoolean( "checks.InvalidMove.enable" );
        ChecksManager.scaffold = config.getBoolean( "checks.Scaffold.enable" );
        ChecksManager.timer = config.getBoolean( "checks.Timer.enable" );
        ChecksManager.killaura = config.getBoolean( "checks.KillAura.enable" );

        ChecksManager.flyaFlag = config.getBoolean( "checks.Fly.A.teleportback" );
        ChecksManager.flybFlag = config.getBoolean( "checks.Fly.B.teleportback" );
        ChecksManager.flycFlag = config.getBoolean( "checks.Fly.C.teleportback" );
        ChecksManager.flydFlag = config.getBoolean( "checks.Fly.D.teleportback" );
        ChecksManager.flyeFlag = config.getBoolean( "checks.Fly.E.teleportback" );
        ChecksManager.speedaFlag = config.getBoolean( "checks.Speed.A.teleportback" );
        ChecksManager.speedbFlag = config.getBoolean( "checks.Speed.B.teleportback" );
        ChecksManager.speedcFlag = config.getBoolean( "checks.Speed.C.teleportback" );
        ChecksManager.stepFlag = config.getBoolean( "checks.Step.teleportback" );
        ChecksManager.noslowFlag = config.getBoolean( "checks.NoSlow.teleportback" );
        ChecksManager.invalidmoveFlag = config.getBoolean( "checks.InvalidMove.teleportback" );
        ChecksManager.scaffoldFlag = config.getBoolean( "checks.Scaffold.removeblock" );
        ChecksManager.timerFlag = config.getBoolean( "checks.Timer.teleportback" );
        ChecksManager.killauraFlag = config.getBoolean( "checks.KillAura.removehit" );
    }
    public void register(Listener listener){
        getServer().getPluginManager().registerEvents( listener , this );
    }
}
