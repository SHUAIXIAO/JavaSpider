package ������Ŀ;
import java.util.ArrayList;  

public class Main {  
  
    public static void main(String[] args) {  
        // ���弴�����ʵ�����  
       int caramount = 48721;
    	
       for (int i = 0; i < caramount ; i= i + 12 ){	
    		String url = "http://www.carsales.com.au/cars/results?offset="+i+"&q=%28Service%3D%5BCarsales%5D%26%28State%3D%5BVictoria%5D%26Region%3D%5BMelbourne%5D%29%29&area=Stock&vertical=car&sortBy=TopDeal&WT.z_srchsrcx=makemodel";    // ���弴�����ʵ�����  ��Ҫ��������������
            // �������Ӳ���ȡҳ������  
            String content = Spider.SendGet(url);  
         // ��ȡ��ҳ������еļ�¼
            ArrayList<Record> myRecord = Spider.GetRecord(content); 
            // ��ӡ���   
            System.out.println(myRecord); 
          
           
       
         
        
    	}	
    	
    	
    	
    	
    	
    	
    }  
}  