package com.example.demo.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

//
//import com.example.demo.model.QflowHistory;
//import com.example.demo.view.InvioAllegatoMailPropertiesView;
//import org.apache.http.entity.ContentType;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.core.io.InputStreamSource;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
public class Utils {

    private static final String key = "m9MBpaTm84zKuIHuelzXYKg5qM9jjn88hApexibOAog";
    private static final String initVector = "8C9W6SwxzxC8gpzSI4opDQ";


    public static String encrypt(String value) {
//        try {
//
////            byte[] bytes = Base64.decodeBase64(key);
////            String s1 = StringUtils.newStringUtf8(bytes);
////            java.util.Base64.Decoder urlDecoder = java.util.Base64.getUrlDecoder();
////            byte[] decodeKey = urlDecoder.decode(key.getBytes("UTF-8"));
////            String s = decodeKey.toString();
////            byte[] vector = urlDecoder.decode(initVector.getBytes("UTF-8"));
////            IvParameterSpec iv = new IvParameterSpec(vector);
////            SecretKeySpec skeySpec = new SecretKeySpec(decodeKey, "AES");
////
////            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
////            int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
////            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//
////            byte[] params = value.getBytes("UTF-8");
////            byte[] message = generateKey(params, 64);
////            byte[] encrypted = cipher.doFinal(message);
////            return Base64.encodeBase64String(encrypted);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        return null;
    }


