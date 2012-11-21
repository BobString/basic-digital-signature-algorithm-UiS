package utils;

import java.math.BigInteger;
import java.util.Random;

import com.sun.tools.javac.util.Pair;

import main.DSA;
import main.Session;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Session session = Session.getInstance(true);

		 Pair<BigInteger, BigInteger> privateKeys = session.getPrivateKey();
		 
		 String tatiana = "Hi Tatiana i love you";
		 Pair<String, Pair<BigInteger, BigInteger>> sign = DSA.sign(tatiana, session.getGlobalKeyG(), session.getGlobalKeyP(), session.getGlobalKeyG(), privateKeys.fst);
		 
		 System.out.println(DSA.verify(tatiana, sign.snd.fst, sign.snd.snd, session.getGlobalKeyG(), session.getGlobalKeyP(), session.getGlobalKeyQ(), privateKeys.snd));
		 
		 session.destroy();


	}
	
	

	
}
