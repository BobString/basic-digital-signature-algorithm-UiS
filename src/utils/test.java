package utils;


import java.math.BigInteger;

import main.DSA;
import main.Key;
import main.Session;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String tatiana = "Hi Tatiana i love you";

		Session session = Session.getInstance(true);

		Pair<BigInteger, BigInteger> privateKeys = session.getPrivateKey();

		Pair<BigInteger, BigInteger> sign = DSA.sign(true, tatiana,
				session.getGlobalKeyG(), session.getGlobalKeyP(),
				session.getGlobalKeyQ(), privateKeys.getFirst());

		System.out.println(DSA.verify(true, tatiana, sign.getFirst(),
				sign.getSecond(), session.getGlobalKeyG(),
				session.getGlobalKeyP(), session.getGlobalKeyQ(),
				privateKeys.getSecond()));

		session.destroy();

	}
}
