package com.epam.kyiv.classloader;

public class Runner {
	public static void main(String[] args) {
		while (true) {
			ClassLoader myClassLoader = new MyClassLoader(new String[] { "." });
			try {
				Class<?> clazz = Class.forName(
						"com.epam.stud.classloader.TestModule", true,
						myClassLoader);				
				Object t = clazz.newInstance();
				System.out.println(t);
				Thread.sleep(3000);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
