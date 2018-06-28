package csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVWriter {
    private String outputFilePath;
    private CSVPrinter csvPrinter;

    /**
     * @param outputFilePath file path to export
     * @param headers        optional params, values of csv header
     * @throws IOException
     */
    public CSVWriter(String outputFilePath, String... headers) throws IOException {
        this.outputFilePath = outputFilePath;
        Writer writer = Files.newBufferedWriter(Paths.get(outputFilePath));
        CSVFormat format = headers == null || headers.length > 0 ? CSVFormat.DEFAULT.withHeader(headers) : CSVFormat.DEFAULT;
        csvPrinter = new CSVPrinter(writer, format);
    }

    public void writeLine(Object... values) throws IOException {
        csvPrinter.printRecord(values);
    }

    /**
     * Flush data to file
     *
     * @throws IOException
     */
    public void export() throws IOException {
        csvPrinter.flush();
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }
}
