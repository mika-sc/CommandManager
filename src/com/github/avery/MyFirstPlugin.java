package com.github.avery;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.github.avery.dcmdSendMsg.sendmessage;
import com.loadsftdep.LoadPapi;
import org.bukkit.ChatColor;
import com.github.avery.event.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import com.command.*;
import com.github.avery.version.*;
import com.onlineupdate.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class MyFirstPlugin extends JavaPlugin implements Listener{
    private static MyFirstPlugin plugin;
    protected int Normally;
    public String VER="1.3";
    public String VSTA="Beta";
    public String VSVER="4";
    public String BUILD="145";
    public String VERSION=VER+VSVER+"."+BUILD;
    public double newVersion = 0.0D;
    public double currentVersion = 0.0D;
    public String newVersionTitle = "";
    public String currentVersionTitle = VERSION;
    public boolean CitEna=false;
    public boolean ComEnd=false;
    private getVersion bkversion = new getVersion();
    public CommandSender sender2=getServer().getConsoleSender();
    @Override
    public void onEnable(){
        Normally = 422;
        this.currentVersionTitle = getDescription().getVersion().split("-")[0];
        this.currentVersion = Double.valueOf(this.currentVersionTitle.replaceFirst("\\.", "")).doubleValue();
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        //System.out.println(getServer().getPluginManager().isPluginEnabled("CatSeedLogin"));
        //System.out.println(getDataFolder());
        if(!getDataFolder().exists())getDataFolder().mkdir();
        File oldmsg = new File(getDataFolder(), "languageselect.yml");
        boolean w;
        if(oldmsg.exists())w = oldmsg.delete();
        //getServer().getPluginManager().registerEvents(new VioMessage(), this);
        File langsel = new File(getDataFolder(), "config.yml");
        if(!langsel.exists()||!this.VERSION.equalsIgnoreCase(YamlConfiguration.loadConfiguration(langsel).getString("ver"))) {
            try {
                //System.out.println("Create");
                boolean b = langsel.createNewFile();
                //getServer().getLogger("b");
                YamlConfiguration la = YamlConfiguration.loadConfiguration(langsel);
                la.set("ver",this.VERSION);
                la.set("update-check",true);
                la.set("lang","cn");
                la.set("enablejoinmessage","disable");
                la.save(langsel);
            } catch(IOException e) {e.printStackTrace();}
        }
        File msgfile = new File(getDataFolder(), "message.yml");
        YamlConfiguration msg = YamlConfiguration.loadConfiguration(msgfile);
        if (!msgfile.exists()||!this.VERSION.equalsIgnoreCase(msg.getString("trver"))) {
            try {
                boolean b = msgfile.createNewFile();
                msg.set("trver",this.VERSION);
                msg.set("Catchken","§2CatSeedLogin Checked!");
                msg.set("Catchkcn","§2已检测到CatSeedLogin!");
                msg.set("Authchken","§2AuthMeReloaded Checked!");
                msg.set("Authchkcn","§2已检测到AuthMeReloaded!");
                msg.set("Nonechken","§2Login plugins not found!(This is not a error)");
                msg.set("Nonechkcn","§2未找到登录插件!");
                msg.set("PluEnaen","§2DCMD §3Version "+this.VERSION+" §2has been enabled.------By Normally_422");
                msg.set("PluEnacn","§2DCMD §3v"+this.VERSION+"§2已加载！------By Normally_422");
                msg.set("PluDisaen","§2Goodbye. :D");
                msg.set("PluDisacn","§2DCMD已关闭！");
                msg.set("LoCiten","§4CitizensII not found!");
                msg.set("LoCitcn","§4未找到CitizensII插件!");
                msg.set("LoComen","§4CommandNPC not found!");
                msg.set("LoComcn","§4未找到CommandNPC插件!");
                msg.set("Ver","§2Version §3"+this.VER+" "+this.VSTA+" §3"+this.VSVER+"§2, Build §3"+this.BUILD+" §2(v§3"+this.VERSION+"§2),By Normally_422");
                msg.set("NorOPen","§4Sorry! You don't have enough permission to do this.");
                msg.set("NorOPcn","§4你没有足够的权限执行此命令！");
                msg.set("CmdSeten","§2Command set successfully.");
                msg.set("CmdSetcn","§2命令成功设置！");
                msg.set("CmdDelen","§2Command delete successfully.");
                msg.set("CmdDelcn","§2命令成功删除！");
                msg.set("CmdLisen","§2Player: §3%Player%, §2Command: §3%Command% §2.");
                msg.set("CmdLiscn","§2玩家: §3%Player%, §2命令: §3%Command% §2.");
                msg.set("CmdTimen","§2Time: §3%Player%, §2Command: §3%Command% §2.");
                msg.set("CmdTimcn","§2时间: §3%Player%, §2命令: §3%Command% §2.");
                msg.set("PapiUnfen","§4PlaceholderAPI not found!");
                msg.set("PapiUnfcn","§4未找到PlaceholderAPI！PAPI功能将不生效！");
                msg.set("WrngCmdcn","§4语法错误！输入/dcmd help查询命令.");
                msg.set("WrngCmden","§4Wrong args! Type /dcmd help for helps.");
                msg.set("Chkupen","§2Checking for Updates ... ");
                msg.set("Chkupcn","§2正在检查更新 ... ");
                msg.set("Downcn","§2新版本已下载至.\\plugins\\DelayCommand文件夹！");
                msg.set("Downen","§2The latest version has been downloaded at the folder .\\plugins\\DelayCommand !");
                msg.save(msgfile);
            } catch (IOException e) {e.printStackTrace();}
        }

        if (getServer().getPluginManager().isPluginEnabled("CatSeedLogin")){
            getServer().getPluginManager().registerEvents(new CatJoin(), this);
            getServer().getPluginManager().registerEvents(new CatReg(), this);
            sendmessage.plugin.message(msgCtrl("Catchk"),sender2);
        }else if (getServer().getPluginManager().isPluginEnabled("AuthMe")){
            getServer().getPluginManager().registerEvents(new AuthJoin(), this);
            getServer().getPluginManager().registerEvents(new AuthReg(), this);
            sendmessage.plugin.message(msgCtrl("Authchk"),sender2);
        }
        else {
            getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        }

        //加载lang.yml文件
        //YamlConfiguration msg = YamlConfiguration.loadConfiguration(msgfile);
        YamlConfiguration con = YamlConfiguration.loadConfiguration(langsel);
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            boolean b = (new LoadPapi()).register();
            if (b) sendmessage.plugin.message("§2PlaceholderAPI registered OK!",sender2);
            else sendmessage.plugin.message("§4PlaceholderAPI registered failed!",sender2);
        } else {
            if("cn".equalsIgnoreCase(con.getString("lang")))getServer().getConsoleSender().sendMessage(msg.getString("PapiUnfcn"));
            else getServer().getConsoleSender().sendMessage(msg.getString("PapiUnfen"));
        }
        plugin = this;
        if (!getServer().getPluginManager().isPluginEnabled("Citizens")) {
            //getServer().getConsoleSender().sendMessage("&e[DCMD] &4Error: 未找到CitizensII插件，DCMD即将关闭. / CitizensII not found! DCMD will disable.");
            if("cn".equalsIgnoreCase(con.getString("lang")))getServer().getConsoleSender().sendMessage(msg.getString("LoCitcn"));
            else getServer().getConsoleSender().sendMessage(msg.getString("LoCiten"));
            this.CitEna=true;
            //getServer().getPluginManager().disablePlugin(plugin);
        }
        if (!getServer().getPluginManager().isPluginEnabled("CommandNPC")) {
            if(con.getString("lang").equalsIgnoreCase("cn"))getServer().getConsoleSender().sendMessage(msg.getString("LoComcn"));
            else getServer().getConsoleSender().sendMessage(msg.getString("LoComen"));
            this.ComEnd=true;
            //getServer().getConsoleSender().sendMessage("&e[DCMD] &4Error: 未找到CommandNPC插件，DCMD即将关闭. / CommandNPC not found! DCMD will disable.");
            //getServer().getPluginManager().disablePlugin(plugin);
        }
        sendmessage.plugin.message("§2Initializing datas......",sender2);
        CommandManager.INSTANCE.init(0);
        File conf=new File("\\DelayCommand\\data.yml");
        sendmessage.plugin.message("§2Loading configs......",sender2);
        //conf.delete();
        //CommandManager.INSTANCE.init(1);
        //sendMess(this.sender2);

        //File conf=new File("\\DelayCommand\\data.yml");
        if("cn".equalsIgnoreCase(con.getString("lang")))sendmessage.plugin.message(msg.getString("PluEnacn"),sender2);
        else sendmessage.plugin.message(msg.getString("PluEnaen"),sender2);
        sendmessage.plugin.message("§2Running on Minecraft server version §3"+this.bkversion.getBukkitVer(),sender2);
        //getCommand("dcmd").setExecutor(new MyFirstPlugin());

        //System.out.println("[DelayCommand] DCMD Version 1.23.76 has been enabled.------By Normally_422");
        getUpdate(0);
    }
    boolean getuptmp = false;
    public boolean getUpdate(int gettingModule){//0-> onEnable 1-> Check Update

        getServer().getScheduler().runTask((Plugin)this, new Runnable() {
            public void run() {
                /*Permission perm = Vault.this.getServer().getPluginManager().getPermission("vault.update");
                if (perm == null) {
                    perm = new Permission("vault.update");
                    perm.setDefault(PermissionDefault.OP);
                    Vault.this.plugin.getServer().getPluginManager().addPermission(perm);
                }
                perm.setDescription("Allows a user or the console to check for vault updates");*/
                getServer().getScheduler().runTaskTimerAsynchronously((Plugin)plugin, new Runnable(){
                    public void run(){
                        if (getServer().getConsoleSender().hasPermission("dcmdupdate") && getConfig().getBoolean("update-check", true))
                            try {
                                sendmessage.plugin.message(msgCtrl("Chkup"),sender2);
                                newVersion = updateCheck(currentVersion);
                                if (newVersion > currentVersion) {
                                    sendmessage.plugin.message("The latest version: v§3" + newVersionTitle + "§e is out! You are still running version: v§3" + currentVersionTitle,sender2);
                                    if (gettingModule == 0)sendmessage.plugin.message("Update at: §3https://dev.bukkit.org/projects/DelayofflineCommand",sender2);
                                    getuptmp = true;
                                } else if (currentVersion > newVersion) {
                                    sendmessage.plugin.message("§2The Latest Version: v§3" + newVersionTitle + "§2 | Current Version: v§3" + currentVersionTitle,sender2);
                                    sendmessage.plugin.message("§2No new version available!",sender2);
                                } else {
                                    sendmessage.plugin.message("§2No new version available!",sender2);
                                }
                            } catch (Exception exception) {}
                    }
                }, 0L, 432000L);
            }
        });
        return getuptmp;
    }
    public void onDisable(){
        File msgfile = new File(getDataFolder(),"message.yml");
        File lang = new File(getDataFolder(),"config.yml");
        YamlConfiguration msg = YamlConfiguration.loadConfiguration(msgfile);
        YamlConfiguration la = YamlConfiguration.loadConfiguration(lang);
        if("cn".equalsIgnoreCase(la.getString("lang")))sendmessage.plugin.message(msg.getString("PluDisacn"),sender2);
        else sendmessage.plugin.message(msg.getString("PluDisaen"),sender2);
        sendmessage.plugin.message("§2Saving configs......",sender2);
        CommandManager.INSTANCE.save();
        //System.out.println("&e[DelayCommand] Thank you and goodbye. :D");
    }
    public String msgCtrl(String msgnamepre){
        File msgfile = new File(getDataFolder(),"message.yml");
        File lang = new File(getDataFolder(),"config.yml");
        YamlConfiguration msg = YamlConfiguration.loadConfiguration(msgfile);
        YamlConfiguration la = YamlConfiguration.loadConfiguration(lang);
        if("cn".equalsIgnoreCase(la.getString("lang")))return msg.getString(msgnamepre+"cn");
        else return msg.getString(msgnamepre+"en");
    }
    /*private void sendMess(CommandSender sender){
        sendmessage.plugin.message("&e[DCMD] &4Error: CommandNPC not found! DCMD will disable.");
    }*/
    /*public void help(CommandSender sender,Command cmd){
        sendmessage.plugin.message("=======DelayCommand Helps=======");
        sendmessage.plugin.message("    /dcmd ver   Show version.");
        sendmessage.plugin.message("    /dcmd predo <player> <aftercommand>   Do command when player online.");
    }*/

    private void help(){
        File msgfile = new File(getDataFolder(),"message.yml");
        File lang = new File(getDataFolder(),"config.yml");
        YamlConfiguration msg = YamlConfiguration.loadConfiguration(msgfile);
        YamlConfiguration la = YamlConfiguration.loadConfiguration(lang);
        if("cn".equalsIgnoreCase(la.getString("lang")))sendmessage.plugin.message(msg.getString("WrngCmdcn"),sender2);
        else sendmessage.plugin.message(msg.getString("WrngCmden"),sender2);
    }
    public double updateCheck(double currentVersion) {
        try {
            URL url = new URL("https://api.curseforge.com/servermods/files?projectids=545091");
            URLConnection conn = url.openConnection();
            conn.setReadTimeout(5000);
            conn.addRequestProperty("User-Agent", "DCMD Update Checker");
            conn.setDoOutput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = reader.readLine();
            JSONArray array = (JSONArray) JSONValue.parse(response);
            if (array.size() == 0) {
                sendmessage.plugin.message("§4No files found, please check your network.",sender2);
                return currentVersion;
            }

            this.newVersionTitle = ((String)((JSONObject)array.get(array.size() - 1)).get("name")).replace("DCMD", "").trim();

            //sendmessage.plugin.message(fir);
            return Double.valueOf(this.newVersionTitle.replaceFirst("\\.", "").trim()).doubleValue();
        } catch (Exception e) {
            sendmessage.plugin.message("§4There was an issue attempting to check for the latest version!",sender2);
            sendmessage.plugin.message("§4尝试检查最新版本时出现问题!",sender2);
            e.printStackTrace();
            return currentVersion;
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        this.sender2=sender;
        File msgfile = new File(getDataFolder(),"message.yml");
        File lang = new File(getDataFolder(),"config.yml");
        /*if(!msgfile.exists()){
            System.out.println("onEnable");
            this.onEnable();
        }*/
        YamlConfiguration msg = YamlConfiguration.loadConfiguration(msgfile);
        YamlConfiguration la = YamlConfiguration.loadConfiguration(lang);
        if(label.equalsIgnoreCase("dcmd")){
            if(args.length==0){
                //help(sender,cmd);
                this.help();
                return true;
            }
            if(args.length==1) {
                if (args[0].equalsIgnoreCase("ver")) {
                    sendmessage.plugin.message(msg.getString("Ver"),sender);
                    return true;
                }
                if(args[0].equalsIgnoreCase("predo")){
                    if(!sender.hasPermission("dcmd.admin")){
                        if("cn".equalsIgnoreCase(la.getString("lang")))sendmessage.plugin.message(msg.getString("NorOPcn"),sender);
                        else sendmessage.plugin.message(msg.getString("NorOPen"),sender);
                        //.sendMessage("§e[DCMD] §4Sorry! You are not OP so you can't do this.");
                        help();
                        return true;
                    }
                }
                if(args[0].equalsIgnoreCase("update")){
                    //String tmp=args[1];
                    String tmp = "download";
                    if(!updatePlugin.INSTANCE.upd(sender2,tmp,0)){
                        this.help();
                        return true;
                    }

                    return true;
                }
                if(args[0].equalsIgnoreCase("noropenbynormallydownloadthelatestfile")){
                    updatePlugin.INSTANCE.upd(sender2,"download",1);
                    return true;
                }
                if(args[0].equalsIgnoreCase("help")){
                    return false;
                }
                this.help();
                return true;
            }
            /*if(args[1].equalsIgnoreCase("--clean")){

            }*/
            if(args.length==2){

                this.help();
                return true;
            }
            if(args[0].equalsIgnoreCase("predo")){
                if(sender.hasPermission("dcmd.admin")){
                    String trueCommand = getTrueCommand(args);
                    String pla=args[1];
                    if("cn".equalsIgnoreCase(la.getString("lang"))){
                        sendmessage.plugin.message(msg.getString("CmdSetcn"),sender);
                        sendmessage.plugin.message(msg.getString("CmdLiscn").replace("%Player%",pla).replace("%Command%",trueCommand),sender);
                    }
                    else {
                        sendmessage.plugin.message(msg.getString("CmdSeten"),sender);
                        sendmessage.plugin.message(msg.getString("CmdLisen").replace("%Player%",pla).replace("%Command%",trueCommand),sender);
                    }
                    //sendmessage.plugin.message("§e[DCMD] §2Player: §3"+pla+", §2Command: §3"+trueCommand+"§2.");
                    addPlayerCommand(pla, trueCommand);
                /*String str="";
                for(int i=3;i<=args.length;i++){
                    str=str+args[i]+" ";
                }*/
                    //getServer().getPluginManager().registerEvents(new setCommand(), this);
                    return true;
                }else{
                    if("cn".equalsIgnoreCase(la.getString("lang")))sendmessage.plugin.message(msg.getString("NorOPcn"),sender);
                    else sendmessage.plugin.message(msg.getString("NorOPen"),sender);
                    return true;
                }
            }else if(args[0].equalsIgnoreCase("predodel")){
                if(sender.hasPermission("dcmd.admin")){
                    String trueCommand = getTrueCommand(args);
                    String pla=args[1];
                    if("cn".equalsIgnoreCase(la.getString("lang"))){
                        sendmessage.plugin.message(msg.getString("CmdDelcn"),sender);
                        sendmessage.plugin.message(msg.getString("CmdLiscn").replace("%Player%",pla).replace("%Command%",trueCommand),sender);
                    }
                    else {
                        sendmessage.plugin.message(msg.getString("CmdDelen"),sender);
                        sendmessage.plugin.message(msg.getString("CmdLisen").replace("%Player%",pla).replace("%Command%",trueCommand),sender);
                    }
                    //sendmessage.plugin.message("§e[DCMD] §2Player: §3"+pla+", §2Command: §3"+trueCommand+"§2.");
                    addDeleteCommand(pla, trueCommand);
                    return true;
                }else{
                    if("cn".equalsIgnoreCase(la.getString("lang")))sendmessage.plugin.message(msg.getString("NorOPcn"),sender);
                    else sendmessage.plugin.message(msg.getString("NorOPen"),sender);
                    return true;
                }
            }else if(args[0].equalsIgnoreCase("preti")){
                if(sender.hasPermission("dcmd.admin")){
                    String trueCommand = getTrueCommand(args);
                    String tm=args[1];
                    if("cn".equalsIgnoreCase(la.getString("lang"))){
                        sendmessage.plugin.message(msg.getString("CmdSetcn"),sender);
                        sendmessage.plugin.message(msg.getString("CmdTimcn").replace("%Player%",tm).replace("%Command%",trueCommand),sender);
                    }
                    else {
                        sendmessage.plugin.message(msg.getString("CmdSeten"),sender);
                        sendmessage.plugin.message(msg.getString("CmdTimen").replace("%Player%",tm).replace("%Command%",trueCommand),sender);
                    }
                    //sendmessage.plugin.message("§e[DCMD] §2Player: §3"+pla+", §2Command: §3"+trueCommand+"§2.");
                    addTimesCommand(tm, trueCommand);
                    return true;
                }else{
                    if("cn".equalsIgnoreCase(la.getString("lang")))sendmessage.plugin.message(msg.getString("NorOPcn"),sender);
                    else sendmessage.plugin.message(msg.getString("NorOPen"),sender);
                    return true;
                }
            }else if(args[0].equalsIgnoreCase("pretidel")){
                if(sender.hasPermission("dcmd.admin")){
                    String trueCommand = getTrueCommand(args);
                    String tm=args[1];
                    if("cn".equalsIgnoreCase(la.getString("lang"))){
                        sendmessage.plugin.message(msg.getString("CmdDelcn"),sender);
                        sendmessage.plugin.message(msg.getString("CmdTimcn").replace("%Player%",tm).replace("%Command%",trueCommand),sender);
                    }
                    else {
                        sendmessage.plugin.message(msg.getString("CmdDelen"),sender);
                        sendmessage.plugin.message(msg.getString("CmdTimen").replace("%Player%",tm).replace("%Command%",trueCommand),sender);
                    }
                    //sendmessage.plugin.message("§e[DCMD] §2Player: §3"+pla+", §2Command: §3"+trueCommand+"§2.");
                    addDelTimCommand(tm, trueCommand);
                    return true;
                }else{
                    if("cn".equalsIgnoreCase(la.getString("lang")))sendmessage.plugin.message(msg.getString("NorOPcn"),sender);
                    else sendmessage.plugin.message(msg.getString("NorOPen"),sender);
                    return true;
                }
            }
            this.help();
            return true;
        }
        return true;
    }
    private void addPlayerCommand(String plaonl, String command) {
        System.out.println("Player:"+plaonl+" Command:"+command);
        CommandManager.INSTANCE.addCommand(plaonl + "," + command);
    }
    private void addDeleteCommand(String plaonl, String command) {
        System.out.println("Player:"+plaonl+" Command:"+command);
        CommandManager.INSTANCE.addDelcommands(plaonl + "," + command);
        CommandRunnable runnable = new CommandRunnable();
        runnable.getname(plaonl);
        runnable.remove();
    }
    private void addTimesCommand(String time, String command) {
        System.out.println("Time:"+time+" Command:"+command);

    }
    private void addDelTimCommand(String time, String command) {
        System.out.println("Time:"+time+" Command:"+command);
    }
    private String getTrueCommand(String... args) {
        if (args == null || args.length <= 2)
            return "";
        String newCommand = args[2];
        for (int i = 3; i < args.length; i++)
            newCommand = String.valueOf(newCommand) + " " + args[i];
        return newCommand;
    }
    public static MyFirstPlugin getPlugin(){
        return plugin;
    }
}
