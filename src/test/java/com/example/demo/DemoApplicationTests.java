package com.example.demo;

import com.example.demo.util.FullTextUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
//		File f = new File("C:\\Users\\paolo.convertini\\Downloads\\dichiarazione aziendale.docx");
//        File f = new File("C:\\Users\\paolo.convertini\\Downloads\\dichiarazione aziendale.doc");
//        File f = new File("C:\\Users\\paolo.convertini\\Downloads\\ciao.pptx");
//        File f = new File("C:\\Users\\paolo.convertini\\Downloads\\Formazione dei nuovi dipendenti.ppt");
//        File f = new File("C:\\Users\\paolo.convertini\\Downloads\\barcode fatture emmedata.pdf");
		File f = new File("C:\\Users\\paolo.convertini\\Downloads\\3d104778-827f-43f2-aa52-09b7a97b29c3.eml");
//        File f = new File("C:\\Users\\paolo.convertini\\Downloads\\bookmarks_02_04_19.html");
//        File f = new File("C:\\Users\\paolo.convertini\\Downloads\\test_export-Aperto_da_me-1553785141508 (1).xlsx");
//        File f = new File("C:\\Users\\paolo.convertini\\Downloads\\test_export-Aperto_da_me-1553785141508.xls");
//        File f = new File("C:\\Users\\paolo.convertini\\Downloads\\configurazioni.txt");
		System.out.println(f.getName() + " >> " + FullTextUtils.textExtraction(f));
	}

}
