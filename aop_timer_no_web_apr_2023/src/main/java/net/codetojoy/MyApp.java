package net.codetojoy;

import net.codetojoy.api.Api; 

public class MyApp {
	public static void main(String[] args) {
        var greeting = new Api().getGreeting();
        System.out.println("TRACER " + greeting);
        System.out.println("Ready.");
	}
}
