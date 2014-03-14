package com.epam.kyiv.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyClassLoader extends ClassLoader {

	private Map<String, Class<?>> classesHash = new HashMap<String, Class<?>>();

	public final String[] classPath;

	public MyClassLoader(String[] classPath) {
		this.classPath = classPath;
	}

	protected synchronized Class<?> loadClass(String name, boolean resolve)
			throws ClassNotFoundException {
		Class<?> result = findClass(name);
		if (resolve) {
			resolveClass(result);
		}
		return result;
	}

	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> result = (Class<?>) classesHash.get(name);
		if (result != null) {
			return result;
		}
		File f = findFile(name.replace('.', '/'), ".class");
		if (f == null) {
			return findSystemClass(name);
		}
		try {
			byte[] classBytes = loadFileAsBytes(f);
			result = defineClass(name, classBytes, 0, classBytes.length);
		} catch (IOException e) {
			throw new ClassNotFoundException("Cannot load class " + name + ": "
					+ e);
		} catch (ClassFormatError e) {
			throw new ClassNotFoundException(
					"Format of class file incorrect for class " + name + ": "
							+ e);
		}
		classesHash.put(name, result);
		return result;
	}

	private File findFile(String name, String extension) {
		for (int k = 0; k < classPath.length; k++) {
			File file = new File((new File(classPath[k])).getPath()
					+ File.separatorChar
					+ name.replace('/', File.separatorChar) + extension);
			if (file.exists()) {
				return file;
			}
		}
		return null;
	}

	public static byte[] loadFileAsBytes(File file) throws IOException {
		byte[] result = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		try {
			fis.read(result, 0, result.length);
		} finally {
				fis.close();
		}
		return result;
	}
}
