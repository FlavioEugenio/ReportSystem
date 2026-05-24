package com.flavio.reportsystem.commands;

import com.flavio.reportsystem.managers.LogManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.configuration.ConfigurationSection;

import org.bukkit.entity.Player;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ReportLogsCommand
        implements CommandExecutor {

    private final LogManager
            logManager =
            new LogManager();

    @Override
    public boolean onCommand(

            CommandSender sender,

            Command command,

            String label,

            String[] args

    ) {

        // =========================
        // PLAYER
        // =========================

        if (!(sender instanceof Player)) {

            sender.sendMessage(
                    "§cApenas jogadores."
            );

            return true;

        }

        Player player =
                (Player) sender;

        // =========================
        // PERMISSION
        // =========================

        if (!player.hasPermission(
                "reports.logs"
        )) {

            player.sendMessage(
                    "§cSem permissão."
            );

            return true;

        }

        // =========================
        // INVENTORY
        // =========================

        Inventory inventory =
                Bukkit.createInventory(

                        null,

                        54,

                        "§8Logs Staff"

                );

        ConfigurationSection section =
                logManager.getConfig()
                        .getConfigurationSection(
                                "logs"
                        );

        // =========================
        // NO LOGS
        // =========================

        if (section == null) {

            player.sendMessage(
                    "§cNenhum log encontrado."
            );

            return true;

        }

        // =========================
        // LOAD LOGS
        // =========================

        for (String id :
                section.getKeys(false)) {

            String action =
                    section.getString(
                            id + ".action"
                    );

            String staff =
                    section.getString(
                            id + ".staff"
                    );

            String target =
                    section.getString(
                            id + ".target"
                    );

            String date =
                    section.getString(
                            id + ".date"
                    );

            ItemStack item =
                    new ItemStack(
                            Material.PAPER
                    );

            ItemMeta meta =
                    item.getItemMeta();

            meta.setDisplayName(
                    "§eLog #" + id
            );

            List<String> lore =
                    new ArrayList<>();

            lore.add(
                    "§7Ação: §f"
                            + action
            );

            lore.add(
                    "§7Staff: §f"
                            + staff
            );

            lore.add(
                    "§7Target: §f"
                            + target
            );

            lore.add(
                    "§7Data: §f"
                            + date
            );

            meta.setLore(lore);

            item.setItemMeta(meta);

            inventory.addItem(item);

        }

        // =========================
        // OPEN
        // =========================

        player.openInventory(
                inventory
        );

        return true;

    }

}