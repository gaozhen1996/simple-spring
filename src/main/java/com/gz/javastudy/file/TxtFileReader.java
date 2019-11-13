package com.gz.javastudy.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TxtFileReader {
	
	public static void main(String[] args) {
		String filePath = "C:/Users/hspcadmin/Desktop/20190916bond_valuation_sample.txt";
		int startline = 30;
		try {
			readTxt2SQL(startline,filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void readTxt2SQL(int startline,String filepath) throws Exception {
        FileInputStream filestream=new FileInputStream(filepath);
        byte[] b = new byte[3];
        filestream.read(b);
        String ecode="gbk";
        if (b[0] == -17 && b[1] == -69 && b[2] == -65){
            ecode="utf-8";
        }
        InputStreamReader readStream=new InputStreamReader(filestream,ecode);
        BufferedReader reader=new BufferedReader(readStream);
        
        String temp=null;
        int line=0;//行号
        while((temp=reader.readLine())!=null){
            line++;
            if(line<startline) {
            	continue;
            }
            String[] fields = temp.split("\\|");
            String initSQL = "insert into TJK_ZZQS_BOND_VALUATION_NEW (S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, S12, S13, S14, S15, S16, S17, S18, L_ZTBH, D_YWRQ)\r\n" + 
            		"values (";
            StringBuffer sql = new StringBuffer(initSQL);
            for (int i = 0; i < 4; i++) {
            	String field = "".equals(fields[i].trim())?"":fields[i].trim();
            	sql.append("'"+field+"',");
			}
            for (int i = 4; i < 14; i++) {
            	String field = "".equals(fields[i].trim())?"":fields[i].trim();
            	sql.append("'"+field+"',");
			}    
            for (int i = 14; i < 15; i++) {
            	String field = "".equals(fields[i].trim())?"":fields[i].trim();
            	sql.append("'"+field+"',");
			}
            for (int i = 15; i < 17; i++) {
            	String field = "".equals(fields[i].trim())?"":fields[i].trim();
            	sql.append("'"+field+"',");
			}
            for (int i = 17; i < 18; i++) {
            	String field = "".equals(fields[i].trim())?"":fields[i].trim();
            	sql.append("'"+field+"',");
			}
            sql.append(" "+8000+" ,");
            sql.append("date'2019-09-16');");
            System.out.println(sql.toString());
            
            //取1000行
            if(line>=1000) {
            	break;
            }
        }
        
        if(readStream!=null){
            readStream.close();
        }
        if(reader!=null){
            reader.close();
        }
	}
}
