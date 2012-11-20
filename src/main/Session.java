package main;

import java.math.BigInteger;

import utils.DSAUtils;

/**
 * @author robertomm Singleton class
 */
public class Session {

	// private static Session session = new Session();
	private static Session session;
	private static boolean debug;
	private BigInteger globalKeyP;
	private BigInteger globalKeyQ;
	private BigInteger globalKeyG;
	private int L = 512;

	/**
	 * The private constructor (because it is a sigleton class) generate the
	 * public keys
	 * 
	 */
	private Session(boolean b) {
		if (b) {
			debug = true;
		} else {
			debug = false;
		}

		// ==Q==
		debugMode("Creating global key Q .......... ", false);
		globalKeyQ = DSAUtils.getPrime(160);
		debugMode("[OK]", true);

		// ==P==
		debugMode("Creating global key P: ", true);
		BigInteger two = BigInteger.valueOf(2);
		BigInteger min = two.pow(L - 1);
		// BigInteger max = two.pow(L);

		debugMode("		Creating next prime of min .......... ", false);
		Integer l1 = (L - 1);
		BigInteger p = DSAUtils.nextPrime(min);
		debugMode("[OK]", true);
		debugMode("		Ensuring that min < p .......... ", false);
		while (min.compareTo(p) >= 0) {
			p = DSAUtils.getPrime(l1);
			System.out.println("PIN: " + p);
			System.out.println("MIN: " + min);
			// This ensure that min < p and p never is going to be greater than
			// L
		}
		debugMode("[OK]", true);
		globalKeyP = p;
		debugMode("Global key P succesfully created.", true);

		// ==G==
		BigInteger exp = globalKeyP.subtract(BigInteger.ONE);
		exp = exp.divide(globalKeyQ);
		// H is going to be 2 and pass the first condition 1 < h < p-1 Let's see
		// if it pass the second one
		System.out.println(exp);
		BigInteger g = DSAUtils.pow(two, exp);
		// With this, we ensure that the second condition is approved
		Integer inte = 3;
		while (!(g.mod(p).compareTo(BigInteger.ONE) > 1)) {
			BigInteger aux = BigInteger.valueOf(inte);
			g = DSAUtils.pow(aux, exp);
			inte++;
		}
		globalKeyG = g;

	}

	public static Session getInstance(boolean debugMode) {
		if (session == null)
			session = new Session(debugMode);

		return session;

	}

	/**
	 * @return the globalKeyP
	 */
	public BigInteger getGlobalKeyP() {
		return globalKeyP;
	}

	/**
	 * @return the globalKeyQ
	 */
	public BigInteger getGlobalKeyQ() {
		return globalKeyQ;
	}

	/**
	 * @return the globalKeyG
	 */
	public BigInteger getGlobalKeyG() {
		return globalKeyG;
	}

	/**
	 * @param s
	 * @param ln
	 */
	private void debugMode(String s, boolean ln) {
		if (debug) {
			if (ln) {
				System.out.println(s);
			} else {
				System.out.print(s);
			}
		}
	}

	public void destroy() {
		globalKeyP = null;
		globalKeyQ = null;
		globalKeyG = null;
		session = null;
	}

}
