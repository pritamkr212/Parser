package com.parser.Parser.parser.reportService;

import com.parser.Parser.parser.fileParserService.CSVFileParserServiceImpl;
import com.parser.Parser.parser.fileParserService.FileParserService;
import com.parser.Parser.parser.model.InputData;
import com.parser.Parser.parser.model.OutputData;
import com.parser.Parser.parser.model.ReferenceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final TaskScheduler taskScheduler;

    @Autowired
    public ReportServiceImpl(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    @Autowired
    private CSVFileParserServiceImpl service;

    @Override
    public byte[] processUploadedFiles(MultipartFile inputFile, MultipartFile referenceFile) throws IOException {
        List<InputData> inputDataList = service.parseFile(inputFile).stream().map(data -> {
            InputData inputData = new InputData();
            inputData.setField1(data.get("field1"));  // Assuming "field1" is the header name in your CSV
            inputData.setField2(data.get("field2"));  // Similarly, map other fields
            inputData.setField3(data.get("field3"));
            inputData.setField4(data.get("field4"));
            inputData.setField5(new BigDecimal(data.get("field5")));
            inputData.setRefkey1(data.get("refkey1"));
            inputData.setRefkey2(data.get("refkey2"));
            return inputData;
        }).collect(Collectors.toList());

        List<ReferenceData> referenceDataList = service.parseFile(referenceFile)
                .stream()
                .map(data -> {
                    ReferenceData referenceData = new ReferenceData();
                    referenceData.setRefkey1(data.get("refkey1"));
                    referenceData.setRefdata1(data.get("refdata1"));
                    referenceData.setRefkey2(data.get("refkey2"));
                    referenceData.setRefdata2(data.get("refdata2"));
                    referenceData.setRefdata3(data.get("refdata3"));
                    referenceData.setRefdata4(new BigDecimal(data.get("refdata4")));
                    return referenceData;
                })
                .collect(Collectors.toList());

        List<OutputData> outputDataList = transformData(inputDataList, referenceDataList);
        byte[] csvBytes = convertToCSVBytes(outputDataList);
        return csvBytes;
    }

    @Override
    public void scheduleReportGeneration(String cronExpression) {
        Runnable task = () -> {
            System.out.println("Scheduled report generation...");
        };
        taskScheduler.schedule(task, new CronTrigger(cronExpression));
    }

    private List<OutputData> transformData(List<InputData> inputDataList, List<ReferenceData> referenceDataList) {

        return inputDataList.stream()
                .map(inputData -> {
                    ReferenceData matchingReference = findMatchingReference(inputData, referenceDataList);
                    if (matchingReference != null) {
                        OutputData outputData = new OutputData();
                        outputData.setOutfield1(inputData.getField1() + inputData.getField2());
                        outputData.setOutfield2(matchingReference.getRefdata1());
                        outputData.setOutfield3(matchingReference.getRefdata2() + matchingReference.getRefdata3());
                        BigDecimal maxField5Refdata4 = inputData.getField5().max(matchingReference.getRefdata4());
                        outputData.setOutfield4(new BigDecimal(inputData.getField3()).multiply(maxField5Refdata4));
                        outputData.setOutfield5(maxField5Refdata4);
                        return outputData;
                    }
                    return null;
                })
                .filter(outputData -> outputData != null)
                .collect(Collectors.toList());
    }

    private ReferenceData findMatchingReference(InputData inputData, List<ReferenceData> referenceDataList) {
        return referenceDataList.stream()
                .filter(referenceData -> referenceData.getRefkey1().equals(inputData.getRefkey1())
                        && referenceData.getRefkey2().equals(inputData.getRefkey2()))
                .findFirst()
                .orElse(null);
    }

    private byte[] convertToCSVBytes(List<OutputData> outputDataList) {
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("outfield1,outfield2,outfield3,outfield4,outfield5\n");
        for (OutputData outputData : outputDataList) {
            csvBuilder.append(outputData.getOutfield1()).append(",")
                    .append(outputData.getOutfield2()).append(",")
                    .append(outputData.getOutfield3()).append(",")
                    .append(outputData.getOutfield4()).append(",")
                    .append(outputData.getOutfield5()).append("\n");
        }
        return csvBuilder.toString().getBytes();
    }
}