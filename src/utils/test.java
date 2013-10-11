package utils;


import java.math.BigInteger;

import main.DSA;
import main.Session;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String message = "Hi I think I have an A";

		Session session = Session.getInstance(true);

		Pair<BigInteger, BigInteger> privateKeys = session.getPrivateKey();

		Pair<BigInteger, BigInteger> sign = DSA.sign(true, message,
				session.getGlobalKeyG(), session.getGlobalKeyP(),
				session.getGlobalKeyQ(), privateKeys.getFirst());

		System.out.println(DSA.verify(true, message, sign.getFirst(),
				sign.getSecond(), session.getGlobalKeyG(),
				session.getGlobalKeyP(), session.getGlobalKeyQ(),
				privateKeys.getSecond()));

		session.destroy();

	}
}
