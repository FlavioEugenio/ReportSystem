package com.flavio.reportsystem.models;

public class Report {

    private final String id;

    private final String player;

    private final String target;

    private final String category;

    private final String reason;

    private final String date;

    private final String status;

    public Report(

            String id,

            String player,

            String target,

            String category,

            String reason,

            String date,

            String status

    ) {

        this.id = id;

        this.player = player;

        this.target = target;

        this.category = category;

        this.reason = reason;

        this.date = date;

        this.status = status;

    }

    // =========================
    // GETTERS
    // =========================

    public String getId() {

        return id;

    }

    public String getPlayer() {

        return player;

    }

    public String getTarget() {

        return target;

    }

    public String getCategory() {

        return category;

    }

    public String getReason() {

        return reason;

    }

    public String getDate() {

        return date;

    }

    public String getStatus() {

        return status;

    }

}