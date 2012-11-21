package main;

import java.math.BigInteger;
import java.security.MessageDigest;

import utils.DSAUtils;

import com.sun.tools.javac.util.Pair;

public class DSA {

	/*
	 * Static methods -> (M,r,s) sign(M, claves) Boolean verify(M, r, s)
	 */

	public static Pair<String, Pair<BigInteger, BigInteger>> sign(String message,
			BigInteger publcG, BigInteger publicP, BigInteger publicQ,
			BigInteger privateX) {
		// K
		BigInteger k = DSAUtils.getPrime(publicQ.bitLength() - 1);

		// R
		BigInteger r = publcG.modPow(k, publicP).mod(publicQ);

		// S
		MessageDigest md = null;
		BigInteger s = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(message.getBytes());
			BigInteger hash = new BigInteger(md.digest());
			s = (k.modInverse(publicP).multiply(hash.add(privateX.multiply(r))))
					.mod(publicQ);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Pair<String, Pair<BigInteger, BigInteger>> result = Pair.of(message,
				Pair.of(r, s));
		return result;
	}

	public static Boolean verify(String message, BigInteger r, BigInteger s,
			BigInteger publcG, BigInteger publicP, BigInteger publicQ,
			BigInteger privateY) {
		MessageDigest md;
		BigInteger v = BigInteger.ZERO;
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(message.getBytes());
			BigInteger messagehash = new BigInteger(md.digest());
			BigInteger w = s.modInverse(publicQ);
			BigInteger u1 = messagehash.multiply(w).mod(publicQ);
			BigInteger u2 = r.multiply(w).mod(publicQ);
			v = ((publcG.modPow(u1, publicP).multiply(privateY.modPow(u2, publicP))).mod(publicP)).mod(publicQ);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		if (v.equals(r)) {
			return true;
		} else {
			return false;
		}
	}

}
