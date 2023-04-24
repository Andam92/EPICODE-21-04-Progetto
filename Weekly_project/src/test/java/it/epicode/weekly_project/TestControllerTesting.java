package it.epicode.weekly_project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.epicode.weekly_project.auth.controller.TestController;

public class TestControllerTesting {
	
	TestController toBeTested = new TestController();
	
	@Test
	public void allAccessTest() {
		String result = toBeTested.allAccess();
		assertEquals("Public Content.", result);
	}
	
}

