package net.codetojoy.api;

import net.codetojoy.logging.LogExecutionTime;

public class Api {
	@LogExecutionTime
	public String getGreeting() {
        // pathological sleep
        try { Thread.sleep(3000); } catch (Exception ex) {} 

		return "hello / bonjour!";
	}
}
