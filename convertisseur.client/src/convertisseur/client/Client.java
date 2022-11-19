package convertisseur.client;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.apache.cxf.jaxrs.client.*; 
import convertisseur.service.data.*;

public class Client {
	private static String webServiceUrl = "http://localhost:8080/convertisseur.serivce/api/money";
	public static void main(String[] args) {
	Money Code = new Money(add("CNY"));
	System.out.println(Code.getRate());
	Money money = get("CNY");
	System.out.println(money.getRate());
	}
	
	private static String add(String fromCode){ 
		System.out.print("Check " + fromCode + "... "); 
		WebClient c = WebClient.create(webServiceUrl); 
		Money s = new Money(fromCode);
		Response r = c.post(s); 
		if(r.getStatus() == 400) {
			System.out.println("Oops!");
			return null; }
		String uri = r.getHeaderString("Content-Location"); 
		System.out.println("Check Fini");
		System.out.println(uri);
		System.out.println((uri.substring(uri.lastIndexOf('/')+1)));
		return s.getfromCode();
	}
//
	private static Boolean delete(String fromCode) { 
		System.out.print("Deleting " + fromCode + "... ");
		WebClient c = WebClient.create(webServiceUrl).path(fromCode); 
		int status = c.delete().getStatus();
		if(status == 200) {
			System.out.println("Delete Fini.");
			return true; }
		System.out.println("Oops!");
		return false; }
	
	private static Money get(String fromCode) { 
		System.out.print("Getting " + fromCode + "... ");
		WebClient c = WebClient.create(webServiceUrl).path(fromCode); 
		Money s = null;
	try {
		System.out.println(1); 
		s = c.get(Money.class);
		System.out.println(s.toString()); 
		} 
	catch(NotFoundException e) {
			System.out.println("Oops!"); }
		return s; 
	}
	

}


