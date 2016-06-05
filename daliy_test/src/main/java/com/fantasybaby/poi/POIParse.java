package com.fantasybaby.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * use poi plugin to parse excel ...
 * @author 曦
 *
 */
public class POIParse {
	public static void main(String[] args) {
		try {
			InputStream input = POIParse.class.getClassLoader().getResourceAsStream("testExcel.xlsx");
			
			XSSFWorkbook workbook = new XSSFWorkbook(input);
			String sheetName = workbook.getSheetName(0);
			System.out.println(sheetName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
