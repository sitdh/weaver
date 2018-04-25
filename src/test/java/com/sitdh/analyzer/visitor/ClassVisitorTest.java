package com.sitdh.analyzer.visitor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.apache.bcel.classfile.ClassParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClassVisitorTest {
	
	private ClassVisitor cv;
	
	private String DIRECTORY_LOCATION 	= "";
	private String FILE_LOCATION 		= "";
	
	private List<String> classListing 	= null;

	@Before
	public void setUp() throws Exception {
		ClassLoader cl = getClass().getClassLoader();
		
		String resourceLocation = new File(new File(cl.getResource(".").getFile()).getParent()).getParent();
		
		resourceLocation += "/src/test/resources";
		
		DIRECTORY_LOCATION = resourceLocation;
		FILE_LOCATION = DIRECTORY_LOCATION + "/lib/com/Hello.class";
		
		ClassParser cp = new ClassParser(new FileInputStream(FILE_LOCATION), FILE_LOCATION);
		
		cv = new ClassVisitor(cp.parse(), null);
		
		classListing = Arrays.asList(
				"java.lang.Object",
				"lib.com.Hello",
				"lib.com.sitdh.Greeting");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void visitorShouldReadConstants() {
		
		cv.start(s -> {
			System.out.println(s);
			assertTrue(classListing.contains(s));
		});
	}

}
