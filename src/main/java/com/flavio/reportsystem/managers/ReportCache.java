package com.flavio.reportsystem.managers;

import com.flavio.reportsystem.models.Report;

import java.util.HashMap;
import java.util.Map;

public class ReportCache {

    private final Map<String, Report>
            cache =
            new HashMap<>();

    // =========================
    // ADD REPORT
    // =========================

    public void addReport(
            Report report
    ) {

        cache.put(
                report.getId(),
                report
        );

    }

    // =========================
    // GET REPORT
    // =========================

    public Report getReport(
            String id
    ) {

        return cache.get(id);

    }

    // =========================
    // REMOVE REPORT
    // =========================

    public void removeReport(
            String id
    ) {

        cache.remove(id);

    }

    // =========================
    // GET ALL
    // =========================

    public Map<String, Report>
    getReports() {

        return cache;

    }

}