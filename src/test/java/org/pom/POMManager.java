package org.pom;


public class POMManager {
	public static POMManager pomManager;
	private Ebay ebay;
	private POMManager() {
	}

	public static POMManager getPomManager() {
      if (pomManager==null) {
    	  pomManager = new POMManager();
	}
		return pomManager;
	}
	
	public  Ebay geteBay() {
		ebay=new Ebay();		
		return ebay;
	}
}
