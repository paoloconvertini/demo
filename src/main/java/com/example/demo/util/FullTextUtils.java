package com.example.demo.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.sax.BodyContentHandler;

import java.io.File;
import java.io.FileInputStream;

public class FullTextUtils {


    private static Log log = LogFactory.getLog(FullTextUtils.class);


    public static String textExtraction(File f) {
        try (FileInputStream fis = new FileInputStream(f)) {
            String documentContent;
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
//            if(StringUtils.equals(FilenameUtils.getExtension(f.getAbsolutePath()), "eml")){
//                Pkcs7Parser pkcs7Parser = new Pkcs7Parser();
//                pkcs7Parser.parse(fis, handler, metadata, new ParseContext());
//                //rimuovo gli spazi bianchi (ne sono lasciati parecchi da Tika)
//                documentContent = StringUtils.normalizeSpace(handler.toString());
//
//            } else {
                Tika tika = new Tika();
//                AutoDetectParser parser = new AutoDetectParser();
                documentContent = tika.parseToString(fis);
                //rimuovo gli spazi bianchi (ne sono lasciati parecchi da Tika)
                documentContent = StringUtils.normalizeSpace(documentContent);
//            }
//            String documentContent = tika.parseToString(fis);


            return documentContent;
        } catch (Exception e) {
            System.out.println(f.getName() + " >>  ERRORE"+ e);
        }
        return null;
    }
}
