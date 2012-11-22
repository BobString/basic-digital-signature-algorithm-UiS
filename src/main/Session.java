package main;

import java.math.BigInteger;
import java.util.Random;

import utils.DSAUtils;

import com.sun.tools.javac.util.Pair;

/**
 * @author robertomm 
 */
public class Session {

	private static Session session;
	private static boolean debug;
	private BigInteger globalKeyP;
	private BigInteger globalKeyQ;
	private BigInteger globalKeyG;
	private int L = 512;

	
	/**
	 * @param boolean b, debug mode
	 */
	private Session(boolean b) {
		if (b) {
			debug = true;
		} else {
			debug = false;
		}
		debugMode("=== CREATION OF GLOBAL KEYS ===", true);

		// ==Q==
		debugMode("Creating global key Q .......... ", false);
		globalKeyQ = DSAUtils.getPrime(161);
		debugMode("[OK]", true);

		// ==P==
		debugMode("Creating global key P .......... ", false);
		BigInteger p=DSAUtils.getPrime(L);
	    BigInteger tempp=p.subtract(BigInteger.ONE);
	    while (!DSAUtils.primalityTest(p) || p.bitLength() != L) {
	        p = DSAUtils.getPrime(L + 1);
	        tempp = p.subtract(BigInteger.ONE);
	        p = p.subtract(tempp.remainder(globalKeyQ));
	    } 
		
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

	/**
	 * This method is the one that gives you the instance of Session or create a new one if it isn't created.
	 * @param boolean debugMode
	 * @return Object Session
	 */
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
	 * @param String s, the string to write.
	 * @param boolean ln, if we want line break.
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
	
	/**
	 * @return Pair<BigInteger, BigInteger> the pair of (x,y) the private and the public personal key
	 */
	public Pair<BigInteger, BigInteger> getPrivateKey(){
		
		debugMode("=== CREATION OF PRIVATE KEY ===", true);
		//Private key
		debugMode("Creating private key X .......... ", false);
		BigInteger privK = DSAUtils.getPrime(getGlobalKeyQ().bitLength());
		while(privK.compareTo(globalKeyQ) != -1){
			privK = DSAUtils.getPrime(getGlobalKeyQ().bitLength());
		}
		debugMode("[OK]", true);
		debugMode("Creating private key Y .......... ", false);
		//Public key: 
		BigInteger pubK = getGlobalKeyG().modPow(privK, getGlobalKeyP());
		
		Pair<BigInteger, BigInteger> result = Pair.of(privK, pubK);
		debugMode("[OK]", true);
		
		return result;
	}

	/**
	 * Method to destroy the Session
	 */
	public void destroy() {
		debugMode("=== DESTROYING SESSION ===", true);
		globalKeyP = null;
		globalKeyQ = null;
		globalKeyG = null;
		session = null;
		debugMode("Session destroyed", true);
	}

}
