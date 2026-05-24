package com.flavio.reportsystem.commands;

import com.flavio.reportsystem.managers.ReportManager;

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

public class ReportsCommand implements CommandExecutor {

    private final ReportManager reportManager =
            new ReportManager();

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {

        if (!(sender instanceof Player)) {

            sender.sendMessage(
                    "§cApenas jogadores."
            );

            return true;

        }

        Player player =
                (Player) sender;

        if (!player.hasPermission(
                "reports.view"
        )) {

            player.sendMessage(
                    "§cSem permissão."
            );

            return true;

        }

        openReportsMenu(
                player,
                1,
                "ALL",
                "ALL"
        );

        return true;

    }

    // =========================
    // MENU
    // =========================

    public void openReportsMenu(
            Player player,
            int page,
            String statusFilter,
            String categoryFilter
    ) {

        Inventory inventory =
                Bukkit.createInventory(
                        null,
                        54,
                        "§cReports | "
                                + statusFilter
                                + " | "
                                + categoryFilter
                                + " | Página "
                                + page
                );

        ConfigurationSection reportsSection =
                reportManager.getConfig()
                        .getConfigurationSection(
                                "reports"
                        );

        // =========================
        // SEM REPORTS
        // =========================

        if (reportsSection == null) {

            player.sendMessage(
                    "§cNenhum report encontrado."
            );

            player.openInventory(inventory);

            return;

        }

        // =========================
        // ESTATÍSTICAS
        // =========================

        int totalReports = 0;

        int totalOpen = 0;

        int totalClosed = 0;

        for (String id :
                reportsSection.getKeys(false)) {

            totalReports++;

            String status =
                    reportsSection.getString(
                            id + ".status"
                    );

            if (status != null
                    && status.equalsIgnoreCase(
                    "OPEN"
            )) {

                totalOpen++;

            } else {

                totalClosed++;

            }

        }

        ItemStack statsItem =
                new ItemStack(Material.BOOK);

        ItemMeta statsMeta =
                statsItem.getItemMeta();

        statsMeta.setDisplayName(
                "§eEstatísticas"
        );

        List<String> statsLore =
                new ArrayList<>();

        statsLore.add(
                "§7Total: §f"
                        + totalReports
        );

        statsLore.add(
                "§7OPEN: §a"
                        + totalOpen
        );

        statsLore.add(
                "§7CLOSED: §c"
                        + totalClosed
        );

        statsMeta.setLore(statsLore);

        statsItem.setItemMeta(statsMeta);

        inventory.setItem(4, statsItem);

        // =========================
        // REPORTS
        // =========================

        int slot = 0;

        for (String id :
                reportsSection.getKeys(false)) {

            String status =
                    reportsSection.getString(
                            id + ".status"
                    );

            String category =
                    reportsSection.getString(
                            id + ".category"
                    );

            if (status == null) {

                status = "OPEN";

            }

            if (category == null) {

                category = "SEM";

            }

            // filtro status
            if (!statusFilter.equalsIgnoreCase(
                    "ALL"
            ) && !status.equalsIgnoreCase(
                    statusFilter
            )) {

                continue;

            }

            // filtro categoria
            if (!categoryFilter.equalsIgnoreCase(
                    "ALL"
            ) && !category.equalsIgnoreCase(
                    categoryFilter
            )) {

                continue;

            }

            String reporter =
                    reportsSection.getString(
                            id + ".player"
                    );

            String target =
                    reportsSection.getString(
                            id + ".target"
                    );

            String reason =
                    reportsSection.getString(
                            id + ".reason"
                    );

            String date =
                    reportsSection.getString(
                            id + ".date"
                    );

            ItemStack item =
                    new ItemStack(
                            Material.PAPER
                    );

            ItemMeta meta =
                    item.getItemMeta();

            meta.setDisplayName(
                    "§cReport #" + id
            );

            List<String> lore =
                    new ArrayList<>();

            lore.add(
                    "§7Reporter: §f"
                            + reporter
            );

            lore.add(
                    "§7Target: §f"
                            + target
            );

            lore.add(
                    "§7Categoria: §f"
                            + category
            );

            lore.add(
                    "§7Motivo: §f"
                            + reason
            );

            lore.add(
                    "§7Data: §f"
                            + date
            );

            lore.add(
                    "§7Status: §f"
                            + status
            );

            meta.setLore(lore);

            item.setItemMeta(meta);

            inventory.setItem(
                    slot,
                    item
            );

            slot++;

        }

        // =========================
        // FILTROS
        // =========================

        ItemStack all =
                new ItemStack(Material.BOOK);

        ItemMeta allMeta =
                all.getItemMeta();

        allMeta.setDisplayName(
                "§eTodos"
        );

        all.setItemMeta(allMeta);

        inventory.setItem(45, all);

        ItemStack open =
                new ItemStack(Material.SLIME_BALL);

        ItemMeta openMeta =
                open.getItemMeta();

        openMeta.setDisplayName(
                "§aOPEN"
        );

        open.setItemMeta(openMeta);

        inventory.setItem(46, open);

        ItemStack closed =
                new ItemStack(Material.REDSTONE);

        ItemMeta closedMeta =
                closed.getItemMeta();

        closedMeta.setDisplayName(
                "§cCLOSED"
        );

        closed.setItemMeta(closedMeta);

        inventory.setItem(47, closed);

        // =========================
        // CATEGORIAS
        // =========================

        ItemStack hack =
                new ItemStack(
                        Material.DIAMOND_SWORD
                );

        ItemMeta hackMeta =
                hack.getItemMeta();

        hackMeta.setDisplayName(
                "§cHACK"
        );

        hack.setItemMeta(hackMeta);

        inventory.setItem(50, hack);

        ItemStack chat =
                new ItemStack(Material.PAPER);

        ItemMeta chatMeta =
                chat.getItemMeta();

        chatMeta.setDisplayName(
                "§eCHAT"
        );

        chat.setItemMeta(chatMeta);

        inventory.setItem(51, chat);

        ItemStack abuse =
                new ItemStack(
                        Material.LAVA_BUCKET
                );

        ItemMeta abuseMeta =
                abuse.getItemMeta();

        abuseMeta.setDisplayName(
                "§4ABUSE"
        );

        abuse.setItemMeta(abuseMeta);

        inventory.setItem(52, abuse);

        // =========================
        // ABRIR MENU
        // =========================

        player.openInventory(inventory);

    }

}