//package com.example.demo.util;
//
//import it.quix.framework.core.model.Template;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import com.example.demo.view.InvioAllegatoMailPropertiesView;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Properties;
//
//public class InvioAllegatoMailProperties {
//
//    private static final long serialVersionUID = -6858104983718984272L;
//
//    private static final Log log = LogFactory.getLog(InvioAllegatoMailProperties.class);
//
//
//    InputStream inputStream;
//
//    public List<InvioAllegatoMailPropertiesView> getPropValues() throws IOException {
//        List<InvioAllegatoMailPropertiesView> list = new ArrayList<>();
//        try {
//            Properties prop = new Properties();
//            String propFileName = "resources/invioAllegatiMailJob.properties";
//
//            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
//
//            if (inputStream != null) {
//                prop.load(inputStream);
//            } else {
//                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
//            }
//            Template templateSubject = new Template();
//            Template templateBody = new Template();
//            Integer numRegole = Integer.valueOf(prop.getProperty("num.regole"));
//            for (int i = 1; i <= numRegole; i++) {
//                // get the property value and print it out
//                String id = prop.getProperty("id.regola." + i);
//                String qery = prop.getProperty("qery.documentale." + i);
//                if(prop.getProperty("template.oggetto.mail." + i).contains(";")){
//                    String[] strings = prop.getProperty("template.oggetto.mail." + i).split(";");
//                    templateSubject.setApplication(strings[0]);
//                    templateSubject.setOrganization(strings[1]);
//                    templateSubject.setLanguage(strings[2]);
//                    templateSubject.setName(strings[3]);
//                }
//                if(prop.getProperty("template.body.mail." + i).contains(";")){
//                    String[] strings = prop.getProperty("template.body.mail." + i).split(";");
//                    templateBody.setApplication(strings[0]);
//                    templateBody.setOrganization(strings[1]);
//                    templateBody.setLanguage(strings[2]);
//                    templateBody.setName(strings[3]);
//                }
//                Boolean zipYN = "Y".equals(prop.getProperty("invia.zipYN." + i));
//                Boolean mailMassiveYN = "Y".equals(prop.getProperty("massivaYN." + i));
//                List<String> toList = new ArrayList<>();
//                if(prop.getProperty("destinatari.to." + i).contains(";")){
//                    String[] strings = prop.getProperty("destinatari.to." + i).split(";");
//                    toList.addAll(Arrays.asList(strings));
//                } else {
//                    toList.add(prop.getProperty("destinatari.to." + i));
//                }
//                List<String>  ccList = new ArrayList<>();
//                if(prop.getProperty("destinatari.cc." + i).contains(";")){
//                    String[] strings = prop.getProperty("destinatari.cc." + i).split(";");
//                    ccList.addAll(Arrays.asList(strings));
//                } else {
//                    ccList.add(prop.getProperty("destinatari.cc." + i));
//                }
//                List<String>  bccList = new ArrayList<>();
//                if(prop.getProperty("destinatari.bcc." + i).contains(";")){
//                    String[] strings = prop.getProperty("destinatari.bcc." + i).split(";");
//                    bccList.addAll(Arrays.asList(strings));
//                } else {
//                    bccList.add(prop.getProperty("destinatari.bcc." + i));
//                }
//                String mittente = prop.getProperty("mittente." + i);
//                String replyTo = prop.getProperty("replyTo." + i);
//
//                InvioAllegatoMailPropertiesView propertiesView =
//                        new InvioAllegatoMailPropertiesView(id, qery, templateSubject, templateBody, zipYN, mailMassiveYN, toList, ccList, bccList, mittente, replyTo);
//                list.add(propertiesView);
//            }
//        } catch (Exception e) {
//            log.debug("Errore nel recupero delle properties per invioAllegatiMailJob" , e);
//        } finally {
//            inputStream.close();
//        }
//        return list;
//    }
//}
