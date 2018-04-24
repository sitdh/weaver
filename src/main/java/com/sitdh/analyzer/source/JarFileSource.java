package com.sitdh.analyzer.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;

public class JarFileSource implements AnalyzedSource {
	
	private JarFile jar;
	
	public JarFileSource(String fileLocation) throws IOException {
		this.jar = new JarFile(fileLocation);
	}
	
	public JarFileSource(JarFile jar) {
		this.jar = jar;
	}

	public Optional<List<JavaClass>> listClasses() {
		List<JavaClass> classListing = new ArrayList<JavaClass>();
		
		Enumeration<JarEntry> jarEntries = this.jar.entries();
		
		while(jarEntries.hasMoreElements()) {
			JarEntry entry = jarEntries.nextElement();
			
			if(entry.isDirectory() || !entry.getName().endsWith(".class")) continue;
			
			ClassParser parsser = new ClassParser(jar.getName(), entry.getName()); 
			
			try {
				classListing.add(parsser.parse());
			} catch (ClassFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return Optional.of(classListing);
	}

}
