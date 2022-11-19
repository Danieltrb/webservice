package convertisseur.service.data;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class Money {
	private String fromCode; 
	public float rate; 
	public Money() {}

	public Money(String fromCode) { 
		this.fromCode = fromCode;
		this.rate = (float) 0;
		this.rate = Transform.Transform(fromCode,"EUR",1);
	}
	
	public float getRate() { 
		return rate;
	}
	public void setfromCode(String fromCode) { 
		this.fromCode = fromCode;
	}
//	public Float getGrade() { 
//		return grade;
//	}
	public void setRate(String fromCode){ 
		this.rate = Transform.Transform(fromCode,"EUR",1);
	}

	public String getfromCode() { 
		return this.fromCode;
	}
	
	public void setrate(String fromCode) { 
		this.rate = Transform.Transform(fromCode,"EUR",1);
	}
	
	@Override
	public String toString(){
		return fromCode + "::" + rate ; }
	}