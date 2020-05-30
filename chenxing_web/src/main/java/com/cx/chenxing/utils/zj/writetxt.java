package com.cx.chenxing.utils.zj;

import java.io.*;

public class writetxt {
		private static String filenameTemp; 

		/** 
		* 创建文件 
		* 
		* @throws IOException 
		*/ 
		public static boolean creatTxtFile(String path, String name) throws IOException { 
		boolean flag = false; 
		filenameTemp = path + name + ".txt"; 
		File filename = new File(filenameTemp); 
		if (!filename.exists()) { 
		filename.createNewFile(); 
		flag = true; 
		} 
		return flag; 
		} 

		/** 
		* 写文件 
		* 
		* @param newStr 
		*            新内容 
		* @throws IOException 
		*/ 
		public static boolean writeTxtFile (String newStr) 
		throws IOException { 
		// 先读取原有文件内容，然后进行写入操作 
		boolean flag = false; 
		String filein = newStr + "\r\n"; 
		String temp = ""; 

		FileInputStream fis = null; 
		InputStreamReader isr = null; 
		BufferedReader br = null; 

		FileOutputStream fos = null; 
		PrintWriter pw = null; 
		try { 
		// 文件路径 
		File file = new File(filenameTemp); 
		// 将文件读入输入流 
		fis = new FileInputStream(file); 
		isr = new InputStreamReader(fis); 
		br = new BufferedReader(isr); 
		StringBuffer buf = new StringBuffer(); 

		// 保存该文件原有的内容 
		for (@SuppressWarnings("unused")
		int j = 1; (temp = br.readLine()) != null; j++) { 
		buf = buf.append(temp); 
		// System.getProperty("line.separator") 
		// 行与行之间的分隔符 相当于“\n” 
		buf = buf.append(System.getProperty("line.separator")); 
		} 
		buf.append(filein); 

		fos = new FileOutputStream(file); 
		pw = new PrintWriter(fos); 
		pw.write(buf.toString().toCharArray()); 
		pw.flush(); 
		flag = true; 
		} catch (IOException e1) { 
		// TODO 自动生成 catch 块 
		throw e1; 
		} finally { 
		if (pw != null) { 
		pw.close(); 
		} 
		if (fos != null) { 
		fos.close(); 
		} 
		if (br != null) { 
		br.close(); 
		} 
		if (isr != null) { 
		isr.close(); 
		} 
		if (fis != null) { 
		fis.close(); 
		} 
		} 
		return flag; 
		} 

}
