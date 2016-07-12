package JavaSpiderOfCarsales;
import java.util.ArrayList;  

public class Main {  
  
    public static void main(String[] args) {  
        // 定义即将访问的链接  
       int caramount = 48721;
    	
       for (int i = 0; i < caramount ; i= i + 12 ){	
    		String url = "http://www.carsales.com.au/cars/results?offset="+i+"&q=%28Service%3D%5BCarsales%5D%26%28State%3D%5BVictoria%5D%26Region%3D%5BMelbourne%5D%29%29&area=Stock&vertical=car&sortBy=TopDeal&WT.z_srchsrcx=makemodel";    // 定义即将访问的链接  需要解决如何批量导入
            // 访问链接并获取页面内容  
            String content = Spider.SendGet(url);  
         // 获取该页面的所有的记录
            ArrayList<Record> myRecord = Spider.GetRecord(content); 
            // 打印结果   
            System.out.println(myRecord); 
          
           
       
         
        
    	}	
    	
    	
    	
    	
    	
    	
    }  
}  
