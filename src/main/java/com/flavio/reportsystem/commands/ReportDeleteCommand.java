package com.flavio.reportsystem.commands;

import com.flavio.reportsystem.managers.ReportManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReportDeleteCommand
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
                "reports.delete"
        )) {

            sender.sendMessage(
                    "§cSem permissão."
            );

            return true;

        }

        // =========================
        // USAGE
        // =========================

        if (args.length == 0) {

            sender.sendMessage(
                    "§cUse: /reportdelete <id>"
            );

            return true;

        }

        String id =
                args[0];

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
        // DELETE
        // =========================

        reportManager.getConfig().set(

                "reports." + id,

                null

        );

        reportManager.saveConfig();

        sender.sendMessage(

                "§aReport #"
                        + id
                        + " deletado."

        );

        return true;

    }

}