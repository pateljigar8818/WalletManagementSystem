package com.demo.wms.util;

import java.util.HashMap;
import java.util.Map;

public enum CardTypeEnum {
	
	DEBIT_CARD(1,"DEBIT_CARD"),
	CREDIT_CARD(2,"CREDIT_CARD"),
	PREPAID_CARD(3,"PREPAID_CARD");
	
	private static final Map<Integer,String> map;
	public static final CardTypeEnum[] VALUES = values();
	public final String desc;
	public final int id;

	static {
		map = new HashMap<Integer, String>();
		for(CardTypeEnum cardTypeConstant : VALUES) {
			map.put(cardTypeConstant.id,cardTypeConstant.desc );
		}
	}
	
	private CardTypeEnum(int id,String desc){
		this.desc=desc;
		this.id=id;
	}
	
	public static String getNamebyID(int id){
		return map.get(id);
	}
	

}
