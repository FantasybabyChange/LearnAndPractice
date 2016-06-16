package com.fantasybaby.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * use poi plugin to parse excel ...
 * @author 曦
 *
 */
public class POIParse {
	private static Logger _logger = Logger.getLogger(POIParse.class);
	private File  file;
	private static POIParse poiParse;
	private PoiExcelEnum poiType;
	private static Workbook workbook;
	private OutputStream outPutStream;
	private POIParse() {
	}
	public void setFile(String filePath){
		file = new File(filePath);
	}
	public  void setType(PoiExcelEnum poiType){
		this.poiType = poiType;
	}
	public static POIParse getInstance(){
		if(poiParse == null){
			poiParse = new POIParse();
		}
		return poiParse;
	}
	private void createWorkBook(){
		_logger.info("create WokrHook start");
		if(poiType != null){
			switch(poiType){
			case HSSF:
				workbook = new HSSFWorkbook();
				break;
			case XSSF:
				workbook = new XSSFWorkbook();
				break;
			case SXSSF:
				workbook = new SXSSFWorkbook();
			default:break;
			}
		}
		if(workbook == null){
			workbook = new XSSFWorkbook();
		}
//		return workbook;
	}
	public void createWorkBook(PoiExcelEnum type){
		this.poiType = type;
		createWorkBook();
	}
	public Sheet createSheet(String sheetName){
		String safeName = WorkbookUtil.createSafeSheetName(sheetName);
		return workbook.createSheet(safeName);
	}
	public void outPutFile(String fileName) throws Exception{
		this.file = new File(fileName);
		outPutFile();
	}
	public void outPutFile() throws Exception{
			if(this.file == null){
				throw new Exception("please init File path");
			}
			outPutStream = new FileOutputStream(this.file);
			_logger.info("start write File " + this.file.getName());
			workbook.write(outPutStream);
			_logger.info("end write File " + this.file.getAbsolutePath());
			close();
	}
	private void close() throws IOException{
		if(outPutStream != null){
			outPutStream.flush();
			outPutStream.close();
		}
		if(workbook != null){
			workbook.close();
		}
	}
	public static void testCreateExcel(){
		try {
			POIParse instance = POIParse.getInstance();
			instance.setFile("createExcel.xls");
			instance.createWorkBook();
			Sheet createSheet = instance.createSheet("hello man");
			Row row1 = createSheet.createRow(0);
			Cell cell = row1.createCell(0,Cell.CELL_TYPE_STRING);
			cell.setCellValue("name");
			Cell cell2 = row1.createCell(1,Cell.CELL_TYPE_STRING);
			cell2.setCellValue("姓名");
			
			instance.outPutFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		/*try {
			InputStream input = POIParse.class.getClassLoader().getResourceAsStream("testExcel.xlsx");
			
			XSSFWorkbook workbook = new XSSFWorkbook(input);
			String sheetName = workbook.getSheetName(0);
			System.out.println(sheetName);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		testCreateExcel();
		
	}

}
