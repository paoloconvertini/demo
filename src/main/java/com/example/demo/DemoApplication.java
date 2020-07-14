package com.example.demo;

import com.itextpdf.text.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@SpringBootApplication
public class DemoApplication {
    private static final String EOL = "\n"; //it was like this System.getProperty("line.separator"); but it offends Fortify analysis
    private static final String PATTERN = "^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";

    public static void main(String[] args) throws IOException, DocumentException {
        String data = "2020/09-10";
        String[] split = StringUtils.split(data, "-");
        boolean matches = data.matches(PATTERN);
        System.out.println("di " + matches);

    }


}
