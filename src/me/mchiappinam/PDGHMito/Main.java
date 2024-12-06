package me.mchiappinam.pdghmito;

import java.io.File;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;

public class Main extends JavaPlugin implements Listener {
	public boolean jaDivulgou = false;
	
  public void onEnable() {
    getServer().getPluginCommand("mito").setExecutor(new Comando(this));
    getServer().getPluginCommand("setmito").setExecutor(new Comando(this));
    getServer().getPluginManager().registerEvents(this, this);

	File file = new File(getDataFolder(),"config.yml");
	if(!file.exists()) {
		try {
			saveResource("config_template.yml",false);
			File file2 = new File(getDataFolder(),"config_template.yml");
			file2.renameTo(new File(getDataFolder(),"config.yml"));
		}catch(Exception e) {}
	}

	getServer().getConsoleSender().sendMessage("§3[PDGHMito] §2ativado - Plugin by: mchiappinam");
	getServer().getConsoleSender().sendMessage("§3[PDGHMito] §2Acesse: http://pdgh.net/");
  }

  public void onDisable() {
	getServer().getConsoleSender().sendMessage("§3[PDGHMito] §2desativado - Plugin by: mchiappinam");
	getServer().getConsoleSender().sendMessage("§3[PDGHMito] §2Acesse: http://pdgh.net/");
  }

  @EventHandler(priority=EventPriority.HIGHEST)
  private void onChat(ChatMessageEvent e) {
    if ((e.getTags().contains("mito")) && (getConfig().getString("Mito").toLowerCase().trim().equalsIgnoreCase(e.getSender().getName().toLowerCase()))) {
    	e.setTagValue("mito", "§5ⓂⒾⓉⓄ");
    }
  }

  @EventHandler(priority=EventPriority.HIGHEST)
  private void onDeath(PlayerDeathEvent e) {
    if ((e.getEntity().getKiller() instanceof Player)) {
      Player killed = e.getEntity();
      Player killer = e.getEntity().getKiller();
      if (getConfig().getString("Mito").toLowerCase().trim().equalsIgnoreCase(killed.getName().toLowerCase())) {
        getConfig().set("Mito", killer.getName());
        saveConfig();
        reloadConfig();
        getServer().broadcastMessage(" ");
        getServer().broadcastMessage("§5[ⓂⒾⓉⓄ] §l"+killed.getName()+" §cmorreu!");
        getServer().broadcastMessage("§5[ⓂⒾⓉⓄ] §cAgora o novo §5ⓂⒾⓉⓄ §cé §5§l"+killer.getName());
        getServer().broadcastMessage(" ");
        novoMito(killer);
      }
    }
  }

  @EventHandler
  private void onPlayerJoin(PlayerJoinEvent e) {
	  if ((jaDivulgou==false) && (getConfig().getString("Mito").toLowerCase().trim().equalsIgnoreCase(e.getPlayer().getName().toLowerCase()))) {
		  jaDivulgou = true;
		  getServer().broadcastMessage(" ");
		  getServer().broadcastMessage("§5[ⓂⒾⓉⓄ] §l"+e.getPlayer().getName()+" §clogou-se no servidor");
		  getServer().broadcastMessage(" ");
	  }
  }

  public void novoMito(Player p) {
		p.playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 3);
		p.playEffect(p.getLocation(), Effect.BOW_FIRE, 3);
		p.playEffect(p.getLocation(), Effect.CLICK1, 3);
		p.playEffect(p.getLocation(), Effect.CLICK2, 3);
		p.playEffect(p.getLocation(), Effect.DOOR_TOGGLE, 3);
		p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 3);
		p.playEffect(p.getLocation(), Effect.EXTINGUISH, 3);
		p.playEffect(p.getLocation(), Effect.GHAST_SHOOT, 3);
		p.playEffect(p.getLocation(), Effect.GHAST_SHRIEK, 3);
		p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 3);
		p.playEffect(p.getLocation(), Effect.POTION_BREAK, 3);
		p.playEffect(p.getLocation(), Effect.RECORD_PLAY, 3);
		p.playEffect(p.getLocation(), Effect.SMOKE, 3);
		p.playEffect(p.getLocation(), Effect.STEP_SOUND, 3);
		p.playEffect(p.getLocation(), Effect.ZOMBIE_CHEW_IRON_DOOR, 3);
		p.playEffect(p.getLocation(), Effect.ZOMBIE_CHEW_WOODEN_DOOR, 3);
		p.playEffect(p.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 3);
		for (Player all : Bukkit.getServer().getOnlinePlayers()) {
        	all.playSound(all.getLocation(), Sound.COW_HURT, 1.0F, 1.0F);
		}
  }
  
  
  
  
  
  
  
  
  
  
}