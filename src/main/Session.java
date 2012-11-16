package main;

import java.math.BigInteger;

import utils.DSAUtils;

public class Session {
	
	//Singleton
	/*Attribute global public key, it is created in the constructor
	 * Methods closeSession() generateKeys()
	 */
	private static Session session = new Session();
	private BigInteger globalKeyP;
	private BigInteger globalKeyQ;
	private BigInteger globalKeyG;
	private int L = 512;
	
	private Session(){
		globalKeyQ = DSAUtils.getPrime(160);
		//P
		BigInteger two = BigInteger.valueOf(2);
		BigInteger min = two.pow(L-1);
		BigInteger max = two.pow(L);
		BigInteger p = DSAUtils.getPrime(new Integer(L-1));
		//Comprobar que p es mayor que min.
		
		
	}
	

	public static Session getInstance() {
        return session;
    }
	

	

}