    /**
     * Method for creation of valid byte array from key
     */
    static byte[] generateKey(byte[] key, int lenght) throws UnsupportedEncodingException {
        byte[] keyBytes = new byte[lenght];
        int len = key.length;

        if (len > keyBytes.length) {
            len = keyBytes.length;
        }

        System.arraycopy(key, 0, keyBytes, 0, len);
        return keyBytes;
    }

//
//
//    public static List<Map<String, Object>> managegeHits(Map<String, Object> returnMap, String id) {
//        List<Map<String, Object>> list = (List<Map<String, Object>>) returnMap.get("hits");
//        Iterator<Map<String, Object>> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            Map<String, Object> map = iterator.next();
//            Map<String, Object> sourceMap = (Map<String, Object>) map.get("_source");
//            if (sourceMap.containsKey("notification-" + id)) {
//                System.out.println(sourceMap.get("notification-" + id));
//                iterator.remove();
//            }
//        }
//        return list;
//    }
//
//    public static  List<Map<String, Object>> getFileListFromElasticResponse(List<Map<String, Object>> hitsMap) throws Exception {
//        List<Map<String, Object>> updatingMapList = new ArrayList<>();
//
////        List<File> listFiles = new ArrayList<>();
//        for (Map<String, Object> hits : hitsMap) {
//            Map<String, Object> map = new HashMap<>();
//            Map<String, Object> _source = (Map<String, Object>) hits.get("_source");
//            String source = (String) _source.get("source");
//            String uuid = (String) _source.get("uuid");
//            String name = (String) _source.get("name");
//            String id = (String) hits.get("_id");
//            String docType = (String) hits.get("_type");
//            String referenceNumber = (String) _source.get("id");
//            String refDateStr = (String) _source.get("referenceDate");
//            Date referenceDate = null;
//            if (StringUtils.isNotEmpty(refDateStr)) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//                referenceDate = sdf.parse(refDateStr);
//            }
//            String organization = (String) _source.get("organizationCode");
//            String fileSystemDocumentPath = "MA005_FileSystemDocumentPath";
//            String subPath = getDocumentSubPath(organization, docType, name, referenceDate);
//            File document = new File(fileSystemDocumentPath + subPath);
//            File outputFile = FileManagerUtils.createFolder("download", name, name);
//            FileUtils.copyFile(document, outputFile);
//            log.debug("Download file " + document.getAbsolutePath());
//            map.put("file", outputFile);
//            map.put("id", id);
//            map.put("type", docType);
//            map.put("_source", _source);
//            updatingMapList.add(map);
//
//        }
//        return updatingMapList;
//    }
//
//    /** PRIVATE METHODS **/
//    private static String getDocumentSubPath(String organization, String type, String name, Date documentDate) {
//        Calendar now = Calendar.getInstance();
//        if (documentDate != null) {
//            now.setTime(documentDate);
//        }
//        int year = now.get(Calendar.YEAR);
//        int month = now.get(Calendar.MONTH) + 1;
//        int day = now.get(Calendar.DATE);
//        return "/" + year + "/" + organization + "/" + type + "/" + month + "/" + day + "/" + name;
//    }
//
//    public static List<MailHandlerImpl.InputStreamSourceContentType> setAttachementFiles(Boolean archive, File fileSystemTempDir, List<Map<String, Object>> mapList) throws Exception {
//        List<MailHandlerImpl.InputStreamSourceContentType> attachmentFiles = new ArrayList<>();
//        List<File> listFiles = new ArrayList<>();
//        for (Map<String, Object> map : mapList) {
//            File file = (File) map.get("file");
//            listFiles.add(file);
//        }
//        if (archive) {
//            // genero lo zip a partire dalla lista file
//            File tempZip = File.createTempFile("ZipFile", ".zip", fileSystemTempDir);
//            ZipUtils.zip(listFiles.toArray(new File[listFiles.size()]), tempZip);
//            for (File file : listFiles) {
//                DestroyFileAndFolderInputStream stream = new DestroyFileAndFolderInputStream(file);
//                stream.close();
//            }
//            MailHandlerImpl.InputStreamSourceContentType issct = getInputStreamSourceContentType(tempZip);
//            attachmentFiles.add(issct);
//            boolean delete = tempZip.delete();
//
//            System.out.print(delete?"ok":"non eliminato");
//        } else {
//            for (File file : listFiles) {
//                MailHandlerImpl.InputStreamSourceContentType issct = getInputStreamSourceContentType(file);
//                attachmentFiles.add(issct);
//                DestroyFileAndFolderInputStream stream = new DestroyFileAndFolderInputStream(file);
//                stream.close();
//            }
//        }
//        return attachmentFiles;
//    }
//
//
//    public static void updatingDocument(InvioAllegatoMailPropertiesView propValue, Mail mail, Map<String, Object> map) throws IOException {
//        if (org.apache.commons.lang.StringUtils.isNotEmpty(mail.getMailId())) {
//            String mailId = mail.getMailId();
//            Date mailSendDatetime = mail.getSendDatetime();
//            Map<String, Object> updateMap = new HashMap<>();
//            Map<String, Object> docMap = new HashMap<>();
//            Map<String, String> notificationMap = new HashMap<>();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//            notificationMap.put("sendDate", sdf.format(mailSendDatetime));
//            notificationMap.put("idFrmkMail", mailId);
//            docMap.put("notification-" + propValue.getId(), notificationMap);
//            updateMap.put("doc", docMap);
//            String json = new JSONSerializer().deepSerialize(updateMap);
//            String updatingUrl = "http://10.236.0.124:9200/documents/" + map.get("type") + "/" + map.get("id") + "/_update";
//            Request.Post(updatingUrl).addHeader("Content-type", "application/json").bodyString(json, ContentType.APPLICATION_JSON).execute();
//        }
//    }
//
//    public static MailHandlerImpl.InputStreamSourceContentType getInputStreamSourceContentType(File file) throws IOException {
//        InputStreamSource iss = new ByteArrayResource(IOUtils.toByteArray(new FileInputStream(file)));
//        return new MailHandlerImpl.InputStreamSourceContentType("application/octet-stream", iss, file.getName());
//    }
//
//    public static Boolean copyFileAndResizeImg(File img) {
//        try {
//            String percorsoBaseDestinazione = "C:/QData/designer/destinazione";
//            log.info("Recuperata la configurazione al percorso base su file system DSG0013_destinazioneTestataDsgPath: " + percorsoBaseDestinazione);
//            String parentFileName = img.getParentFile().getName();
//
//            String percorsoDestinazione = percorsoBaseDestinazione + "/" + parentFileName;
//
//
//            File copy = new File(percorsoDestinazione + "/" + img.getName());
//            try {
//                log.info("Spostamento immagine Testata nella cartella " + percorsoDestinazione);
//                FileUtils.copyFile(img, copy);
//                log.info("Immagine Testata spostata con successo");
//            } catch (IOException e) {
//                String msg = "Impossibile spostare il file " + img.getName() + " nella cartella " + percorsoDestinazione;
//                log.warn(msg);
//                log.trace(e);
//                return false;
//            }
//            String[] qualitaList = {"L", "M", "S"};
//            for (String qualita : qualitaList) {
//                try {
//                    log.info("Resize immagine a dimensione " + qualita);
//                    resizeImageByQuality(img, percorsoDestinazione, qualita);
//                } catch (IOException e) {
//                    String msg = "Impossibile salvare l'immagine " + img.getName() + " ridimensionata" + qualita + " nel percorso " + percorsoDestinazione;
//                    log.warn(msg);
//                    log.trace(e);
//                    break;
//                }
//            }
//            return true;
//        } catch (Exception e) {
//            String msg = "Errore nel recuperare la configurazione dal databse: designer, DEFAULT, DSG0013_destinazioneTestataDsgPath";
//            log.error(msg, e);
//            return false;
//        }
//    }
//
//
//    public static Long getDateDiff(Date start, Date end) {
//        Calendar calendarStart = Calendar.getInstance();
//        calendarStart.setTime(start);
//        Calendar calendarEnd = Calendar.getInstance();
//        calendarEnd.setTime(end);
//        long calendarStartTimeInMillis = calendarStart.getTimeInMillis();
//        long calendarEndTimeInMillis = calendarEnd.getTimeInMillis();
//        long diff = calendarEndTimeInMillis - calendarStartTimeInMillis;
//        return diff / (60000);
//    }
//
//    public static String convertBooleanToString(Boolean presenza) {
//        String s = "";
//        if (presenza) {
//            s = "Sì";
//        } else {
//            s = "No";
//        }
//        return s;
//    }
//
//    public static String resizeImageByQuality(File img, String imgDirPath, String quality) throws IOException {
//        return resizeImageByQuality(img, imgDirPath, quality, false);
//    }
//
//    /**
//     * METODO USATO PER EFFETTUARE IL RESIZE DI UNA IMMAGINE DI QUALITA' DI PARTENZA "XL" IN UNA FINALE TRA "L", "M" O "S" PASSATA IN FIRMA.<BR>
//     * IL METODO RITORNA IL PATH ASSOLUTO DELL'IMMAGINE.<BR>
//     * NEL CASO NON SIA STATO IN GRADO DI CREARLA, RITORNA NULL.
//     *
//     * @param img
//     * @param imgDirPath
//     * @param quality
//     * @return
//     */
//    public static String resizeImageByQuality(File img, String imgDirPath, String quality, boolean keepOriginalName) throws IOException {
//        double scaleFactor = getScaleFactorForQuality(quality);
//        if (scaleFactor < 0) {
//            return null;
//        }
//        return resizeImage(img, imgDirPath, quality, scaleFactor, keepOriginalName);
//    }
//
//    private static String resizeImage(File img, String imgDirPath, String quality, double scaleFactor, boolean keepOriginalFileName) throws IOException {
//        String origFileName = img.getName();
//        String extension = FilenameUtils.getExtension(origFileName);
//        String newFileName = origFileName;
//        if (!keepOriginalFileName) {
//            origFileName = origFileName.substring(0, (origFileName.lastIndexOf("_") + 1));
//            newFileName = origFileName + quality + "." + extension;
//        }
//        File resizedImg = new File(imgDirPath, newFileName);
//        boolean resized = ImageResizer.resizeJpg(img, resizedImg, scaleFactor, 1);
//        if (!resized) {
//            return null;
//        }
//        BufferedImage bufImg = ImageIO.read(resizedImg);
//        return resizedImg.getAbsolutePath();
//    }
//
//    private static double getScaleFactorForQuality(String quality) {
//        double scaleFactor = 1;
//        // IL FATTORE DI SCALA VIENE DEFINITO IN BASE ALLA QUALITA' PASSATA IN FIRMA:
//        if ("L".equals(quality)) {
//            // L E' 1/2 DELLA QUALITA' DI PARTENZA XL
//            scaleFactor = 0.5;
//        } else if ("M".equals(quality)) {
//            // M E' 1/4 DELLA QUALITA' DI PARTENZA XL
//            scaleFactor = 0.25;
//        } else if ("S".equals(quality)) {
//            // S E' 1/8 DELLA QUALITA' DI PARTENZA XL
//            scaleFactor = 0.125;
//        } else if (!"XL".equals(quality)) {
//            // non riconosciuto mando un numero non valido
//            scaleFactor = -1;
//        }
//        return scaleFactor;
//    }
//
//    public static String elastic() {
//        return "{\n" +
//                "    \"took\": 519,\n" +
//                "    \"timed_out\": false,\n" +
//                "    \"_shards\": {\n" +
//                "        \"total\": 5,\n" +
//                "        \"successful\": 5,\n" +
//                "        \"failed\": 0\n" +
//                "    },\n" +
//                "    \"hits\": {\n" +
//                "        \"total\": 4,\n" +
//                "        \"max_score\": 13.165346,\n" +
//                "        \"hits\": [\n" +
//                "            {\n" +
//                "                \"_index\": \"documents\",\n" +
//                "                \"_type\": \"allegatoOrdineFornitore\",\n" +
//                "                \"_id\": \"allegatoOrdineFornitore-13666-135495-ATT-\",\n" +
//                "                \"_score\": 13.165346,\n" +
//                "                \"_source\": {\n" +
//                "                    \"vendorDesc\": \"CERAMICA FONDOVALLE SPA\",\n" +
//                "                    \"extension\": \"pdf\",\n" +
//                "                    \"vendorOrderNumber\": \"135495\",\n" +
//                "                    \"source\": \"FS\",\n" +
//                "                    \"type\": \"allegatoOrdineFornitore\",\n" +
//                "                    \"version\": null,\n" +
//                "                    \"uploadUser\": \"VP_Buyer\",\n" +
//                "                    \"vendorCode\": \"13666\",\n" +
//                "                    \"filename\": \"Scheda_G6618000148 (1).pdf\",\n" +
//                "                    \"organizationCode\": \"13666\",\n" +
//                "                    \"indexDate\": \"2018-08-31T11:14:05.762+0200\",\n" +
//                "                    \"uploadUserDesc\": \"Paolo Rossi\",\n" +
//                "                    \"name\": \"Scheda_G6618000148 (1).pdf\",\n" +
//                "                    \"id\": \"135495-ATT-\",\n" +
//                "                    \"referenceDate\": \"2018-08-31T11:14:02.008+0200\",\n" +
//                "                    \"notification-114ccbbd-e9f4-4dc0-b599-7d5b62a268de\": {\n" +
//                "                                                                             \"sendDate\": \"2018-10-17T11:32:05.762+0200\",\n" +
//                "                                                                             \"_idFrmkMail\":  \"00009ad8-2d6b-4552-b82a-d2234cbdcf71\",\n" +
//                "                                                                          }\n" +
//                "                }\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"_index\": \"documents\",\n" +
//                "                \"_type\": \"allegatoOrdineFornitore\",\n" +
//                "                \"_id\": \"allegatoOrdineFornitore-6314-78631-ATT-2\",\n" +
//                "                \"_score\": 13.161024,\n" +
//                "                \"_source\": {\n" +
//                "                    \"vendorDesc\": \"POLCART S.P.A.\",\n" +
//                "                    \"extension\": \"png\",\n" +
//                "                    \"vendorOrderNumber\": \"78631\",\n" +
//                "                    \"source\": \"FS\",\n" +
//                "                    \"type\": \"allegatoOrdineFornitore\",\n" +
//                "                    \"version\": null,\n" +
//                "                    \"uploadUser\": \"administrator\",\n" +
//                "                    \"vendorCode\": \"6314\",\n" +
//                "                    \"filename\": \"qbottino-colorato10.png\",\n" +
//                "                    \"organizationCode\": \"6314\",\n" +
//                "                    \"indexDate\": \"2018-09-07T09:56:33.003+0200\",\n" +
//                "                    \"uploadUserDesc\": \"User Administrator\",\n" +
//                "                    \"name\": \"qbottino-colorato10.png\",\n" +
//                "                    \"id\": \"78631-ATT-2\",\n" +
//                "                    \"referenceDate\": \"2018-09-07T09:56:28.672+0200\"\n" +
//                "                }\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"_index\": \"documents\",\n" +
//                "                \"_type\": \"allegatoOrdineFornitore\",\n" +
//                "                \"_id\": \"allegatoOrdineFornitore-13666-135495-ATT-0\",\n" +
//                "                \"_score\": 12.583075,\n" +
//                "                \"_source\": {\n" +
//                "                    \"vendorDesc\": \"CERAMICA FONDOVALLE SPA\",\n" +
//                "                    \"extension\": \"pdf\",\n" +
//                "                    \"vendorOrderNumber\": \"135495\",\n" +
//                "                    \"source\": \"FS\",\n" +
//                "                    \"type\": \"allegatoOrdineFornitore\",\n" +
//                "                    \"version\": null,\n" +
//                "                    \"uploadUser\": \"VP_Buyer\",\n" +
//                "                    \"vendorCode\": \"13666\",\n" +
//                "                    \"filename\": \"Scheda_G6618000148 (1).pdf\",\n" +
//                "                    \"organizationCode\": \"13666\",\n" +
//                "                    \"indexDate\": \"2018-08-31T12:48:27.718+0200\",\n" +
//                "                    \"uploadUserDesc\": \"Paolo Rossi\",\n" +
//                "                    \"name\": \"Scheda_G6618000148 (1).pdf\",\n" +
//                "                    \"id\": \"135495-ATT-0\",\n" +
//                "                    \"referenceDate\": \"2018-08-31T12:48:24.181+0200\"\n" +
//                "                }\n" +
//                "            },\n" +
//                "            {\n" +
//                "               \"_index\": \"documents\",\n" +
//                "               \"_type\": \"customerInvoice\",\n" +
//                "               \"_id\": \"5545-4500540\",\n" +
//                "               \"_score\": 46.423737,\n" +
//                "                \"_source\": {\n" +
//                "                    \"billtoAccountNumber\": \"221838\",\n" +
//                "                    \"invoiceSerie\": \"5545\",\n" +
//                "                    \"source\": \"FS\",\n" +
//                "                    \"type\": \"customerInvoice\",\n" +
//                "                    \"version\": 529,\n" +
//                "                    \"invoiceTotal\": \"-24,263.69\",\n" +
//                "                    \"billtoName\": \"B323 - KOVACS TUEZEP KFT.\",\n" +
//                "                    \"shiptoName\": \" - , , \",\n" +
//                "                    \"billtoCode\": \"14559171\",\n" +
//                "                    \"organizationCode\": \"641\",\n" +
//                "                    \"organization\": \"641\",\n" +
//                "                    \"invoiceNumber\": \"4500540\",\n" +
//                "                    \"name\": \"2018.01.02-5545-4500540.pdf\",\n" +
//                "                    \"invoiceType\": \"K60_CM_EU_BONUS\",\n" +
//                "                    \"id\": \"5545-4500540\",\n" +
//                "                    \"referenceDate\": \"2018-01-02T00:00:00.000+0100\"\n" +
//                "                 }\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"_index\": \"documents\",\n" +
//                "                \"_type\": \"allegatoOrdineFornitore\",\n" +
//                "                \"_id\": \"allegatoOrdineFornitore-13666-135495-ATT-1\",\n" +
//                "                \"_score\": 12.583075,\n" +
//                "                \"_source\": {\n" +
//                "                    \"vendorDesc\": \"CERAMICA FONDOVALLE SPA\",\n" +
//                "                    \"extension\": \"png\",\n" +
//                "                    \"vendorOrderNumber\": \"135495\",\n" +
//                "                    \"source\": \"FS\",\n" +
//                "                    \"type\": \"allegatoOrdineFornitore\",\n" +
//                "                    \"version\": null,\n" +
//                "                    \"uploadUser\": \"VP_Buyer\",\n" +
//                "                    \"vendorCode\": \"13666\",\n" +
//                "                    \"filename\": \"qbottino-colorato11.png\",\n" +
//                "                    \"organizationCode\": \"13666\",\n" +
//                "                    \"indexDate\": \"2018-09-06T12:14:12.476+0200\",\n" +
//                "                    \"uploadUserDesc\": \"Paolo Rossi\",\n" +
//                "                    \"name\": \"qbottino-colorato11.png\",\n" +
//                "                    \"id\": \"135495-ATT-1\",\n" +
//                "                    \"referenceDate\": \"2018-09-06T12:14:10.412+0200\"\n" +
//                "                }\n" +
//                "            }\n" +
//                "        ]\n" +
//                "    }\n" +
//                "}";
//    }
//
}
