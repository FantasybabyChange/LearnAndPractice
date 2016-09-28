package com.fantasybaby.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * use poi plugin to parse excel ...
 * @author 曦
 *
 */
public class POIParse {
	
	private static Logger _logger = LogManager.getLogger(POIParse.class);
	private File  file;
	private static POIParse poiParse;
	private PoiExcelEnum poiType;
	private  Workbook workbook;
	private OutputStream outPutStream;
	private InputStream inputStream;
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
	private CreationHelper getCreationHelper(){
		return workbook.getCreationHelper();
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
	private void createWorkBookParse(){
		try {
			if(this.file != null){
				this.workbook = WorkbookFactory.create(this.file);
				return;
			}
			if(this.inputStream != null){
				this.workbook = WorkbookFactory.create(this.inputStream);
			}
		} catch (Exception e) {
		}
		}
	public void createWorkBook(String filePath){
		this.file = new File(filePath);
		createWorkBookParse();
	}
	public void createWorkBookStream(String filePath){
		try {
			inputStream = new FileInputStream(filePath);
			createWorkBookParse();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public CellStyle getCellStyle(){
		return this.workbook.createCellStyle();
	}
	public static void testCreateExcel(){
		try {
			POIParse instance = POIParse.getInstance();
			instance.setFile("createExcel.xls");
			instance.createWorkBook();
			Sheet createSheet = instance.createSheet("hello man");
			CreationHelper createHelp = instance.getCreationHelper();
			Row row1 = createSheet.createRow(0);
			Cell cell = row1.createCell(0,Cell.CELL_TYPE_STRING);
			cell.setCellValue("name");
			Cell cell2 = row1.createCell(1,Cell.CELL_TYPE_STRING);
			cell2.setCellValue(createHelp.createRichTextString("哈哈哈"));
			CellStyle cellStyle = instance.getCellStyle();
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyle.setBorderTop((IndexedColors.GREEN.getIndex()));
			cellStyle.setFillBackgroundColor(IndexedColors.BLUE_GREY.index);
			cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
			cell2.setCellStyle(cellStyle);
			row1.createCell(2).setCellValue(false);
			Cell cell4 = row1.createCell(3);
			cell4.setCellType(Cell.CELL_TYPE_ERROR);
			cell4.setCellValue("aaa");
			instance.outPutFile();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void testParseExcel(){
		POIParse instance = POIParse.getInstance();
		instance.createWorkBook("createExcel.xls");
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
//		testParseExcel();
		
	}

}
