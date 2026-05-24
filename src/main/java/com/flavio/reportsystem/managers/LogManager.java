package com.flavio.reportsystem.managers;

import com.flavio.reportsystem.ReportSystemPlugin;

import org.bukkit.Bukkit;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogManager {

    private final File file;

    private final FileConfiguration config;

    public LogManager() {

        file =
                new File(
                        "plugins/ReportSystem/logs.yml"
                );

        // =========================
        // CREATE FILE
        // =========================

        if (!file.exists()) {

            try {

                file.createNewFile();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

        // =========================
        // LOAD CONFIG
        // =========================

        config =
                YamlConfiguration
                        .loadConfiguration(file);

    }

    // =========================
    // ADD LOG
    // =========================

    public void addLog(

            String action,

            String staff,

            String target

    ) {

        int id =
                config.getConfigurationSection(
                        "logs"
                ) == null

                        ? 1

                        : config.getConfigurationSection(
                        "logs"
                ).getKeys(false).size() + 1;

        String date =
                LocalDateTime.now()
                        .format(

                                DateTimeFormatter
                                        .ofPattern(
                                                "dd/MM/yyyy HH:mm"
                                        )

                        );

        // =========================
        // SAVE
        // =========================

        config.set(
                "logs." + id + ".action",
                action
        );

        config.set(
                "logs." + id + ".staff",
                staff
        );

        config.set(
                "logs." + id + ".target",
                target
        );

        config.set(
                "logs." + id + ".date",
                date
        );

        saveConfig();

    }

    // =========================
    // SAVE CONFIG
    // =========================

    public void saveConfig() {

        Bukkit.getScheduler()
                .runTaskAsynchronously(

                        ReportSystemPlugin
                                .getInstance(),

                        () -> {

                            try {

                                config.save(file);

                            } catch (IOException e) {

                                e.printStackTrace();

                            }

                        }

                );

    }

    // =========================
    // GET CONFIG
    // =========================

    public FileConfiguration getConfig() {

        return config;

    }

}