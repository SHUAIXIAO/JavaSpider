package JavaSpiderOfCarsales;

import java.io.BufferedReader;  
import java.io.InputStreamReader;  
import java.net.URL;  
import java.net.URLConnection;  
import java.util.ArrayList;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  

public class Spider {

	static String SendGet(String url) {     //网页内容抓取函数开始
        // 定义一个字符串用来存储网页内容  
        String result = "";  
        // 定义一个缓冲字符输入流  
        BufferedReader in = null;  
  
        try {  
            // 将string转成url对象  
            URL realUrl = new URL(url);   //将string类型转换为URL对象
            // 初始化一个链接到那个url的连接  
            URLConnection connection = realUrl.openConnection();  
            // 开始实际的连接  
            connection.connect();  
            // 初始化 BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(  
                    connection.getInputStream()));  
            // 用来临时存储抓取到的每一行的数据  
            String line;  
            while ((line = in.readLine()) != null) {  
                // 遍历抓取到的每一行并将其存储到result里面  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送GET请求出现异常！" + e);  
            e.printStackTrace();  
        }  
        // 使用finally来关闭输入流  
        finally {  
            try {  
                if (in != null) {  
                    in.close();  
                }  
            } catch (Exception e2) {  
                e2.printStackTrace();  
            }  
        }  
        return result;  
  
    }  //网页内容抓取函数结束
  
	
	static ArrayList<Record> GetRecord(String content) {  
	
        // 预定义一个ArrayList来存储结果  
        ArrayList<Record> results = new ArrayList<Record>();  
        
        // 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容  
          Pattern UrlPattern = Pattern.compile("\"n_width-max title \">[\r\n ]+<a href=\"(.+?)\" data-csn");//添加正则表达式
          Pattern TypePattern = Pattern.compile("Body</div>[\r\n ]+<div class=\"feature-text\">(.+?)</div>");
          Pattern MeterPattern = Pattern.compile("Odometer</div>[\r\n ]+<div class=\"feature-text\">(.+?)</div>");
          Pattern TransPattern = Pattern.compile("Transmission</div>[\r\n ]+<div class=\"feature-text\">(.+?)</div>");
          Pattern EnginePattern = Pattern.compile("Engine</div>[\r\n ]+<div class=\"feature-text\">(.+?)</div>");
          Pattern PricePattern = Pattern.compile("<div class=\"price\">(.+?)</div>");
         
        
        // 定义一个matcher用来做匹配  
        Matcher UrlMatcher = UrlPattern.matcher(content); 
        Matcher TypeMatcher = TypePattern.matcher(content);  
        Matcher MeterMatcher = MeterPattern.matcher(content);
        Matcher TransMatcher = TransPattern.matcher(content);  
        Matcher EngineMatcher = EnginePattern.matcher(content);  
        Matcher PriceMatcher = PricePattern.matcher(content);  
        
        
        
        
      
        boolean isFind =  UrlMatcher.find()&& TypeMatcher.find() &&MeterMatcher.find() && TransMatcher.find()&& EngineMatcher.find()&&PriceMatcher.find();
        
        //&& TitleMatcher.find()
        
       //匹配Title数据
        	
        	 
        	 //Pattern YearPattern = Pattern.compile("(.+?)");
        	// Pattern BrandPattern = Pattern.compile("(.+?)");
        	// Pattern ModePattern = Pattern.compile("(.+?)");
        	
        	// Matcher YearMatcher = YearPattern.matcher(Title);
        	// Matcher BrandMatcher = BrandPattern.matcher(Title);
        	// Matcher ModeMatcher = ModePattern.matcher(Title);
        
        	

        while (isFind)   
        	
           
        {
        
        	
        	//添加成功匹配的结果 
        	 Record RecordTemp = new Record();  
        	 
        	 String Title = UrlMatcher.group(1);
        	// System.out.println("*"+Title+"*");
        	
        	 Pattern YearPattern = Pattern.compile("details/.+?-.+?-(.+?)/");
        	 Pattern BrandPattern = Pattern.compile("details/(.+?)-");
        	 Pattern ModePattern = Pattern.compile("details/.+?-(.+?)-");
        	 Pattern AdtypePattern = Pattern.compile("/(.+?)/");
        	 Pattern IDPattern = Pattern.compile("/.+/.+/.+/(.+?)/");
        	 
        	 Matcher YearMatcher = YearPattern.matcher(Title);
        	 Matcher BrandMatcher = BrandPattern.matcher(Title);
        	 Matcher ModeMatcher = ModePattern.matcher(Title);
        	 Matcher AdtypeMatcher = AdtypePattern.matcher(Title);
        	 Matcher IDMatcher = IDPattern.matcher(Title);
        	 
        	 if(AdtypeMatcher.find() && BrandMatcher.find() && IDMatcher.find() && ModeMatcher.find() && YearMatcher.find() ){
        	 RecordTemp.Year = YearMatcher.group(1);
        	 RecordTemp.Brand = BrandMatcher.group(1);
        	 RecordTemp.Mode = ModeMatcher.group(1);
        	 RecordTemp.Adtype = AdtypeMatcher.group(1);
        	 RecordTemp.ID = "S"+IDMatcher.group(1);
        	 }
        	 RecordTemp.Url = "http://www.carsales.com.au"+UrlMatcher.group(1);
        	 RecordTemp.Type = TypeMatcher.group(1);
        	 RecordTemp.Meter = MeterMatcher.group(1);
        	 RecordTemp.Trans = TransMatcher.group(1);
        	 RecordTemp.Engine = EngineMatcher.group(1);
        	 RecordTemp.Price = PriceMatcher.group(1);
        	
        	 
			
        	 //System.out.println(RecordTemp.Url +" "+ RecordTemp.Title +" "+ RecordTemp.Meter +" "+ RecordTemp.Trans +" "+ RecordTemp.Engine+" "+ RecordTemp.Price);	
			
		    
			
			results.add(RecordTemp);
				 
   
			isFind =  UrlMatcher.find()&& TypeMatcher.find() &&MeterMatcher.find() && TransMatcher.find()&& EngineMatcher.find()&&PriceMatcher.find();
            //&& TitleMatcher.find()
        }
        
        return results; 
	}



}
