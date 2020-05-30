package com.cx.chenxing.utils.zj;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
* 获取经纬度
*
* @author jueyue 返回格式：Map<String,Object> map map.put("status",
* reader.nextString());//状态 map.put("result", list);//查询结果
* list<map<String,String>>
* 密钥:f247cdb592eb43ebac6ccd27f796e2d2
*/
public class GetLatAndLngByBaiduUtil {
    /**
    * @param addr
    * 查询的地址
    * @return
    * @throws IOException
    */
    public Object[] getCoordinate(String addr) throws IOException {
        String lng = null;//经度
        String lat = null;//纬度
        String address = null;
        try {
            address = java.net.URLEncoder.encode(addr, "UTF-8");
        }catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String key = "f247cdb592eb43ebac6ccd27f796e2d2";
        String url = String.format("http://api.map.baidu.com/geocoder?address=%s&output=json&key=%s", address, key);
        URL myURL = null;
        URLConnection httpsConn = null;
        try {
            myURL = new URL(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader insr = null;
        BufferedReader br = null;
        try {
            httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
            if (httpsConn != null) {
                insr = new InputStreamReader( httpsConn.getInputStream(), "UTF-8");
                br = new BufferedReader(insr);
                String data = null;
                int count = 1;
                while((data= br.readLine())!=null){
                    if(count==5){
                        lng = (String)data.subSequence(data.indexOf(":")+1, data.indexOf(","));//经度
                        count++;
                    }else if(count==6){
                        lat = data.substring(data.indexOf(":")+1);//纬度
                        count++;
                    }else{
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(insr!=null){
                insr.close();
            }
            if(br!=null){
                br.close();
            }
        }
        return new Object[]{lng,lat};
    }

    public static void main(String[] args) throws IOException {
        //GetLatAndLngByBaiduUtil getLatAndLngByBaidu = new GetLatAndLngByBaiduUtil();
        //Object[] o = getLatAndLngByBaidu.getCoordinate("河北省张家口市沽源县新长途汽车站");
        //System.out.println(o[0]);//经度
        //System.out.println(o[1]);//纬度
        System.out.println(getCityFromLngAndlat(39.978398, 116.407672));
    }

    public static String getCityFromLngAndlat(Object j, Object w) throws IOException{
    //通过修改这里的location（经纬度）参数，即可得到相应经纬度的详细信息
    String url2 = "http://api.map.baidu.com/geocoder/v2/?ak=ga2vOgjKiex14wPRkTblnAHYIB2bWrTy&location="+j+","+w+"&output=json&pois=1";
    System.out.println(url2);
            URL myURL2 = null;
            URLConnection httpsConn2 = null;
            String city = "";
            try {
                myURL2 = new URL(url2);
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }
            InputStreamReader insr2 = null;
            BufferedReader br2 = null;
            try {
                httpsConn2 = (URLConnection) myURL2.openConnection();// 不使用代理
                if (httpsConn2 != null) {
                    insr2 = new InputStreamReader( httpsConn2.getInputStream(), "UTF-8");
                    br2 = new BufferedReader(insr2);
                    String data2 = br2.readLine();
                    try
                    {
                       //解析得到的json格式数据
                        JSONObject dataJson = new JSONObject(data2);
                        JSONObject result = dataJson.getJSONObject("result");
                        //JSONObject addressComponent = result.getJSONObject("addressComponent");
                        //city = addressComponent.getString("city");
                        city = result.getString("formatted_address");

                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(insr2!=null){
                    insr2.close();
                }
                if(br2!=null){
                    br2.close();
                }
            }
            return city;
    }




    /*
     * 调用以下路径根据经纬度获取地址，返回数据为JSON格式，
     * 116.40387397
     * 39.91488908
     * http://api.map.baidu.com/geocoder/v2/?ak=rBPm1r98veaCsVfLxLdAbLHd6w9j8ipn&location=39.91488908,116.40387397&output=json&pois=1
     * http://api.map.baidu.com/geocoder/v2/?ak=f247cdb592eb43ebac6ccd27f796e2d2&location=41.580404,115.636092&output=json&pois=1
     * http://api.map.baidu.com/geocoder/v2/?ak=pmCgmADsAsD9rEXkqWNcTzjd&location=41.580404,115.636092&output=json&pois=1
     * 返回结果：
     * {
     * 	"status":0,
     * 	"result":{
     * 		"location":{
     * 			"lng":115.63609199999994,
     * 			"lat":41.58040389999715
     * 		},
     * 	"formatted_address":"河北省张家口市沽源县S242",
     * 	"business":"",
     * 	"addressComponent":{
     * 		"country":"中国",
     * 		"country_code":0,
     * 		"province":"河北省",
     * 		"city":"张家口市",
     * 		"district":"沽源县",
     * 		"adcode":"130724",
     * 		"street":"S242",
     * 		"street_number":"",
     * 		"direction":"",
     * 		"distance":""
     * 	},
     * 	"pois":[{
     * 		"addr":"平定堡镇后湾子林场路46号",
     * 		"cp":" ",
     * 		"direction":"附近",
     * 		"distance":"0",
     * 		"name":"张家口市平定林木开发有限公司",
     * 		"poiType":"公司企业",
     * 		"point":{
     * 			"x":115.63609251561631,
     * 			"y":41.58039859151349
     * 		},
     * 		"tag":"公司企业",
     * 		"tel":"",
     * 		"uid":"368c871f69b8448de01103c0",
     * 		"zip":""
     * 	},
     * 	{
     * 		"addr":"张家口沽源县小梁村",
     * 		"cp":" ",
     * 		"direction":"附近",
     * 		"distance":"1",
     * 		"name":"坝上庆源农家院",
     *		"poiType":"休闲娱乐",
     * 		"point":{
     * 			"x":115.63608353256122,
     * 			"y":41.58039859151349
     *		 },
     * 		"tag":"休闲娱乐;农家院",
     * 		"tel":"",
     * 		"uid":"185557d8ed9e6021ed74f8d6",
     * 		"zip":""
     * 	}],
     * 	"poiRegions":[],
     * 	"sematic_description":"张家口市平定林木开发有限公司附近0米",
     *	"cityCode":264
     * 	}
     * }
     */
}
