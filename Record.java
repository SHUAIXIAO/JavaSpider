package JavaSpiderOfCarsales;
 

public class Record {

	public String Year;
	public String Brand;
	public String Mode;
	public String Type;
	public String Meter;
	public String Trans;
	public String Engine;
	public String Price;
	public String ID;
	public String Url;
	public String Title;
	public String Adtype;
	 public Record() {  
		    Year = "";  
	        Brand = ""; 
	        Mode = "";
	        Type = "";
	        Trans = "";
	        Meter = "";
	        Engine = "";
	        Price = "";
	        ID = "";
	        Url = "";
	        Adtype = "";
	    
	    }  
	  
	    @Override  
	    public String toString() {  
	        return  " # " + Year + " # "  + Brand + " # "+ Mode +" # "+ Type + " # " + Trans + " # " + Meter + " # "+ Engine + " # " + Price + " # " + Adtype + " # " + ID + " # " + Url +"\n";  
	    }  



}
