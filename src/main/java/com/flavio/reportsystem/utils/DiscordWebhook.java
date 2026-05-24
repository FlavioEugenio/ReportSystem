package com.flavio.reportsystem.utils;

import com.flavio.reportsystem.ReportSystemPlugin;

import org.bukkit.Bukkit;

import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;

import java.nio.charset.StandardCharsets;

public class DiscordWebhook {

    // =========================
    // SEND MESSAGE
    // =========================

    public static void sendMessage(
            String message
    ) {

        // =========================
        // CONFIG
        // =========================

        boolean enabled =
                ReportSystemPlugin
                        .getInstance()
                        .getConfig()
                        .getBoolean(
                                "webhook.enabled"
                        );

        String webhookUrl =
                ReportSystemPlugin
                        .getInstance()
                        .getConfig()
                        .getString(
                                "webhook.url"
                        );

        // =========================
        // CHECK
        // =========================

        if (!enabled) {

            return;

        }

        if (webhookUrl == null
                || webhookUrl.isEmpty()) {

            Bukkit.getConsoleSender()
                    .sendMessage(
                            "§c[ReportSystem] Webhook inválida."
                    );

            return;

        }

        // =========================
        // ASYNC
        // =========================

        Bukkit.getScheduler()
                .runTaskAsynchronously(

                        ReportSystemPlugin
                                .getInstance(),

                        () -> {

                            try {

                                // =========================
                                // URL
                                // =========================

                                URL url =
                                        new URL(
                                                webhookUrl
                                        );

                                HttpURLConnection connection =
                                        (HttpURLConnection)
                                                url.openConnection();

                                connection.setRequestMethod(
                                        "POST"
                                );

                                connection.setRequestProperty(
                                        "Content-Type",
                                        "application/json"
                                );

                                connection.setRequestProperty(
                                        "User-Agent",
                                        "ReportSystem"
                                );

                                connection.setDoOutput(
                                        true
                                );

                                connection.setConnectTimeout(
                                        5000
                                );

                                connection.setReadTimeout(
                                        5000
                                );

                                // =========================
                                // SAFE MESSAGE
                                // =========================

                                String safeMessage =
                                        message

                                                .replace(
                                                        "\"",
                                                        "\\\""
                                                )

                                                .replace(
                                                        "\n",
                                                        "\\n"
                                                );

                                // =========================
                                // JSON
                                // =========================

                                String json =
                                        "{"
                                                + "\"content\":\""
                                                + safeMessage
                                                + "\""
                                                + "}";

                                // =========================
                                // SEND
                                // =========================

                                OutputStream stream =
                                        connection.getOutputStream();

                                stream.write(

                                        json.getBytes(
                                                StandardCharsets.UTF_8
                                        )

                                );

                                stream.flush();

                                stream.close();

                                // =========================
                                // RESPONSE
                                // =========================

                                int responseCode =
                                        connection.getResponseCode();

                                if (responseCode != 204
                                        && responseCode != 200) {

                                    Bukkit.getConsoleSender()
                                            .sendMessage(

                                                    "§c[ReportSystem] Erro webhook: "
                                                            + responseCode

                                            );

                                }

                                connection.disconnect();

                            } catch (Exception e) {

                                Bukkit.getConsoleSender()
                                        .sendMessage(

                                                "§c[ReportSystem] Falha ao enviar webhook."

                                        );

                                e.printStackTrace();

                            }

                        }

                );

    }

}