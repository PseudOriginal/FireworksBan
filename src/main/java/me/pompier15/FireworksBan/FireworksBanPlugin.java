package me.pompier15.FireworksBan;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FireworksBanPlugin extends JavaPlugin {

    @Override
    public void onEnable()
    {
        FileConfiguration config = this.getConfig();
        config.addDefault("commandToExecute","none");
        config.addDefault("maxEffectNumber",10);

        //Save it into the config.yml file
        config.options().copyDefaults(true);
        saveConfig();

        new Listeners(this);
    }

    public String getCommandToExecuteFromConfig(){

        reloadConfig();
        FileConfiguration config = this.getConfig();
        return config.getString("commandToExecute");
    }

    public int getMaxEffectSizeFromConfig(){
        reloadConfig();
        FileConfiguration config = this.getConfig();

        return config.getInt("maxEffectNumber");
    }
}
