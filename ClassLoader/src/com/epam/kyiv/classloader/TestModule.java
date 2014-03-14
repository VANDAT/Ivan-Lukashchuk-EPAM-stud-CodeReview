package com.epam.kyiv.classloader;

public class TestModule {
	static int count;

	@Override
	public String toString() {
		return "TestModule, version 1! " + count++;
	}
}
