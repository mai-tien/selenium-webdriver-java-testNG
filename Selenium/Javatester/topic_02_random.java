package Javatester;

import java.util.Random;

public class topic_02_random {

	public static void main(String[] args) {
		Random rand = new Random();
		System.out.println(rand.nextFloat());
		System.out.println(rand.nextDouble());
		System.out.println(rand.nextInt());
		System.out.println(rand.nextInt(9999));
		System.out.println("maitien" + rand.nextInt(9999)+ "@gmail.com");
		System.out.println(rand.nextInt(999));
		System.out.println(rand.nextLong());


	}

}
