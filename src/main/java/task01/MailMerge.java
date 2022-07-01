package task01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.crypto.Data;

public class MailMerge {
    public static void main(String[] args)throws Exception {
        File txtFile = new File(args[0]);
        File csvFile = new File(args[1]);
        String output = args[2];

        if(!txtFile.exists() || !txtFile.isFile()) {
            throw new IllegalArgumentException("File not readable " + txtFile);
        }
        if(!csvFile.exists() || !csvFile.isFile()) {
            throw new IllegalArgumentException("File not readable " + csvFile);
        }

        new MailMerge().merge(txtFile, csvFile, output);
    }

    private void merge(File txtFile, File dataFile, String output) throws Exception {

        Data data = new Data();
        data.read(dataFile);

        
        try (InputStream is = new FileInputStream(txtFile)) {
                try (OutputStream out = new FileOutputStream(output)) {
                    System.out.println(out);
                }
            }        
    }         
}
