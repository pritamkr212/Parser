package com.parser.Parser.parser.web;

import com.parser.Parser.parser.reportService.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/generate")
    public String generateReport(
            @RequestParam("inputFile") MultipartFile inputFile,
            @RequestParam("referenceFile") MultipartFile referenceFile
    ) {
        try {
            reportService.processUploadedFiles(inputFile, referenceFile);
            return "Report generation initiated successfully.";
        } catch (IOException e) {
            return "Failed to initiate report generation: " + e.getMessage();
        }
    }

    @PostMapping("/schedule")
    public String scheduleReportGeneration(
            @RequestParam("inputFile") MultipartFile inputFile,
            @RequestParam("referenceFile") MultipartFile referenceFile,
            @RequestParam("cronExpression") String cronExpression
    ) {
        try {
            reportService.processUploadedFiles(inputFile, referenceFile);
            reportService.scheduleReportGeneration(cronExpression);
            return "Report scheduled successfully.";
        } catch (IOException e) {
            return "Failed to schedule report generation: " + e.getMessage();
        }
    }
}