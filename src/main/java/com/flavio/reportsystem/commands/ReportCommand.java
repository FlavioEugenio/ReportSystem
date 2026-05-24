package com.flavio.reportsystem.commands;

import com.flavio.reportsystem.ReportSystemPlugin;

import com.flavio.reportsystem.managers.CooldownManager;
import com.flavio.reportsystem.managers.ReportManager;

import com.flavio.reportsystem.utils.DiscordWebhook;

import org.bukkit.Bukkit;
import org.bukkit.Sound;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

public class ReportCommand
        implements CommandExecutor {

    private final ReportManager
            reportManager =
            new ReportManager();

    private final CooldownManager
            cooldownManager =
            new CooldownManager();

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
        // USAGE
        // =========================

        if (args.length < 3) {

            player.sendMessage(
                    "§cUse: /report <player> <categoria> <motivo>"
            );

            player.sendMessage(
                    "§7Categorias:"
            );

            player.sendMessage(
                    "§cHACK §7- §eCHAT §7- §4ABUSE"
            );

            return true;

        }

        // =========================
        // TARGET
        // =========================

        Player target =
                Bukkit.getPlayer(args[0]);

        if (target == null) {

            player.sendMessage(
                    "§cJogador offline."
            );

            return true;

        }

        // =========================
        // SELF REPORT
        // =========================

        //if (target.getName()
              //  .equalsIgnoreCase(
            //           player.getName()
           //     )) {

          //  player.sendMessage(
           //        "§cVocê não pode se reportar."
          //  );

         //   return true;

      //  }

        // =========================
        // COOLDOWN
        // =========================

        if (cooldownManager.isInCooldown(
                player.getUniqueId()
        )) {

            player.sendMessage(

                    "§cEspere "
                            + cooldownManager
                            .getRemaining(
                                    player.getUniqueId()
                            )
                            + " segundos."

            );

            return true;

        }

        // =========================
        // CATEGORY
        // =========================

        String category =
                args[1].toUpperCase();

        if (!category.equals("HACK")
                &&
                !category.equals("CHAT")
                &&
                !category.equals("ABUSE")) {

            player.sendMessage(
                    "§cCategorias válidas:"
            );

            player.sendMessage(
                    "§cHACK §7- §eCHAT §7- §4ABUSE"
            );

            return true;

        }

        // =========================
        // REASON
        // =========================

        StringBuilder reason =
                new StringBuilder();

        for (int i = 2;
             i < args.length;
             i++) {

            reason.append(args[i])
                    .append(" ");

        }

        // =========================
        // BLACKLIST
        // =========================

        for (String blocked :

                ReportSystemPlugin
                        .getInstance()
                        .getConfig()
                        .getStringList(
                                "blacklist"
                        )) {

            if (reason.toString()
                    .toLowerCase()
                    .contains(
                            blocked.toLowerCase()
                    )) {

                player.sendMessage(
                        "§cMotivo inválido."
                );

                return true;

            }

        }

        // =========================
        // DUPLICATE
        // =========================

        if (reportManager
                .hasOpenReport(

                        player.getName(),

                        target.getName()

                )) {

            player.sendMessage(
                    "§cVocê já reportou esse jogador."
            );

            return true;

        }

        // =========================
        // SAVE REPORT
        // =========================

        reportManager.saveReport(

                player.getName(),

                target.getName(),

                category,

                reason.toString()

        );

        // =========================
        // COOLDOWN
        // =========================

        cooldownManager.setCooldown(
                player.getUniqueId()
        );

        // =========================
        // MESSAGE
        // =========================

        player.sendMessage(
                "§aReport enviado."
        );

        // =========================
        // SOUND
        // =========================

        player.playSound(

                player.getLocation(),

                Sound.LEVEL_UP,

                1F,

                1F

        );

        // =========================
        // WEBHOOK
        // =========================

        DiscordWebhook.sendMessage(

                "🚨 NOVO REPORT\n"

                        + "Reporter: "

                        + player.getName()

                        + "\nTarget: "

                        + target.getName()

                        + "\nCategoria: "

                        + category

                        + "\nReason: "

                        + reason

        );

        // =========================
        // STAFF ALERT
        // =========================

        for (Player online :
                Bukkit.getOnlinePlayers()) {

            if (!online.hasPermission(
                    "reports.staff"
            )) {

                continue;

            }

            online.sendMessage(

                    "§cNOVO REPORT §7| §f"

                            + target.getName()

                            + " §7- §e"

                            + category

            );

            online.playSound(

                    online.getLocation(),

                    Sound.NOTE_PLING,

                    1F,

                    1F

            );

        }

        return true;

    }

}