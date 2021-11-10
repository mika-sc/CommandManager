package com.onlineupdate;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import com.github.avery.MyFirstPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class updatePlugin {
    File msgfile = new File("\\DelayCommand\\message.yml");
    File lang = new File("\\DelayCommand\\config.yml");
    YamlConfiguration msg = YamlConfiguration.loadConfiguration(msgfile);
    YamlConfiguration la = YamlConfiguration.loadConfiguration(lang);
    public static final updatePlugin INSTANCE = new updatePlugin();
    public boolean upd (CommandSender sender,String status,int downloadimme){
        if(status.equalsIgnoreCase("download")){
            if(!dow(sender,downloadimme))return true;
            sender.sendMessage(MyFirstPlugin.getPlugin().msgCtrl("Down"));
            return true;
        }else if (status.equalsIgnoreCase("install")){
            if(!dow(sender,downloadimme))return true;
            sender.sendMessage();
            //Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"");
        }
        return false;
    }
    private boolean dow (CommandSender sender,int downloadimme){
        if(downloadimme==0)
            if(!MyFirstPlugin.getPlugin().getUpdate(1))return false;
        sender.sendMessage("§e[DCMD] §2Downloading ...");
        try {
            URL url = new URL("https://api.curseforge.com/servermods/files?projectids=545091");
            URLConnection conn = url.openConnection();
            conn.setReadTimeout(5000);
            conn.addRequestProperty("User-Agent", "DCMD Update Checker");
            conn.setDoOutput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = reader.readLine();
            JSONArray array = (JSONArray) JSONValue.parse(response);
            String downlURL = ((String)((JSONObject)array.get(array.size() - 1)).get("downloadUrl"));
            String fileName = ((String)((JSONObject)array.get(array.size() - 1)).get("fileName"));
            downloadUsingStream(downlURL,".\\plugins\\DelayCommand\\"+fileName);
            return true;
        } catch (Exception e) {}
        return false;
    }
    private static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
}
