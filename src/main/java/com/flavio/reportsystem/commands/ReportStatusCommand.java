package com.flavio.reportsystem.commands;

import com.flavio.reportsystem.managers.ReportManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReportStatusCommand
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
                "reports.status"
        )) {

            sender.sendMessage(
                    "§cSem permissão."
            );

            return true;

        }

        // =========================
        // USAGE
        // =========================

        if (args.length < 2) {

            sender.sendMessage(
                    "§cUse: /reportstatus <id> <OPEN/CLOSED>"
            );

            return true;

        }

        String id =
                args[0];

        String status =
                args[1]
                        .toUpperCase();

        // =========================
        // VALID STATUS
        // =========================

        if (!status.equals("OPEN")
                && !status.equals("CLOSED")) {

            sender.sendMessage(
                    "§cStatus inválido."
            );

            return true;

        }

        // =========================
        // EXISTS
        // =========================

        if (reportManager.getConfig()
                .getConfigurationSection(
                        "reports"
                ) == null

                ||

                !reportManager.getConfig()
                        .contains(
                                "reports." + id
                        )) {

            sender.sendMessage(
                    "§cReport não encontrado."
            );

            return true;

        }

        // =========================
        // SET STATUS
        // =========================

        reportManager.getConfig().set(

                "reports."
                        + id
                        + ".status",

                status

        );

        reportManager.saveConfig();

        sender.sendMessage(

                "§aStatus do report #"
                        + id
                        + " alterado para §f"
                        + status

        );

        return true;

    }

}