package ������Ŀ;

import java.io.BufferedReader;  
import java.io.InputStreamReader;  
import java.net.URL;  
import java.net.URLConnection;  
import java.util.ArrayList;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  

public class Spider {

	static String SendGet(String url) {     //��ҳ����ץȡ������ʼ
        // ����һ���ַ��������洢��ҳ����  
        String result = "";  
        // ����һ�������ַ�������  
        BufferedReader in = null;  
  
        try {  
            // ��stringת��url����  
            URL realUrl = new URL(url);   //��string����ת��ΪURL����
            // ��ʼ��һ�����ӵ��Ǹ�url������  
            URLConnection connection = realUrl.openConnection();  
            // ��ʼʵ�ʵ�����  
            connection.connect();  
            // ��ʼ�� BufferedReader����������ȡURL����Ӧ  
            in = new BufferedReader(new InputStreamReader(  
                    connection.getInputStream()));  
            // ������ʱ�洢ץȡ����ÿһ�е�����  
            String line;  
            while ((line = in.readLine()) != null) {  
                // ����ץȡ����ÿһ�в�����洢��result����  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("����GET��������쳣��" + e);  
            e.printStackTrace();  
        }  
        // ʹ��finally���ر�������  
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
  
    }  //��ҳ����ץȡ��������
  
	
	static ArrayList<Record> GetRecord(String content) {  
	
        // Ԥ����һ��ArrayList���洢���  
        ArrayList<Record> results = new ArrayList<Record>();  
        
        // ����һ����ʽģ�壬����ʹ��������ʽ����������Ҫץ������  
          Pattern UrlPattern = Pattern.compile("\"n_width-max title \">[\r\n ]+<a href=\"(.+?)\" data-csn");//���������ʽ
          Pattern TypePattern = Pattern.compile("Body</div>[\r\n ]+<div class=\"feature-text\">(.+?)</div>");
          Pattern MeterPattern = Pattern.compile("Odometer</div>[\r\n ]+<div class=\"feature-text\">(.+?)</div>");
          Pattern TransPattern = Pattern.compile("Transmission</div>[\r\n ]+<div class=\"feature-text\">(.+?)</div>");
          Pattern EnginePattern = Pattern.compile("Engine</div>[\r\n ]+<div class=\"feature-text\">(.+?)</div>");
          Pattern PricePattern = Pattern.compile("<div class=\"price\">(.+?)</div>");
         
        
        // ����һ��matcher������ƥ��  
        Matcher UrlMatcher = UrlPattern.matcher(content); 
        Matcher TypeMatcher = TypePattern.matcher(content);  
        Matcher MeterMatcher = MeterPattern.matcher(content);
        Matcher TransMatcher = TransPattern.matcher(content);  
        Matcher EngineMatcher = EnginePattern.matcher(content);  
        Matcher PriceMatcher = PricePattern.matcher(content);  
        
        
        
        
      
        boolean isFind =  UrlMatcher.find()&& TypeMatcher.find() &&MeterMatcher.find() && TransMatcher.find()&& EngineMatcher.find()&&PriceMatcher.find();
        
        //&& TitleMatcher.find()
        
       //ƥ��Title����
        	
        	 
        	 //Pattern YearPattern = Pattern.compile("(.+?)");
        	// Pattern BrandPattern = Pattern.compile("(.+?)");
        	// Pattern ModePattern = Pattern.compile("(.+?)");
        	
        	// Matcher YearMatcher = YearPattern.matcher(Title);
        	// Matcher BrandMatcher = BrandPattern.matcher(Title);
        	// Matcher ModeMatcher = ModePattern.matcher(Title);
        
        	

        while (isFind)   
        	
           
        {
        
        	
        	//��ӳɹ�ƥ��Ľ�� 
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
