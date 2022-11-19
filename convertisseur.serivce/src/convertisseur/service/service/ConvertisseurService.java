package convertisseur.service.service;
import java.util.*;
import convertisseur.service.data.*;

public class ConvertisseurService {
//	private static Map<String, Money> MONEY_DATA = new HashMap<String, Money>();
	private static Map<Integer, Money> MONEY_DATA = new HashMap<Integer, Money>();

	private int getNewId() {
		int newId = 0;
		for (int id : MONEY_DATA.keySet()) {
		if (newId < id) newId = id;
		}
		return ++newId;
	}
	//1.0
//	public Money addMoney(Money s) { 
//		String fromCode = s.getfromCode();
////		s.setRate();
////		System.out.println("addmoney"+s.getRate());
//		if(MONEY_DATA.get(s.getfromCode()) != null) {
//		return null; 
//		}
////		s.setRate(fromCode);
//		MONEY_DATA.put(fromCode, s); 
////		s.setRate(fromCode);
//		return s;
//	}
	
	//2.0
	
	public Money addMoney(Money money) { 
		int id = getNewId(); 
		String fromCode = money.getfromCode();
		if(MONEY_DATA.get(money.getfromCode()) != null) {
		return null; 
		}
//		money.setRate(fromCode);
		MONEY_DATA.put(id, money); 
		return money;
	}
	
	public boolean deleteMoney(String fromCode) { 
		if(MONEY_DATA.get(fromCode) == null) {
			return false; }
		MONEY_DATA.remove(fromCode);
		return true; }
	
	
	public Money getMoney(String fromCode) { 
		Money money = new Money(fromCode);
//		for (Integer i : MONEY_DATA.keySet()) {
//			money=MONEY_DATA.get(0);
//			if((money.getfromCode()).equals(fromCode)){
//				return money;
//			}
//		}
		return money;
	} }