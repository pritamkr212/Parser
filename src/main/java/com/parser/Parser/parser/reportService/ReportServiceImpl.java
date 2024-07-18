package com.parser.Parser.parser.reportService;
;
import com.parser.Parser.parser.model.InputData;
import com.parser.Parser.parser.model.OutputData;
import com.parser.Parser.parser.model.ReferenceData;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final FileParser<InputData> inputParser;
    private final FileParser<ReferenceData> referenceParser;

    @Autowired
    public ReportServiceImpl(TaskScheduler taskScheduler, FileParser<InputData> inputParser, FileParser<ReferenceData> referenceParser) {
        this.taskScheduler = taskScheduler;
        this.inputParser = inputParser;
        this.referenceParser = referenceParser;
    }

    @Override
    public byte[] processUploadedFiles(MultipartFile inputFile, MultipartFile referenceFile) throws IOException {
        List<InputData> inputDataList = inputParser.parseFile(inputFile);
        List<ReferenceData> referenceDataList = referenceParser.parseFile(referenceFile);

        // Perform transformation
        List<OutputData> outputDataList = transformData(inputDataList, referenceDataList);

        // Convert outputDataList to CSV byte array (or other format as needed)
        byte[] csvBytes = convertToCSVBytes(outputDataList);

        return csvBytes;
    }

    @Override
    public void scheduleReportGeneration(String cronExpression) {
        Runnable task = () -> {
            // Implement scheduled report generation logic here
            System.out.println("Scheduled report generation...");
        };

        taskScheduler.schedule(task, new CronTrigger(cronExpression));
    }

    private List<OutputData> transformData(List<InputData> inputDataList, List<ReferenceData> referenceDataList) {
        // Implement transformation logic based on inputDataList and referenceDataList
        // Example transformation rules
        return inputDataList.stream()
                .map(inputData -> {
                    ReferenceData matchingReference = findMatchingReference(inputData, referenceDataList);
                    if (matchingReference != null) {
                        OutputData outputData = new OutputData();
                        outputData.setOutfield1(inputData.getField1() + inputData.getField2());
                        outputData.setOutfield2(matchingReference.getRefdata1());
                        outputData.setOutfield3(matchingReference.getRefdata2() + matchingReference.getRefdata3());
                        BigDecimal maxField5Refdata4 = inputData.getField5().max(matchingReference.getRefdata4());
                        outputData.setOutfield4(BigDecimal.valueOf(inputData.getField3()).multiply(maxField5Refdata4));
                        outputData.setOutfield5(maxField5Refdata4);
                        return outputData;
                    }
                    return null; // Handle case where no matching reference is found
                })
                .filter(outputData -> outputData != null) // Filter out null values
                .collect(Collectors.toList());
    }

    private ReferenceData findMatchingReference(InputData inputData, List<ReferenceData> referenceDataList) {
        // Implement logic to find matching reference data based on inputData
        return referenceDataList.stream()
                .filter(referenceData -> referenceData.getRefkey1().equals(inputData.getRefkey1())
                        && referenceData.getRefkey2().equals(inputData.getRefkey2()))
                .findFirst()
                .orElse(null);
    }

    private byte[] convertToCSVBytes(List<OutputData> outputDataList) {
        // Implement logic to convert List<OutputData> to CSV byte array
        // Example: Convert OutputData list to CSV byte array
        // You can use a library like Apache Commons CSV or any other CSV library

        // For demonstration, converting to simple CSV format
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