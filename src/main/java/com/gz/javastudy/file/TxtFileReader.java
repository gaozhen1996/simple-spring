package com.gz.javastudy.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TxtFileReader {
	
	public static void main(String[] args) {
		String readFilePath = "C:/Users/hspcadmin/Desktop/20190916bond_valuation_sample.txt";
		String writeFilepath = "C:/Users/hspcadmin/Desktop/valuation_new1.sql";
		int startline = 20;
		try {
			long exeBefore = System.currentTimeMillis();
			readTxt2SQL(startline,readFilePath,writeFilepath);
			long exeAfter = System.currentTimeMillis();
			System.out.println(exeAfter - exeBefore+"毫秒");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void readTxt2SQL(int startline,String readFilePath,String writeFilepath) throws Exception {
		/**
		 * open read file
		 */
        FileInputStream filestream=new FileInputStream(readFilePath);
        byte[] b = new byte[3];
        filestream.read(b);
        String ecode="gbk";
        if (b[0] == -17 && b[1] == -69 && b[2] == -65){
            ecode="utf-8";
        }
        InputStreamReader readStream=new InputStreamReader(filestream,ecode);
        BufferedReader reader=new BufferedReader(readStream);
        
        /**
         * open write file
         */
        File needWriteFile = new File(writeFilepath);
        if(needWriteFile.exists()) {
        	needWriteFile.delete();
        }
        needWriteFile.createNewFile();
        BufferedWriter out = null;
        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writeFilepath, true)));
        
        String field = null;
        try {
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
                	field = "".equals(fields[i].trim())?"":fields[i].trim();
                	sql.append("'"+field+"',");
    			}
                for (int i = 4; i < 14; i++) {
                	field = "".equals(fields[i].trim())?"":fields[i].trim();
                	if(field.equals("")) {
                		sql.append("'"+field+"',");
                	}else {
                		//校验数字是否规范
                		sql.append(" "+Double.valueOf(field)+",");
                	}
    			}    
                for (int i = 14; i < 15; i++) {
                	field = "".equals(fields[i].trim())?"":fields[i].trim();
                	sql.append("'"+field+"',");
    			}
                for (int i = 15; i < 17; i++) {
                	field = "".equals(fields[i].trim())?"":fields[i].trim();
                	if(field.equals("")) {
                		sql.append("'"+field+"',");
                	}else {
                		//校验数字是否规范
                		sql.append(" "+Double.valueOf(field)+",");
                	}
    			}
                for (int i = 17; i < 18; i++) {
                	field = "".equals(fields[i].trim())?"":fields[i].trim();
                	sql.append("'"+field+"',");
    			}
                sql.append(" "+8000+" ,");
                sql.append("date'2019-09-16');\r\n\r\n");
                
                out.write(sql.toString());
            }	
            //完成后提交
            out.write("commit;");
		} catch (Exception e) {
			System.out.println("field="+field);
			e.printStackTrace();
		}

        
        if(readStream!=null){
            readStream.close();
        }
        if(reader!=null){
            reader.close();
        }
        if(out != null) {
        	out.close();
        }
	}
}
