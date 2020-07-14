//package com.example.demo.util;
//
//import java.io.File;
//import java.io.IOException;
//
//public class FileManagerUtils {
//
//
//    public static File createFolder(String prefix, String extension, String fileName) throws IOException {
//        File outputFile = null;
//        try {
//            outputFile = File.createTempFile(prefix, extension);
//            File dir = outputFile.getParentFile();
//            String name = outputFile.getName().substring(0, outputFile.getName().indexOf('.'));
//            File f2 = new File(dir, name);
//            f2.mkdir();
//            File f = new File(f2, fileName);
//            return f;
//        } finally {
//            if (outputFile != null) {
//                org.apache.commons.io.FileUtils.deleteQuietly(outputFile);
//            }
//        }
//    }
//}
