package com.sitdh.analyzer.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.commons.io.FileUtils;

public class ClassFileSource implements AnalyzedSource {
	
	private HashMap<String, File> locations = new HashMap<String, File>();
	
	private String[] exts = new String[]{"class"};
	
	public ClassFileSource(String location) {
		this(location, false);
	}
	
	public ClassFileSource(String location, boolean isSingleFile) {
		
		File fileLocation = new File(location);
		
		if (isSingleFile) {
			if (fileLocation.isFile()) {
				locations.put(fileLocation.getAbsolutePath(), fileLocation);
			}
		} else {
			
			File redFile = fileLocation.isDirectory() ? fileLocation : new File(fileLocation.getParent()) ;
			
			FileUtils.iterateFiles(redFile, exts, true).forEachRemaining(f -> {
				locations.put(f.getAbsolutePath(), f);
			});
			
		}
		
	}

	public Optional<List<JavaClass>> listClasses() {
		
		List<JavaClass> listedClass = new ArrayList<JavaClass>();
		
		this.locations.forEach((String filename, File file) -> {
			ClassParser parser;
			
			try {
				parser = new ClassParser(new FileInputStream(file), filename);
				listedClass.add(parser.parse());
			} catch (ClassFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		return Optional.of(listedClass);
	}

}
