package com.rk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;

import com.rk.dao.MCR_Bean;
import com.rk.util.DBSessionUtil;

public class LoadExcel {
	Date date = new Date();
	
	public static void main(String args[]) {
		LoadExcel load = new LoadExcel();
		load.loadExcelToDB();
	}
	public void loadExcelToDB() {
		String excel = "D:\\production\\Eclipse_Workspace\\RepositoryBatchLoad\\test\\OB.MCR_2017-11-14-01-26-35.xls";

		FileInputStream excelFile = null;
		
        try {

            excelFile = new FileInputStream(new File(excel));
            //Workbook workbook = new XSSFWorkbook(excelFile);
            POIFSFileSystem myFileSystem = new POIFSFileSystem(excelFile);
	        HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
	        HSSFSheet mySheet = myWorkBook.getSheetAt(0);
	        Iterator<Row> iterator = mySheet.rowIterator();
            
	        //Sheet datatypeSheet = workbook.getSheetAt(0);
            //Iterator<Row> iterator = datatypeSheet.iterator();
            Date date = new Date() ;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
            System.out.println("absolute file---: "+new File(excel).getParent()+"\\"+dateFormat.format(date)+".txt");
            int i = 0;
            Session session = DBSessionUtil.getInstance().getSession();
            while (iterator.hasNext()) {
            	i++;
                Row currentRow = iterator.next();
                MCR_Bean mcr =  setObject(currentRow);
                System.out.println("Object: "+mcr.getObjectToString());
              /*  session.saveOrUpdate(mcr);
                DBSessionUtil.closeSession(session);
                if ( i % 20 == 0 ) { 
                	session.flush();
                	session.clear();
                }*/
            }
            session.flush();
	        session.clear();
	        session.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        	if(excelFile != null) {
        		try {
					excelFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
	}
	public MCR_Bean setObject(Row row) {
		
		MCR_Bean bean = new MCR_Bean();
		bean.setClaim_ID(row.getCell(0).toString().trim());
		bean.setClaim_Status(row.getCell(0).toString().trim());
		bean.setSource_ID(row.getCell(0).toString().trim());
		bean.setTitle(row.getCell(0).toString().trim());
		bean.setContent_Provider(row.getCell(0).toString().trim());
		bean.setPublication_Year(row.getCell(0).toString().trim());
		bean.setVolume(row.getCell(0).toString().trim());
		bean.setVolume_Text(row.getCell(0).toString().trim());
		bean.setIssue(row.getCell(0).toString().trim());
		bean.setIssue_Text(row.getCell(0).toString().trim());
		bean.setClaim_Problem_Type(row.getCell(0).toString().trim());
		bean.setClaim_Creation_Date(row.getCell(0).toString().trim());
		bean.setDPR_reference(row.getCell(0).toString().trim());
		bean.setPriority(row.getCell(0).toString().trim());
		bean.setManifestation(row.getCell(0).toString().trim());
		bean.setProblem_Description(row.getCell(0).toString().trim());
		bean.setRemarks("");
		bean.setFilename("");
		bean.setDate(date.toString());
		
		return bean; 
	}
}
