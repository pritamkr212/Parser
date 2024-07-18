package com.parser.Parser.parser.reportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ReportScheduler {

    @Autowired
    private ReportService reportService;

    @Scheduled(cron = "${report.generation.cron}")
    public void generateScheduledReport() {
        // Example cron-scheduled method; adjust as needed
        System.out.println("Scheduled report generation...");
    }

    public void scheduleReportGeneration(MultipartFile inputFile, MultipartFile referenceFile, String cronExpression) throws IOException {
        // Process the uploaded files directly or save them as needed
        byte[] outputCsv = reportService.generateReportFromFiles(inputFile, referenceFile);
        // Schedule report generation based on cron expression
        // Implement your scheduling logic here
    }
}