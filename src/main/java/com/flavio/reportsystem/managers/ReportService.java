package com.flavio.reportsystem.managers;

import com.flavio.reportsystem.models.Report;

import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class ReportService {

    private final ReportManager
            reportManager =
            new ReportManager();

    private final ReportCache
            cache =
            new ReportCache();

    // =========================
    // CONSTRUCTOR
    // =========================

    public ReportService() {

        for (Report report :
                getReports()) {

            cache.addReport(report);

        }

    }

    // =========================
    // GET ALL REPORTS
    // =========================

    public List<Report> getReports() {

        List<Report> reports =
                new ArrayList<>();

        ConfigurationSection section =
                reportManager.getConfig()
                        .getConfigurationSection(
                                "reports"
                        );

        if (section == null) {

            return reports;

        }

        for (String id :
                section.getKeys(false)) {

            reports.add(
                    reportManager.getReport(id)
            );

        }

        return reports;

    }

    // =========================
    // GET OPEN REPORTS
    // =========================

    public List<Report> getOpenReports() {

        List<Report> reports =
                new ArrayList<>();

        for (Report report :
                getReports()) {

            if (report.getStatus()
                    .equalsIgnoreCase(
                            "OPEN"
                    )) {

                reports.add(report);

            }

        }

        return reports;

    }

    // =========================
    // GET CLOSED REPORTS
    // =========================

    public List<Report> getClosedReports() {

        List<Report> reports =
                new ArrayList<>();

        for (Report report :
                getReports()) {

            if (report.getStatus()
                    .equalsIgnoreCase(
                            "CLOSED"
                    )) {

                reports.add(report);

            }

        }

        return reports;

    }

    // =========================
    // GET REPORT BY ID
    // =========================

    public Report getReportById(
            String id
    ) {

        // =========================
        // CACHE
        // =========================

        Report cached =
                cache.getReport(id);

        if (cached != null) {

            return cached;

        }

        // =========================
        // YAML
        // =========================

        Report report =
                reportManager.getReport(id);

        // =========================
        // ADD CACHE
        // =========================

        if (report != null) {

            cache.addReport(report);

        }

        return report;

    }

}