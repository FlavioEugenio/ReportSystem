package com.flavio.reportsystem;

import com.flavio.reportsystem.commands.ReportCommand;
import com.flavio.reportsystem.commands.ReportDeleteCommand;
import com.flavio.reportsystem.commands.ReportLogsCommand;
import com.flavio.reportsystem.commands.ReportsCommand;
import com.flavio.reportsystem.commands.ReportStatusCommand;
import com.flavio.reportsystem.commands.ReportTopCommand;

import com.flavio.reportsystem.listeners.ReportMenuListener;

import com.flavio.reportsystem.managers.ReportManager;

import org.bukkit.plugin.java.JavaPlugin;

public class ReportSystemPlugin
        extends JavaPlugin {

    private static
    ReportSystemPlugin instance;

    @Override
    public void onEnable() {

        instance = this;

        // =========================
        // CONFIG
        // =========================

        saveDefaultConfig();

        // =========================
        // LOAD FILES
        // =========================

        new ReportManager();

        // =========================
        // CONSOLE
        // =========================

        getLogger().info(
                "Plugin de reports ativado!"
        );

        // =========================
        // COMMANDS
        // =========================

        getCommand("report")
                .setExecutor(
                        new ReportCommand()
                );

        getCommand("reports")
                .setExecutor(
                        new ReportsCommand()
                );

        getCommand("reportdelete")
                .setExecutor(
                        new ReportDeleteCommand()
                );

        getCommand("reportstatus")
                .setExecutor(
                        new ReportStatusCommand()
                );

        getCommand("reportlogs")
                .setExecutor(
                        new ReportLogsCommand()
                );

        getCommand("reporttop")
                .setExecutor(
                        new ReportTopCommand()
                );

        // =========================
        // LISTENERS
        // =========================

        getServer()
                .getPluginManager()
                .registerEvents(

                        new ReportMenuListener(),

                        this

                );

    }

    @Override
    public void onDisable() {

        getLogger().info(
                "Plugin de reports desativado!"
        );

    }

    // =========================
    // INSTANCE
    // =========================

    public static
    ReportSystemPlugin
    getInstance() {

        return instance;

    }

}