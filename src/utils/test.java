package utils;

import gui.Pair;

import java.math.BigInteger;

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
		Pair<String, Pair<BigInteger, BigInteger>> sign = DSA.sign(true,
				tatiana, session.getGlobalKeyG(), session.getGlobalKeyP(),
				session.getGlobalKeyG(), privateKeys.getFirst());

		System.out.println(DSA.verify(true, tatiana, sign.getSecond()
				.getFirst(), sign.getSecond().getSecond(), session
				.getGlobalKeyG(), session.getGlobalKeyP(), session
				.getGlobalKeyQ(), privateKeys.getSecond()));

		session.destroy();

	}

}
