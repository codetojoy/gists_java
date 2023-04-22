package net.codetojoy.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.codetojoy.logging.LogExecutionTime;

@RestController
@RequestMapping("/v1")
public class Api {
	
	@GetMapping("/greeting")
	@LogExecutionTime
	public ResponseEntity<String> getGreeting(){
        // pathological sleep
        try { Thread.sleep(3000); } catch (Exception ex) {} 

		return ResponseEntity.ok("TRACER hello / bonjour!\n\n");
	}
	
}
