package me.mchiappinam.pdghmito;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Comando implements CommandExecutor {
  private Main plugin;

  public Comando(Main main) {
    plugin = main;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
    if (cmd.getName().equalsIgnoreCase("mito")) {
        if (args.length >0) {
            sender.sendMessage("§cUse /mito");
            return true;
          }
      if (plugin.getConfig().getString("Mito").length() > 0) {
        sender.sendMessage("§5[ⓂⒾⓉⓄ] §l"+plugin.getConfig().getString("Mito")+" §cé o §5ⓂⒾⓉⓄ §catual.");
      }else{
        sender.sendMessage("§5[ⓂⒾⓉⓄ] §cSem §5ⓂⒾⓉⓄ §catual.");
      return true;
      }
    }
    if (cmd.getName().equalsIgnoreCase("setmito")) {
      if (!sender.hasPermission("pdgh.admin")) {
        sender.sendMessage("§cSem permissões");
        return true;
      }
      if ((args.length >1) || (args.length == 0)) {
        sender.sendMessage("§cUse /setmito <nick>");
        return true;
      }
      plugin.getConfig().set("Mito", args[0]);
      plugin.saveConfig();
      plugin.reloadConfig();
      plugin.getServer().broadcastMessage(" ");
      plugin.getServer().broadcastMessage("§5[ⓂⒾⓉⓄ] §l"+args[0]+" §cé o novo §5ⓂⒾⓉⓄ");
      plugin.getServer().broadcastMessage(" ");
      
      Player p = plugin.getServer().getPlayer(args[0]);
      if (!(p == null)) {
          plugin.novoMito(p);
      }
      return true;
    }
    return true;
  }
}