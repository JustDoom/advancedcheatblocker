package com.advancedcheatblocker.acb.pluginanticheat.utils;

import com.advancedcheatblocker.acb.pluginanticheat.checks.CheckNames;

public class ChecksManager{
    public static boolean flya,flyb,flyc,flyd,flye,speeda,speedb,speedc,step,noslow,nofall,invalidmove,timer,scaffold,killaura,flyf,flyg,flyh,flyi = true;
    public static boolean flyaFlag,flybFlag,flycFlag,flydFlag,flyeFlag,speedaFlag,speedbFlag,speedcFlag,stepFlag,noslowFlag,invalidmoveFlag,timerFlag,scaffoldFlag,killauraFlag,flyfFlag,flygFlag,flyhFlag,flyiFlag = false;
    @Deprecated
    public static void setAction(CheckNames check,boolean data){
        switch (check){
            case FlyA:
                flyaFlag = data;
                break;
            case FlyB:
                flybFlag = data;
                break;
            case FlyC:
                flycFlag = data;
                break;
            case FlyD:
                flydFlag = data;
                break;
            case FlyE:
                flyeFlag = data;
                break;
            case SpeedA:
                speedaFlag = data;
                break;
            case SpeedB:
                speedbFlag = data;
                break;
            case SpeedC:
                speedcFlag = data;
                break;
            case Step:
                stepFlag = data;
                break;
            case InvalidMove:
                invalidmoveFlag = data;
                break;
            case Timer:
                timerFlag = data;
                break;
            case NoSlowDown:
                noslowFlag = data;
                break;
            case ScaffoldA:
                flyaFlag = data;
                break;
            case KillAura:
                killauraFlag = data;
                break;
            case FlyF:
                flyfFlag = data;
                break;
            case FlyG:
                flygFlag = data;
                break;
            case FlyH:
                flyhFlag = data;
                break;
            case FlyI:
                flyiFlag = data;
                break;
        }
    }

    @Deprecated
    public static void setStatus(CheckNames check,boolean data){
        switch (check){
            case FlyA:
                flya = data;
                break;
            case FlyB:
                flyb = data;
                break;
            case FlyC:
                flyc = data;
                break;
            case FlyD:
                flyd = data;
                break;
            case FlyE:
                flye = data;
                break;
            case SpeedA:
                speeda = data;
                break;
            case SpeedB:
                speedb = data;
                break;
            case SpeedC:
                speedc = data;
                break;
            case Step:
                step = data;
                break;
            case NoFall:
                nofall = data;
                break;
            case InvalidMove:
                invalidmove = data;
                break;
            case Timer:
                timer = data;
                break;
            case NoSlowDown:
                noslow = data;
                break;
            case ScaffoldA:
                flya = data;
                break;
            case KillAura:
                killaura = data;
                break;
            case FlyF:
                flyf = data;
                break;
            case FlyG:
                flyg = data;
                break;
            case FlyH:
                flyh = data;
                break;
            case FlyI:
                flyi = data;
                break;
        }
    }

}
