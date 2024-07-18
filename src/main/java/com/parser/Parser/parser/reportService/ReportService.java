package com.parser.Parser.parser.reportService;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ReportService {
    byte[] processUploadedFiles(MultipartFile inputFile, MultipartFile referenceFile) throws IOException;

    void scheduleReportGeneration(String cronExpression);
}
