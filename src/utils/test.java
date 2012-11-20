package utils;

import java.math.BigInteger;

import main.Session;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Session session = Session.getInstance(true);
		 System.out.println(session.getGlobalKeyQ());
		 System.out.println(session.getGlobalKeyP());
		 System.out.println(session.getGlobalKeyG());



	}

	
}
