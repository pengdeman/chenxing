/**
 * 
 */
package com.cx.chenxing.utils.zj;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class NormalUtils {

	public static String ISOtoGBK(String inputStr) {
		try {
			if (inputStr == null || inputStr.equals("")) {
				return "";
			}else if (inputStr.equals(new String(inputStr.getBytes(), "GBK"))) {
				return inputStr;
			} else {
				return new String(inputStr.trim().getBytes("ISO-8859-1"), "GBK");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static String ISOtoUTF8(String inputStr) {
		try {
			if (inputStr == null || inputStr.equals("")) {
				return "";
			}else if (inputStr.equals(new String(inputStr.getBytes(), "UTF-8"))) {
				return inputStr;
			} else {
				return new String(inputStr.trim().getBytes("ISO-8859-1"), "UTF-8");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static String GBKtoISO(String inputStr) {
		try {
			if (inputStr == null || inputStr.equals("")) {
				return "";
			}else if (inputStr.equals(new String(inputStr.getBytes(), "ISO-8859-1"))) {
				return inputStr;
			} else {
				return new String(inputStr.trim().getBytes("GBK"), "ISO-8859-1");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static String GBKtoUTF8(String inputStr){
        try {
        	if(inputStr == null || inputStr.equals("")) {
        		return "";
        	}else if(inputStr.equals(new String(inputStr.getBytes(), "UTF-8"))) {
        		return inputStr;
        	}else if(inputStr.equals(new String(inputStr.getBytes("UTF-8"), "UTF-8"))) {
        		return inputStr;
        	}else {
        		return new String(gbk2utf8(inputStr), "UTF-8");
        	}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
    }

    private static byte[] gbk2utf8(String chenese) {
        char c[] = chenese.toCharArray();
        byte[] fullByte = new byte[3 * c.length];
        for (int i = 0; i < c.length; i++) {
            int m = c[i];
            String word = Integer.toBinaryString(m);

            StringBuffer sb = new StringBuffer();
            int len = 16 - word.length();
            for (int j = 0; j < len; j++) {
                sb.append("0");
            }
            sb.append(word);
            sb.insert(0, "1110");
            sb.insert(8, "10");
            sb.insert(16, "10");

            String s1 = sb.substring(0, 8);
            String s2 = sb.substring(8, 16);
            String s3 = sb.substring(16);

            byte b0 = Integer.valueOf(s1, 2).byteValue();
            byte b1 = Integer.valueOf(s2, 2).byteValue();
            byte b2 = Integer.valueOf(s3, 2).byteValue();
            byte[] bf = new byte[3];
            bf[0] = b0;
            fullByte[i * 3] = bf[0];
            bf[1] = b1;
            fullByte[i * 3 + 1] = bf[1];
            bf[2] = b2;
            fullByte[i * 3 + 2] = bf[2];

        }
        return fullByte;
    }
    
    public static JSONObject modelToJSON(Object o) {
		@SuppressWarnings("rawtypes")
		Class c = o.getClass();
		JSONObject result = new JSONObject();
		Field[] fields = c.getDeclaredFields();
		for(Field f : fields) {
			try {
				String name = f.getName();
				@SuppressWarnings("unchecked")
				Object value = c.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1)).invoke(o);
				if(value != null) {
					String className = value.getClass().getName();
					if(className.equals("java.sql.Timestamp") || className.equals("java.util.Date")) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime((Date)value);
						
						result.put(name + "Year", calendar.get(Calendar.YEAR));
						result.put(name + "Month", calendar.get(Calendar.MONTH) + 1);
						result.put(name + "Day", calendar.get(Calendar.DAY_OF_MONTH));
						result.put(name + "Time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value));
						
						value = new SimpleDateFormat("yyyy-MM-dd").format(value);
						result.put(name, value);
					}else if(className.startsWith("org.nercita")) {
						result.put(name, modelToJSON(value));
					}else {
						result.put(name, value);
					}
				}
            } catch (Exception e) { }
		}
		return result;
	}
	
	public static String MD5(String inputStr) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = inputStr.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> fliterParamToBeans(T t, HttpServletRequest request) {
		List<T> result = new ArrayList<T>();
		if (request != null) {
			SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			@SuppressWarnings("rawtypes")
			Class bean = t.getClass();

			Field[] fields = bean.getDeclaredFields();
			HashMap<String, String[]> valueMap = new HashMap<String, String[]>();
			int count = 0;
			for (Field f : fields) {
				try {
					String name = f.getName();
					String[] values = request.getParameterValues(name);
					if (values != null && values.length > 0) {
						valueMap.put(name, values);
						if (values.length > count) {
							count = values.length;
						}
					}
				} catch (Exception e) {
				}
			}

			if (count > 0) {
				// result = new Object[count];
				Iterator<String> keys = valueMap.keySet().iterator();
				while (keys.hasNext()) {
					String name = keys.next();
					String[] value = valueMap.get(name);

					for (int i = 0; i < count; i++) {
						try {
							T o = null;
							if (result.size() > i) {
								o = result.get(i);
							} else {
								o = (T) bean.newInstance();
							}
							Field f = bean.getDeclaredField(name);
							Method m = bean.getMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1), f.getType());
							if (m != null && value.length > i) {
								String v = value[i];
								if (request.getMethod().equalsIgnoreCase("get")) {
									v = ISOtoUTF8(v);
								}
								String valueClass = f.getType().getSimpleName();
								if (v != null && !"".equals(v)) {
									if (valueClass.equalsIgnoreCase("Date") || valueClass.equalsIgnoreCase("Timestamp")) {
										long time = 0l;
										try {
											time = fullDateFormat.parse(v).getTime();
											time = dateFormat.parse(v).getTime();
										} catch (Exception e) {
										}
										if (time != 0) {
											m.invoke(o, new Timestamp(time));
										}
									} else if (valueClass.equalsIgnoreCase("Integer")) {
										m.invoke(o, Integer.parseInt(v));
									} else if (valueClass.equalsIgnoreCase("Double")) {
										m.invoke(o, Double.parseDouble(v));
									} else if (valueClass.equalsIgnoreCase("Boolean")) {
										m.invoke(o, Boolean.parseBoolean(v));
									} else if (valueClass.equalsIgnoreCase("String")) {
										m.invoke(o, v);
									}
								}else{
									Method getMethod = bean.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
									Object defValue = getMethod.invoke(o);
									if (valueClass.equalsIgnoreCase("Integer") && !name.equalsIgnoreCase("id") && defValue == null) {
										m.invoke(o, 0);
									} else if (valueClass.equalsIgnoreCase("Double") && defValue == null) {
										m.invoke(o, 0d);
									} else if (valueClass.equalsIgnoreCase("Boolean") && defValue == null) {
										m.invoke(o, false);
									} if(valueClass.equalsIgnoreCase("String") && "".equals(value)){
										m.invoke(o, "");
									}
								}
							}
							if(result.size() > i){
								result.set(i, o);
							}else{
								result.add(o);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return result;
	}
	
	public static <T> T fliterParamToBean(T bean, HttpServletRequest request){
		if(bean != null && request != null){
			SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			@SuppressWarnings("rawtypes")
			Class c = bean.getClass();
			
			Field[] fields = c.getDeclaredFields();
			for(Field f : fields) {
				try {
					String name = f.getName();
					@SuppressWarnings("unchecked")
					Method m =  c.getMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1), f.getType());
					if(m != null){
						String value = null;
						if(request.getMethod().equalsIgnoreCase("get")){
							value = ISOtoUTF8(request.getParameter(name));
						}else{
							value = request.getParameter(name);
						}
						String valueClass = f.getType().getSimpleName();
						if(value != null && !"".equals(value)){
							if (valueClass.equalsIgnoreCase("Date") || valueClass.equalsIgnoreCase("Timestamp")) {
								long time = 0l;
								try {
									time = fullDateFormat.parse(value).getTime();
									time = dateFormat.parse(value).getTime();
								} catch (Exception e) {
								}
								if (time != 0) {
									m.invoke(bean, new Timestamp(time));
								}
							} else if (valueClass.equalsIgnoreCase("Integer")) {
								m.invoke(bean, Integer.parseInt(value));
							} else if (valueClass.equalsIgnoreCase("Double")) {
								m.invoke(bean, Double.parseDouble(value));
							} else if (valueClass.equalsIgnoreCase("Boolean")) {
								m.invoke(bean, Boolean.parseBoolean(value));
							} else if (valueClass.equalsIgnoreCase("String")) {
								String[] values = request.getParameterValues(name);
								if(values != null && values.length > 0){
									value = "";
									for(String v : values){
										if(!value.equals("")){
											value +=",";
										}
										if(request.getMethod().equalsIgnoreCase("get")){
											value += ISOtoUTF8(v);
										}else{
											value += v;
										}
									}
									m.invoke(bean, value);
								}
							}
						}else{
							@SuppressWarnings("unchecked")
							Method getMethod = c.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
							Object defValue = getMethod.invoke(bean);
							if (valueClass.equalsIgnoreCase("Integer") && !name.equalsIgnoreCase("id") && defValue == null) {
								m.invoke(bean, 0);
							} else if (valueClass.equalsIgnoreCase("Double") && defValue == null) {
								m.invoke(bean, 0d);
							} else if (valueClass.equalsIgnoreCase("Boolean") && defValue == null) {
								m.invoke(bean, false);
							} if(valueClass.equalsIgnoreCase("String") && "".equals(value)){
								m.invoke(bean, "");
							}
						}
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		return bean;
	}
	/*
	public static String getIndexLink(int seed){
		switch (seed)
		{
		case 1:
			System.out.println("../jjfz/jjfz.jsp");
			return "../jjfz/jjfz.jsp";
		case 2:
			System.out.println("../shhx/shhx-cxsrqk.html");
			return "../shhx/shhx-cxsrqk.html";
		case 3:
			System.out.println("../shzl/shzl-jmszzk.html");
			return "../shzl/shzl-jmszzk.html";
		case 4:
			System.out.println("../mzfz/mzfz-ldbgs.html");
			return "../mzfz/mzfz-ldbgs.html";
		case 5:
			System.out.println("../whjy/whjy-jyzxhd.html");
			return "../whjy/whjy-jyzxhd.html";
		case 6:
			System.out.println("../zyhj/zyhj.jsp");
			return "../zyhj/zyhj.jsp";
		
		}
		return null;
	}
	*/
	
	
public static String[] syqpicdownUrl(MultipartHttpServletRequest request,String name,String oldfile){
		
		if(oldfile!=null&&!"".equals(oldfile.trim())){//判断是否是有旧数据
			File oldFile  = new File(request.getSession().getServletContext().getRealPath(File.separator+"pic")+File.separator+oldfile);//原来的文件
			if(oldFile.exists()){
				oldFile.delete();//删除旧文件
			}
		}
		MultipartFile multipartFile = request.getFile(name);
		String nowName[] = null;
		if(multipartFile!=null&&multipartFile.getOriginalFilename()!=null&&!"".equals(multipartFile.getOriginalFilename().trim())){
			try{
				File outFile = new File(request.getSession().getServletContext().getRealPath(File.separator+"pic")+File.separator);//创建路径
				if(!outFile.exists()){
					outFile.mkdirs();
				}
				
				nowName=new String[2];
				
				String filename = multipartFile.getOriginalFilename();
				nowName[0]=filename;
				
				//filename=filename.substring(filename.lastIndexOf("."),filename.length()).toLowerCase();
				
				String filenamehz = ".jpg";
				
				String curren = System.currentTimeMillis()+"";
				
				String downPath = request.getSession().getServletContext().getRealPath(File.separator+"pic")+File.separator+curren+filenamehz;
				nowName[1]=System.currentTimeMillis()+filenamehz;
				
				OutputStream out = new FileOutputStream(downPath);
				InputStream input = multipartFile.getInputStream();
				
				byte[] by = new byte[1024];
				
				while(input.read(by)!=-1){
					out.write(by);
				}
				out.flush();
				out.close();
				input.close();
				
				/**
				 * 加上缩略图
				 */
				String slvdownPath = request.getSession().getServletContext().getRealPath(File.separator+"pic")+File.separator+"slt"+curren+filenamehz;
				ImageCompressUtil.saveMinPhoto(downPath, slvdownPath, 1000, 1d);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return nowName;
	}

public static String getRemortIP(HttpServletRequest request) {  
    if (request.getHeader("x-forwarded-for") == null) {  
        return request.getRemoteAddr();  
    }  
    return request.getHeader("x-forwarded-for");  
}   
}
