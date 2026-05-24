package com.flavio.reportsystem.listeners;

import com.flavio.reportsystem.commands.ReportsCommand;
import com.flavio.reportsystem.managers.ReportManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ReportMenuListener implements Listener {

    private final ReportsCommand reportsCommand =
            new ReportsCommand();

    private final ReportManager reportManager =
            new ReportManager();

    @EventHandler
    public void onInventoryClick(
            InventoryClickEvent event
    ) {

        // =========================
        // MENU REPORTS
        // =========================

        if (event.getView()
                .getTitle()
                .startsWith("§cReports")) {

            event.setCancelled(true);

            if (event.getCurrentItem() == null) {

                return;

            }

            if (!event.getCurrentItem()
                    .hasItemMeta()) {

                return;

            }

            if (!event.getCurrentItem()
                    .getItemMeta()
                    .hasDisplayName()) {

                return;

            }

            Player player =
                    (Player) event.getWhoClicked();

            String itemName =
                    event.getCurrentItem()
                            .getItemMeta()
                            .getDisplayName();

            // =========================
            // FILTROS
            // =========================

            if (itemName.equals("§eTodos")) {

                reportsCommand.openReportsMenu(
                        player,
                        1,
                        "ALL",
                        "ALL"
                );

                return;

            }

            if (itemName.equals("§aOPEN")) {

                reportsCommand.openReportsMenu(
                        player,
                        1,
                        "OPEN",
                        "ALL"
                );

                return;

            }

            if (itemName.equals("§cCLOSED")) {

                reportsCommand.openReportsMenu(
                        player,
                        1,
                        "CLOSED",
                        "ALL"
                );

                return;

            }

            // =========================
            // CATEGORIAS
            // =========================

            if (itemName.equals("§cHACK")) {

                reportsCommand.openReportsMenu(
                        player,
                        1,
                        "ALL",
                        "HACK"
                );

                return;

            }

            if (itemName.equals("§eCHAT")) {

                reportsCommand.openReportsMenu(
                        player,
                        1,
                        "ALL",
                        "CHAT"
                );

                return;

            }

            if (itemName.equals("§4ABUSE")) {

                reportsCommand.openReportsMenu(
                        player,
                        1,
                        "ALL",
                        "ABUSE"
                );

                return;

            }

            // =========================
            // ABRIR REPORT
            // =========================

            if (!itemName.startsWith(
                    "§cReport #"
            )) {

                return;

            }

            String id =
                    itemName.replace(
                            "§cReport #",
                            ""
                    );

            String reporter =
                    reportManager.getConfig()
                            .getString(
                                    "reports." + id + ".player"
                            );

            String target =
                    reportManager.getConfig()
                            .getString(
                                    "reports." + id + ".target"
                            );

            String category =
                    reportManager.getConfig()
                            .getString(
                                    "reports." + id + ".category"
                            );

            String reason =
                    reportManager.getConfig()
                            .getString(
                                    "reports." + id + ".reason"
                            );

            String date =
                    reportManager.getConfig()
                            .getString(
                                    "reports." + id + ".date"
                            );

            String status =
                    reportManager.getConfig()
                            .getString(
                                    "reports." + id + ".status"
                            );

            Inventory menu =
                    Bukkit.createInventory(
                            null,
                            27,
                            "§cReport #" + id
                    );

            // =========================
            // INFO
            // =========================

            ItemStack info =
                    new ItemStack(Material.PAPER);

            ItemMeta infoMeta =
                    info.getItemMeta();

            infoMeta.setDisplayName(
                    "§eInformações"
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

            infoMeta.setLore(lore);

            info.setItemMeta(infoMeta);

            menu.setItem(13, info);

            // =========================
            // RESOLVER
            // =========================

            ItemStack resolve =
                    new ItemStack(
                            Material.EMERALD_BLOCK
                    );

            ItemMeta resolveMeta =
                    resolve.getItemMeta();

            resolveMeta.setDisplayName(
                    "§aResolver Report"
            );

            resolve.setItemMeta(resolveMeta);

            menu.setItem(11, resolve);

            // =========================
            // DELETAR
            // =========================

            ItemStack delete =
                    new ItemStack(
                            Material.REDSTONE_BLOCK
                    );

            ItemMeta deleteMeta =
                    delete.getItemMeta();

            deleteMeta.setDisplayName(
                    "§cDeletar Report"
            );

            delete.setItemMeta(deleteMeta);

            menu.setItem(15, delete);

            player.openInventory(menu);

            return;

        }

        // =========================
        // MENU REPORT
        // =========================

        if (event.getView()
                .getTitle()
                .startsWith("§cReport #")) {

            event.setCancelled(true);

            if (event.getCurrentItem() == null) {

                return;

            }

            if (!event.getCurrentItem()
                    .hasItemMeta()) {

                return;

            }

            if (!event.getCurrentItem()
                    .getItemMeta()
                    .hasDisplayName()) {

                return;

            }

            Player player =
                    (Player) event.getWhoClicked();

            String itemName =
                    event.getCurrentItem()
                            .getItemMeta()
                            .getDisplayName();

            String title =
                    event.getView()
                            .getTitle();

            String id =
                    title.replace(
                            "§cReport #",
                            ""
                    );

            // =========================
            // RESOLVER
            // =========================

            if (itemName.equals(
                    "§aResolver Report"
            )) {

                reportManager.getConfig().set(
                        "reports." + id + ".status",
                        "CLOSED"
                );

                reportManager.saveConfig();

                player.sendMessage(
                        "§aReport resolvido."
                );

                player.closeInventory();

                return;

            }

            // =========================
            // DELETAR
            // =========================

            if (itemName.equals(
                    "§cDeletar Report"
            )) {

                reportManager.getConfig().set(
                        "reports." + id,
                        null
                );

                reportManager.saveConfig();

                player.sendMessage(
                        "§cReport deletado."
                );

                player.closeInventory();

            }

        }

    }

}