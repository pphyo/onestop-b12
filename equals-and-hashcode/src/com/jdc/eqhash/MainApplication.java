package com.jdc.eqhash;

public class MainApplication {

	public static void main(String[] args) {
		
		League l1 = new League(1, "Premier League", "England");
		League l2 = new League(3, "Premier League", "England");
		
		System.out.println(l1 == l2);
		System.out.println(l1.equals(l2));
		
//		String s1 = "Hello";
//		String s2 = "Hello";
//		String s3 = new String("Hello");
//		
//		System.out.println(s1 == s2);
//		System.out.println(s2.equals(s3));

	}

}
