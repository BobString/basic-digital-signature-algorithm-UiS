package main;

import java.math.BigInteger;
import java.util.Random;

import utils.DSAUtils;

import com.sun.tools.javac.util.Pair;

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
		globalKeyQ = DSAUtils.getPrime(161);
		debugMode("[OK]", true);

		// ==P==
		debugMode("Creating global key P .......... ", false);
		BigInteger two = BigInteger.valueOf(2);
		BigInteger min = two.pow(L - 1);
		// BigInteger max = two.pow(L);

		//debugMode("		Creating next prime of min .......... ", false);
		Integer l1 = (L - 1);
		BigInteger p = DSAUtils.nextPrime(min);
		//debugMode("[OK]", true);
		//debugMode("		Ensuring that min < p .......... ", false);
		while (min.compareTo(p) >= 0) {
			p = DSAUtils.getPrime(l1);
			// This ensure that min < p and p never is going to be greater than
			// L
		}
		//debugMode("[OK]", true);
		globalKeyP = p;
		debugMode("[OK]", true);
		
		// ==G==
		debugMode("Creating global key G .......... ", false);
		BigInteger p1 = globalKeyP.subtract(BigInteger.ONE);
		BigInteger exp = p1.divide(globalKeyQ);

		BigInteger tempg;
		Random random = new Random();
		do {
	        tempg = new BigInteger(p1.bitLength(), random);
	    } while (tempg.compareTo(p1) != -1 && tempg.compareTo(BigInteger.ONE) != 1);
		globalKeyG = tempg.modPow(exp, p);
		debugMode("[OK]", true);
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
	
	public Pair<BigInteger, BigInteger> getPrivateKey(){
		
		
		//Private key
		BigInteger privK = DSAUtils.getPrime(getGlobalKeyQ().bitLength() - 1);
		
		//Public key: 
		BigInteger pubK = getGlobalKeyG().modPow(privK, getGlobalKeyP());
		
		Pair<BigInteger, BigInteger> result = Pair.of(privK, pubK);
		
		return result;
	}

	public void destroy() {
		globalKeyP = null;
		globalKeyQ = null;
		globalKeyG = null;
		session = null;
	}

}
