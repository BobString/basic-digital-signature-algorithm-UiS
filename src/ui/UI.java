package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

import main.DSA;
import main.Session;
import utils.Pair;

public class UI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/* Cypher typer */

		System.out.println("Please select option:");
		System.out.println("  1.Start session");
		System.out.println("  2.Verify message");

		String alg = null;
		try {
			alg = (new BufferedReader(new InputStreamReader(System.in)))
					.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (alg == null) {
			alg = "1";
		}

		switch (alg) {
		case "1":
			startSession();
			break;
		case "2":
			verifyMessage();
			break;
		default:
			System.exit(1);
			break;

		}

	}

	private static void startSession() {
		Session session = Session.getInstance(true);

		Pair<BigInteger, BigInteger> privateKeys = session.getPrivateKey();

		System.out.println("");
		System.out.println("Private key x: " + privateKeys.getFirst());
		System.out.println("Private key y: " + privateKeys.getSecond());
		System.out.println("");

		System.out.println("Please introduce message to sign:");
		String text = null;
		while (text == null) {
			try {
				text = (new BufferedReader(new InputStreamReader(System.in)))
						.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Pair<BigInteger, BigInteger> signature = DSA.sign(true, text,
				session.getGlobalKeyG(), session.getGlobalKeyP(),
				session.getGlobalKeyQ(), privateKeys.getFirst());
		System.out.println("Signature (r,s): (" + signature.getFirst() + ", "
				+ signature.getSecond() + ")");
		System.out.println("");
		System.out.println("Do you want to verify a message?");
		System.out.println("  1.Yes (y)");
		System.out.println("  2.No (press enter)");
		String alg = null;
		try {
			alg = (new BufferedReader(new InputStreamReader(System.in)))
					.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		switch (alg) {
		case "y":
			verifyMessage();
			break;
		default:
			System.out.println("Bye!");
			System.exit(1);
			break;

		}

	}

	private static void verifyMessage() {
		System.out.println("Please introduce message to verify:");
		String text = null;
		while (text == null) {
			try {
				text = (new BufferedReader(new InputStreamReader(System.in)))
						.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out
				.println("Please introduce the first element of the signature (r):");
		BigInteger r = null;
		while (r == null) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				String str = br.readLine();
				if (str.isEmpty()) {
					throw new RuntimeException(
							"Please don't enter an empty number");
				}
				r = new BigInteger(str, 10);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out
				.println("Please introduce the second element of the signature (s):");
		BigInteger s = null;
		while (s == null) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				String str = br.readLine();
				if (str.isEmpty()) {
					throw new RuntimeException(
							"Please don't enter an empty number");
				}
				s = new BigInteger(str, 10);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("Please introduce the public key (p):");
		BigInteger p = null;
		while (p == null) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				String str = br.readLine();
				if (str.isEmpty()) {
					throw new RuntimeException(
							"Please don't enter an empty number");
				}
				p = new BigInteger(str, 10);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("Please introduce the public key (q):");
		BigInteger q = null;
		while (q == null) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				String str = br.readLine();
				if (str.isEmpty()) {
					throw new RuntimeException(
							"Please don't enter an empty number");
				}
				q = new BigInteger(str, 10);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("Please introduce the public key (g):");
		BigInteger g = null;
		while (g == null) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				String str = br.readLine();
				if (str.isEmpty()) {
					throw new RuntimeException(
							"Please don't enter an empty number");
				}
				g = new BigInteger(str, 10);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("Please introduce the private key (y):");
		BigInteger y = null;
		while (y == null) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				String str = br.readLine();
				if (str.isEmpty()) {
					throw new RuntimeException(
							"Please don't enter an empty number");
				}
				y = new BigInteger(str, 10);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("");
		Boolean res = DSA.verify(true, text, r, s, g, p, q, y);
		
		System.out.println("=== VERIFICATION STATUS ===");
		if (res) {
			System.out.println("Approved!!");
		} else {
			System.out.println("Failed! (Hope is not my fault)");
		}
	}
}
