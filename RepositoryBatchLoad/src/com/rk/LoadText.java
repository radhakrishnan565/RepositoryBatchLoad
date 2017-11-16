package com.rk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
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

public class LoadText {
	
	String strDate = "";
	public static void main(String args[]) {
		LoadText load = new LoadText();
		load.loadExcelToDB();
	}
	public void loadExcelToDB() {
		String excel = "D:\\production\\Eclipse_Workspace\\RepositoryBatchLoad\\test\\OB.MCR_2017-11-14-01-26-35_test.csv";

        try {
        	Long startTime = System.currentTimeMillis();
        	Date date = new Date();
        	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
            strDate= formatter.format(date);  
        	File file = new File(excel);
        	List<String> lines = FileUtils.readLines(file, "UTF-8");
        	int i = 0;
        	boolean processFlag = false;
        	
        	ArrayList<MCR_Bean> beanList = new ArrayList<MCR_Bean>();
        	int j = 1;
            for (String line : lines) {
            	
               // System.out.println(line);
                if(!processFlag &&line.startsWith("Claim ID")) {
                	processFlag = true;
                }
                if(processFlag) {
                	if(i>0) {
                		MCR_Bean mcr =  setObject(line);
                		beanList.add(mcr);
                       System.out.println("i:"+i);// System.out.println(i+" - Object: "+mcr.getObjectToString());
                        if(i % 10000 == 0) {
                        	ArrayList<MCR_Bean> list = new ArrayList<MCR_Bean>();
                        	list.addAll(beanList);
                        	System.out.println("while calling thread .."+j);
                       
                        	new ProcessDBThread(list, j+"").start();
                        	
                        	beanList = new ArrayList<MCR_Bean>();
                        	j++;
                        }
                	}
                	i++;
                }
            }
            if(beanList.size()>0) {
            	new ProcessDBThread(beanList, j+"").start();
            }
            System.out.println("---flush and close session and commit..");
            Long endTime = System.currentTimeMillis();
            System.out.println(i+" TimeTaken to complete operation: "+(endTime-startTime));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        	
        }
	}
	public MCR_Bean setObject(String strRow) {
		
		String[] row = strRow.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
		MCR_Bean bean = new MCR_Bean();
		if(row.length>0) {
			for(int i = 0; i< row.length; i++) {
				if(row[i].startsWith("\"")&& row[i].endsWith("\"")) {
					row[i] = row[i].substring(1, row[i].length()-1);
				}
			}
		}
		bean.setClaim_ID(row[0].trim());
		bean.setClaim_Status(row[1].trim());
		bean.setSource_ID(row[2].trim());
		bean.setTitle(row[3].trim());
		bean.setContent_Provider(row[4].trim());
		bean.setPublication_Year(row[5].trim());
		bean.setVolume(row[6].trim());
		bean.setVolume_Text(row[7].trim());
		bean.setIssue(row[8].trim());
		bean.setIssue_Text(row[9].trim());
		bean.setClaim_Problem_Type(row[10].trim());
		bean.setClaim_Creation_Date(row[11].trim());
		bean.setDPR_reference(row[12].trim());
		bean.setPriority(row[13].trim());
		bean.setManifestation(row[14].trim());
		bean.setProblem_Description(row[15].trim());
		bean.setRemarks("");
		bean.setFilename("");
		bean.setDate(strDate);
		
		return bean; 
	}
}
