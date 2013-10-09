package main;

import gui.Pair;

import java.math.BigInteger;
import java.security.MessageDigest;

import utils.DSAUtils;

/**
 * @author robertomm
 * 
 */
public class DSA {

	/*
	 * Static methods -> (M,r,s) sign(M, claves) Boolean verify(M, r, s)
	 */
	private static boolean debug;

	/**
	 * Method to create the signature
	 * 
	 * @param deb
	 *            , boolean for activate the debug mode
	 * @param message
	 *            , string to make the signature
	 * @param publcG
	 *            , the global public g key
	 * @param publicP
	 *            , the global public p key
	 * @param publicQ
	 *            , the global public q key
	 * @param privateX
	 *            , the personal private x key
	 * @return Pair<String, Pair<BigInteger, BigInteger>>, Pair of the message
	 *         and another Pair with (r,s)
	 */
	public static Pair<String, Pair<BigInteger, BigInteger>> sign(boolean deb,
			String message, BigInteger publcG, BigInteger publicP,
			BigInteger publicQ, BigInteger privateX) {

		debug = deb;
		debugMode("=== CREATING SIGNATURE ===", true);

		// K
		debugMode("Creating auxiliar variable K .......... ", false);
		BigInteger k = DSAUtils.getPrime(publicQ.bitLength());
		while (k.compareTo(publicQ) != -1) {
			k = DSAUtils.getPrime(publicQ.bitLength());
		}
		debugMode("[OK]", true);

		// R
		debugMode("Creating R .......... ", false);
		BigInteger r = publcG.modPow(k, publicP).mod(publicQ);
		debugMode("[OK]", true);

		// S
		debugMode("Creating S .......... ", false);
		MessageDigest md = null;
		BigInteger s = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(message.getBytes());
			BigInteger hash = new BigInteger(md.digest());
			s = (k.modInverse(publicQ).multiply(hash.add(privateX.multiply(r))))
					.mod(publicQ);
		} catch (Exception e) {
			e.printStackTrace();
		}
		debugMode("[OK]", true);
		Pair<String, Pair<BigInteger, BigInteger>> result = new Pair<String, Pair<BigInteger, BigInteger>>(
				message, new Pair<BigInteger, BigInteger>(r, s));
		return result;
	}

	/**
	 * Method to verify the integrity of a message
	 * 
	 * @param deb
	 *            , boolean for activate the debug mode
	 * @param message
	 *            , string to make the signature
	 * @param r
	 *            , signature value
	 * @param s
	 *            , signature value
	 * @param publcG
	 *            , the global public g key
	 * @param publicP
	 *            , the global public p key
	 * @param publicQ
	 *            , the global public q key
	 * @param privateX
	 *            , the personal private x key
	 * @return if the message is verified with (r,s)
	 */
	public static Boolean verify(boolean deb, String message, BigInteger r,
			BigInteger s, BigInteger publcG, BigInteger publicP,
			BigInteger publicQ, BigInteger privateY) {
		debugMode("=== VERIFY SIGNATURE ===", true);
		debug = deb;
		MessageDigest md;
		BigInteger v = BigInteger.ZERO;
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(message.getBytes());
			BigInteger messagehash = new BigInteger(md.digest());
			debugMode("Creating W .......... ", false);
			BigInteger w = s.modInverse(publicQ);
			debugMode("[OK]", true);
			debugMode("Creating U1 .......... ", false);
			BigInteger u1 = messagehash.multiply(w).mod(publicQ);
			debugMode("[OK]", true);
			debugMode("Creating U2 .......... ", false);
			BigInteger u2 = r.multiply(w).mod(publicQ);
			debugMode("[OK]", true);
			debugMode("Creating V .......... ", false);
			v = ((publcG.modPow(u1, publicP).multiply(privateY.modPow(u2,
					publicP))).mod(publicP)).mod(publicQ);
			debugMode("[OK]", true);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return v.compareTo(r) == 0;
	}

	/**
	 * @param String
	 *            s, the string to write.
	 * @param boolean ln, if we want line break.
	 */
	private static void debugMode(String s, boolean ln) {
		if (debug) {
			if (ln) {
				System.out.println(s);
			} else {
				System.out.print(s);
			}
		}
	}

}
