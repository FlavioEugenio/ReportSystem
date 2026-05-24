package com.flavio.reportsystem.managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ReportMenuManager {

    // =========================
    // CREATE BAN MENU
    // =========================

    public Inventory createBanConfirmMenu(
            String id
    ) {

        Inventory menu =
                Bukkit.createInventory(

                        null,

                        27,

                        "§4Confirmar Ban #" + id

                );

        // =========================
        // CONFIRM
        // =========================

        ItemStack confirm =
                new ItemStack(
                        Material.EMERALD_BLOCK
                );

        ItemMeta confirmMeta =
                confirm.getItemMeta();

        confirmMeta.setDisplayName(
                "§aCONFIRMAR BAN"
        );

        confirm.setItemMeta(
                confirmMeta
        );

        menu.setItem(
                11,
                confirm
        );

        // =========================
        // CANCEL
        // =========================

        ItemStack cancel =
                new ItemStack(
                        Material.REDSTONE_BLOCK
                );

        ItemMeta cancelMeta =
                cancel.getItemMeta();

        cancelMeta.setDisplayName(
                "§cCANCELAR"
        );

        cancel.setItemMeta(
                cancelMeta
        );

        menu.setItem(
                15,
                cancel
        );

        return menu;

    }

}