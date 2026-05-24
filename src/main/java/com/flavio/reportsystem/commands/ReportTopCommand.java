package com.flavio.reportsystem.commands;

import com.flavio.reportsystem.managers.ReportManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportTopCommand
        implements CommandExecutor {

    private final ReportManager
            reportManager =
            new ReportManager();

    @Override
    public boolean onCommand(

            CommandSender sender,

            Command command,

            String label,

            String[] args

    ) {

        // =========================
        // PERMISSION
        // =========================

        if (!sender.hasPermission(
                "reports.top"
        )) {

            sender.sendMessage(
                    "§cSem permissão."
            );

            return true;

        }

        // =========================
        // SECTION
        // =========================

        ConfigurationSection section =
                reportManager.getConfig()
                        .getConfigurationSection(
                                "reports"
                        );

        if (section == null) {

            sender.sendMessage(
                    "§cNenhum report encontrado."
            );

            return true;

        }

        // =========================
        // MAP
        // =========================

        Map<String, Integer>
                reportCount =
                new HashMap<>();

        // =========================
        // LOOP
        // =========================

        for (String id :
                section.getKeys(false)) {

            String target =
                    section.getString(
                            id + ".target"
                    );

            reportCount.put(

                    target,

                    reportCount.getOrDefault(
                            target,
                            0
                    ) + 1

            );

        }

        // =========================
        // SORT
        // =========================

        List<Map.Entry<String, Integer>>
                sorted =
                new ArrayList<>(
                        reportCount.entrySet()
                );

        sorted.sort(

                (a, b) ->

                        b.getValue()
                                .compareTo(
                                        a.getValue()
                                )

        );

        // =========================
        // TITLE
        // =========================

        sender.sendMessage(
                "§6===== TOP REPORTADOS ====="
        );

        // =========================
        // SEND TOP
        // =========================

        int position = 1;

        for (Map.Entry<String, Integer>
                entry : sorted) {

            sender.sendMessage(

                    "§e#"
                            + position
                            + " §f"
                            + entry.getKey()
                            + " §7- §c"
                            + entry.getValue()
                            + " reports"

            );

            position++;

            if (position > 10) {

                break;

            }

        }

        return true;

    }

}