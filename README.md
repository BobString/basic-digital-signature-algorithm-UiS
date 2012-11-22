Digital Signature Algorithm
=================================
DSA stands for Digital Signature Algorithm and was released the 30 of August of 1991[1]. As it name says, the algorithm is only for sign not for cipher texts. One of it's disadvantages is that is harder to compute than RSA.
And the objective of this assignment is to implement that system in Java.

## Design and Implementation

We organized the code in several classes.Here he have the class Session, this class is the one that we are going to use as context. We've designed it with the singleton design pattern[2] so only one instance of the class exist at the same time. In the constructor of the class we create the global keys and then you can access to them through the get methods. Another method is the debugMode, all along the code we've called to this method to put messages of confirmation so we can see if the code is going well. Only if the debug attribute is set (in the constructor) the messages are shown. With the method getPrivateKey() we create a pair of private key (x,y) and we can call it as many times as we want. And the last one, the destroy() method, it delete the global keys as well as  the instance of Session.

Also we have de DSA class. In this class we have the same debugMode as the Session class. Also, we have two main methods, sign and verify. The first parameter of both methods are the debug parameter and the message and the necessary keys to perform the operations.

In order to have the code more organized we have put the auxiliary methods in the DSAUtils class. The majority of the methods in this class are static, so the other classes can use it. With getPrime(Integer i) you can obtain a probable prime of i bits. This method use other methods like primeCandidate(Integer i) that creates a random i bits number with the first and the last bit at 1 (this makes more probable that is a prime, exactly 2 / (i*ln 2)[3]) then we take that probable prime and put it through the primalityTest function that performs the Miller-Rabin primality test to the candidate (the code of the primality test was taken and modified from Algorithm Implementation[4]) .

The last class that we have implemented is the one that draw the whole GUI, EntryPoint.

________________
[1] "Digital Signature Algorithm - Wikipedia, the free encyclopedia." 2004. 21 Nov. 2012 <http://en.wikipedia.org/wiki/Digital_Signature_Algorithm>

[2] "Singleton pattern - Wikipedia, the free encyclopedia." 2004. 22 Nov. 2012 <http://en.wikipedia.org/wiki/Singleton_pattern>

[3] 18 slide of the notes of the subject “Criptografia” at the University of Seville: http://db.tt/ZtVEUVBC

[4] "Algorithm Implementation/Mathematics/Primality Testing - Wikibooks ..." 2010. 22 Nov. 2012 <http://en.wikibooks.org/wiki/Algorithm_Implementation/Mathematics/Primality_Testing>