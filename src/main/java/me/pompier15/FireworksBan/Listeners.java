package me.pompier15.FireworksBan;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandException;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.projectiles.ProjectileSource;

public class Listeners implements Listener
{
    private final FireworksBanPlugin _plugin;

    public Listeners(FireworksBanPlugin plugin)
    {
        _plugin = plugin;

        //Register to recover events from the plugin manager
        _plugin.getServer().getPluginManager().registerEvents(this, _plugin);
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event){

        if(event.getEntityType()== EntityType.FIREWORK)
        {
            Projectile projectile = (Projectile) event.getEntity();
            Firework firework = (Firework) event.getEntity();

            if(firework.getFireworkMeta().getEffectsSize() > _plugin.getMaxEffectSizeFromConfig())
            {
                ProjectileSource source = projectile.getShooter();

                if(source instanceof Player)
                {
                    Player player = (Player) source;

                    if(player.hasPermission("firework.limitation.bypass"))
                    {
                        System.out.println("[FireworksBan] [INFO] Player "+player.getDisplayName()+" tried to launch a malicious firework but has the permission to do it");
                        return;
                    }
                    System.out.println("[FireworksBan] [WARN] Player "+player.getDisplayName()+" tried to launch a malicious firework");

                    String command = _plugin.getCommandToExecuteFromConfig();

                    if(command!=null && !command.equals("none"))
                    {
                        command = command.replaceAll("%PLAYER%",player.getDisplayName());

                        try
                        {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                        }
                        catch (CommandException e){
                            System.out.println("[FireworksBan] [ERROR]"+e.getLocalizedMessage());
                        }
                    }
                }
                System.out.println("[FireworksBan] [WARN] Malicious firework launch prevented");

                event.setCancelled(true);
            }
        }
    }
}
