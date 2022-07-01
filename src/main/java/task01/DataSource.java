package task01;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSource {

    private List<String> title = new ArrayList<>();
    private List<List<String>> input = new ArrayList<>();

    public void read(File dataFile)throws IOException{
        if(FileNameUtils.getExtension(dataFile.getName()).equalsignoreCase("csv")){
            readCSVFile(dataFile);
        } else{
            System.out.println("File is in wrong format!");
        }
    }

    private void readCSVFile(File csvFile)throws IOException {

        try(Reader read = new FileReader(csvFile)){
            CSVFormat csvf = CSVFormat.DEFAULT.
                withHeader().
                withDelimiter(',').
                withQuote('"').
                withCommentMarker((char)0).
                withIgnoreEmptyLines().
                withIgnoreSurroundingSpaces();

                try (CSVParser parser = new CSVParser(read, csvf)) {
                    Map<String, Integer> headerMap = parser.getHeaderMap();
                    Object log;
                    for(Map.Entry<String,Integer> entry : headerMap.entrySet()) {
                        title.add(entry.getKey());
                    }
    
                    List<CSVRecord> lines = parser.getRecords();
                    for(CSVRecord line : lines) {
                        List<String> data = new ArrayList<>();
                        for(int pos = 0;pos < headerMap.size();pos++) {
                            if(line.size() <= pos) {
                                data.add(null);
                            } else {
                                data.add(line.get(pos));
                            }
                        }
    
                        input.add(data);
                    }
                }
            }
        }

        public List<List<String>> getData() {
            return input;
        }

        public List<String> getHeaders() {
            return title;
        }
    
    }

