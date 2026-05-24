package com.flavio.reportsystem.managers;

import com.flavio.reportsystem.ReportSystemPlugin;
import com.flavio.reportsystem.models.Report;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportManager {

    private final File file;

    private final FileConfiguration config;

    public ReportManager() {

        file =
                new File(
                        "plugins/ReportSystem/reports.yml"
                );

        if (!file.exists()) {

            try {

                file.createNewFile();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

        config =
                YamlConfiguration
                        .loadConfiguration(file);

    }

    // =========================
    // SALVAR REPORT
    // =========================

    public void saveReport(
            String player,
            String target,
            String category,
            String reason
    ) {

        int id =
                config.getConfigurationSection(
                        "reports"
                ) == null
                        ? 1
                        : config.getConfigurationSection(
                        "reports"
                ).getKeys(false).size() + 1;

        String date =
                LocalDateTime.now()
                        .format(
                                DateTimeFormatter
                                        .ofPattern(
                                                "dd/MM/yyyy HH:mm"
                                        )
                        );

        config.set(
                "reports." + id + ".player",
                player
        );

        config.set(
                "reports." + id + ".target",
                target
        );

        config.set(
                "reports." + id + ".category",
                category
        );

        config.set(
                "reports." + id + ".reason",
                reason
        );

        config.set(
                "reports." + id + ".date",
                date
        );

        config.set(
                "reports." + id + ".status",
                "OPEN"
        );

        saveConfig();

    }

    // =========================
    // VERIFICAR DUPLICADO
    // =========================

    public boolean hasOpenReport(
            String player,
            String target
    ) {

        ConfigurationSection reportsSection =
                config.getConfigurationSection(
                        "reports"
                );

        if (reportsSection == null) {

            return false;

        }

        for (String id :
                reportsSection.getKeys(false)) {

            String reportPlayer =
                    reportsSection.getString(
                            id + ".player"
                    );

            String reportTarget =
                    reportsSection.getString(
                            id + ".target"
                    );

            String status =
                    reportsSection.getString(
                            id + ".status"
                    );

            if (reportPlayer
                    .equalsIgnoreCase(player)
                    && reportTarget
                    .equalsIgnoreCase(target)
                    && status
                    .equalsIgnoreCase("OPEN")) {

                return true;

            }

        }

        return false;

    }

    // =========================
    // RESOLVER REPORT
    // =========================

    public void resolveReport(
            String id,
            String staff
    ) {

        config.set(
                "reports." + id + ".status",
                "CLOSED"
        );

        config.set(
                "reports." + id + ".resolved-by",
                staff
        );

        saveConfig();

    }

    // =========================
    // PEGAR REPORT
    // =========================

    public Report getReport(
            String id
    ) {

        return new Report(

                id,

                config.getString(
                        "reports." + id + ".player"
                ),

                config.getString(
                        "reports." + id + ".target"
                ),

                config.getString(
                        "reports." + id + ".category"
                ),

                config.getString(
                        "reports." + id + ".reason"
                ),

                config.getString(
                        "reports." + id + ".date"
                ),

                config.getString(
                        "reports." + id + ".status"
                )

        );

    }

    // =========================
    // SALVAR CONFIG
    // =========================

    public void saveConfig() {

        try {

            config.save(file);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    // =========================
    // GET CONFIG
    // =========================

    public FileConfiguration getConfig() {

        return config;

    }

}