package main;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
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
	private static int L = 512;

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
		BigInteger p;
		BigInteger pMinusOne;
		System.out.println("Q: " + globalKeyQ);
		do {
			p = DSAUtils.getPrime(L + 1);
			pMinusOne = p.subtract(BigInteger.ONE);
			System.out.println(p.toString());
			System.out.println(p.bitLength());
		} while (!DSAUtils.primalityTest(p)
				|| p.bitLength() < L
				|| pMinusOne.remainder(globalKeyQ).compareTo(BigInteger.ZERO) != 0);

		// BigInteger p=DSAUtils.getPrime(L);
		// BigInteger tempp=p.subtract(BigInteger.ONE);
		// while (!DSAUtils.primalityTest(p) || p.bitLength() < L) {
		// p = DSAUtils.getPrime(L);
		// tempp = p.subtract(BigInteger.ONE);
		// p = p.subtract(tempp.remainder(globalKeyQ));
		// }

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
		} while (tempg.compareTo(p1) != -1
				&& tempg.compareTo(BigInteger.ONE) != 1);
		globalKeyG = tempg.modPow(exp, p);
		debugMode("[OK]", true);
	}

	public static void getPQ() throws NoSuchAlgorithmException {

		Boolean inicio = true;
		BigInteger pAux;
		BigInteger qAux;
		do {
			Integer n = (L - 1) / 160;
			Integer b = (L - 1) % 160;
			BigInteger SEED;
			Integer g;
			do {
				SEED = DSAUtils.primeCandidate(160);
				g = SEED.bitLength();

				// U = SHA[SEED] XOR SHA[(SEED+1) mod 2g ].
				MessageDigest md1 = null;
				MessageDigest md2 = null;
				BigInteger U = null;

				md1 = MessageDigest.getInstance("SHA-1");
				md1.update(SEED.toByteArray());
				BigInteger seedHash = new BigInteger(md1.digest());
				md2 = MessageDigest.getInstance("SHA-1");
				md2.update(SEED.subtract(BigInteger.ONE)
						.mod(BigInteger.valueOf(2 * g)).toByteArray());
				BigInteger seedMinus1Hash = new BigInteger(md2.digest());
				U = seedHash.xor(seedMinus1Hash);
				qAux = U;
				qAux.setBit(1);
				qAux.setBit(U.bitLength() - 1);

			} while (!DSAUtils.primalityTest(qAux));

			int counter = 0;
			int offset = 2;
			List<BigInteger> V = new ArrayList<BigInteger>();
			BigInteger W = BigInteger.ZERO;
			
			Boolean jump;
			do {

				for (int k = 0; k < n + 1; k++) {
					// Vk = SHA[(SEED + offset + k) mod 2g ].
					MessageDigest mdaux = null;
					BigInteger U = null;

					mdaux = MessageDigest.getInstance("SHA-1");
					mdaux.update(SEED.add(BigInteger.valueOf(offset + k))
							.mod(BigInteger.valueOf(2).pow(g)).toByteArray());
					 U = new BigInteger(mdaux.digest());
					V.add(U);
					// W = V0/font> + V1*2160 + ... + Vn-1*2(n-1)*160 + (Vn mod
					// 2b)
					// * 2n*160
					if (k == 0) {
						W = W.add(V.get(0));
					} else {
						if (k == n) {
							W = W.add((V.get(k).mod(BigInteger.valueOf(2 * b))
									.multiply(BigInteger.valueOf((long) Math
											.pow(2.0, k * 160.0)))));
						}
						W = W.add((V.get(k).multiply(BigInteger
								.valueOf((long) Math.pow(2.0, k * 160.0)))));
					}
				}

				BigInteger X = W.add(BigInteger.valueOf(2).pow(L - 1));
				// c = X mod 2q and set p = X - (c - 1). Note that p is
				// congruent to
				// 1 mod 2q.
				BigInteger c = X.mod(qAux.multiply(BigInteger.valueOf(2)));
				pAux = X.subtract(c.subtract(BigInteger.ONE));
				jump = !DSAUtils.primalityTest(pAux);
				if (jump) {
					// Step 13. Let counter = counter + 1 and offset = offset +
					// n + 1.
					// Step 14. If counter > or = to 212 = 4096 go to step 1,
					// otherwise (i.e. if counter < 4096) go to step 7.
					counter++;
					offset = offset + n + 1;
					if (counter >= 4096) {
						inicio = false;
					}
				}

			} while (jump);
		} while (inicio);

		System.out.println(pAux);
		System.out.println(qAux);

	}

	/**
	 * This method is the one that gives you the instance of Session or create a
	 * new one if it isn't created.
	 * 
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
	 * @param String
	 *            s, the string to write.
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
	 * @return Pair<BigInteger, BigInteger> the pair of (x,y) the private and
	 *         the public personal key
	 */
	public Pair<BigInteger, BigInteger> getPrivateKey() {

		debugMode("=== CREATION OF PRIVATE KEY ===", true);
		// Private key
		debugMode("Creating private key X .......... ", false);
		BigInteger privK = DSAUtils.getPrime(getGlobalKeyQ().bitLength());
		while (privK.compareTo(globalKeyQ) != -1) {
			privK = DSAUtils.getPrime(getGlobalKeyQ().bitLength());
		}
		debugMode("[OK]", true);
		debugMode("Creating private key Y .......... ", false);
		// Public key:
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
