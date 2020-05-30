package com.cx.chenxing.utils.zj;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetExistAccessToken {
    //定义一个私有的静态全局变量来保存该类的唯一实例

   private static GetExistAccessToken getExistAccessToken;

   /// 构造函数必须是私有的

   /// 这样在外部便无法使用 new 来创建该类的实例

   private GetExistAccessToken()

   {

   }

   /// 定义一个全局访问点

   /// 设置为静态方法

   /// 则在类的外部便无需实例化就可以调用该方法

   public static GetExistAccessToken getInstance()

   {

       //这里可以保证只实例化一次

       //即在第一次调用时实例化

       //以后调用便不会再实例化

       if ( getExistAccessToken == null)

       {

           getExistAccessToken = new GetExistAccessToken();

       }

       return getExistAccessToken;

   }

  public  String getExistAccessToken() {
	  SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	 //MediaUtil mediaUtil = MediaUtil.getInstance();

      String accessToken = null;

      // 读取XML文件中的数据

      String filepath = GetExistAccessToken.class.getClassLoader().getResource("accessToken.xml").getPath();

      try {

          Document document = new SAXReader().read(filepath);

          // 将解析结果存储在HashMap中

          Map<String, String> map = new HashMap<String, String>();

          // 得到xml根元素

          Element root = document.getRootElement();

          // 得到根元素的全部子节点

        @SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();

          // 遍历全部子节点

          for (Element e : elementList) {

              map.put(e.getName(), e.getText());

          }

          accessToken = map.get("AccessToken");

          String lastTime = map.get("AccessExpires");

          Date now = new Date();
         
          Date accessExpires = s.parse(lastTime);

          if (now.getTime()>accessExpires.getTime())

          {

              //accessToken = mediaUtil.getAccess_token();
             
        	  accessExpires = new Date(now.getTime() + 7200000);
              
              String nextTime = s.format(accessExpires);

              root.selectSingleNode("AccessToken").setText(accessToken);

              root.selectSingleNode("AccessExpires").setText(

                      nextTime);

              XMLWriter writer = new XMLWriter(new FileWriter(new File(

                      filepath)));

              writer.write(document);

              writer.close();

          }

      } catch (Exception ex) {

          ex.printStackTrace();

      }

      return accessToken;

  }
}
