package com.example.demo;

import com.example.demo.model.Persona;
import com.itextpdf.text.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
public class DemoApplication {
    private static final String EOL = "\n"; //it was like this System.getProperty("line.separator"); but it offends Fortify analysis
    private static final String TO_BE_PROCESSED = "/Users/pconvertini/IdeaProjects/demo/src/main/resources/resources/toBeProcessd";
    private static final String PROCESSED = "/Users/pconvertini/IdeaProjects/demo/src/main/resources/resources/processed";


    public static void main(String[] args) throws IOException, DocumentException {


        File srcFolder = new File(TO_BE_PROCESSED);
        File destFolder = new File(PROCESSED);
        File[] files = srcFolder.listFiles();
        if(files == null || files.length == 0) {
            //FIXME cambiare questa parte con il return boolean = false
            System.out.println("Folder vuoto");
            return;
        }
        //TODO rendere tutto configurabile
        List<File> pdfFiles = Arrays.stream(files)
                .filter(f -> StringUtils.startsWith(f.getName(), "FF-")
                        && StringUtils.endsWith(f.getName(), ".pdf"))
                .collect(Collectors.toList());

         /*
        * Per ogni file trovato, vengono effettuati i seguenti controlli:
            esiste nella stessa cartella un file .txt con lo stesso nome? --> ignoro il file e vado al successivo
            esiste su documentale una fattura fornitore che abbia come barcode esterno o come barcode interno il numero riportato nel nome del file dopo i caratteri "FF-"?
            se no --> ignoro il file e vado al successivo
            se sì --> sposto il file nella cartella "processed"

         */
        for (File file : pdfFiles) {
            //TODO da capire come recpuerare il tipo documento per caricare le sysConfig
            String name = StringUtils.substringBefore(file.getName(), ".pdf") + ".txt";
            boolean isTxt = Arrays.stream(files)
                    .anyMatch(f -> StringUtils.equals(name, f.getName()) && StringUtils.endsWith(f.getName(), ".txt"));
            if(isTxt){
                System.out.println("Ho trovato il txt. Il documento deve ancora essere processato. Vado avanti con il successivo docuemnto!");
                continue;
            }

            String searchValue = StringUtils.substringAfter(name, "FF-");
            // Recupero sysConfig metadati. Questi sono i metadati per cercare in qdoc2 il documento

            String searchMeta = "sapBarcode";

            // TODO chiamare la search di qdoc2
            String searchQuery = searchMeta + ":" + searchValue;
            Persona persona = new Persona(); //FIXME sostituire con il model di qdoc2 document
            if(persona == null){
                System.out.println("Non ho trovato il documento nella documentale. Vado avanti con il successivo docuemnto! ");
            } else {
                try {
                    File destFile = new File(destFolder + "/" + file);
                    FileUtils.moveFile(file, destFile);
                } catch (Exception e) {
                    System.out.println("Error moving file " + name);
                }
            }

        }


    }


}
