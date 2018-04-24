package com.sitdh.analyzer.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClassFileSourceTest {
	
	private ClassFileSource source;
	
	private String TEMPOLARY_LOCATION = "/tmp";
	private String DIRECTORY_LOCATION = "/Users/sitdh/Documents/workspace/weaver/target";
	private String FILE_LOCATION = DIRECTORY_LOCATION + "/classes/com/sitdh/analyzer/source/AnalyzedSource.class";

	@Before
	public void setUp() throws Exception {
		ClassLoader cl = getClass().getClassLoader();
		
		String resourceLocation = new File(new File(cl.getResource(".").getFile()).getParent()).getParent();
		
		resourceLocation += "/src/test/resources";
		
		TEMPOLARY_LOCATION = FileUtils.getTempDirectoryPath();
		DIRECTORY_LOCATION = resourceLocation;
		FILE_LOCATION = DIRECTORY_LOCATION + "/lib/com/Hello.class";
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void resourceFileShouldBeGet()  {
		assertTrue(DIRECTORY_LOCATION.endsWith("/src/test/resources"));
	}
	
	@Test
	public void fileShouldReadAsFile() {
		File f = new File(FILE_LOCATION);
		assertTrue(f.isFile());
		
		f = new File(DIRECTORY_LOCATION);
		assertTrue(f.isDirectory());
	}

	@Test
	public void sourceShouldBeReadClassFolder() {
		source = new ClassFileSource(DIRECTORY_LOCATION);
		assertEquals(2, source.listClasses().get().size());
	}
	
	@Test
	public void sourceShouldNotReturnForInvalidSingleSource() {
		source = new ClassFileSource(FILE_LOCATION, true);
		assertEquals(1, source.listClasses().get().size());
	}

	@Test
	public void sourceShouldNotReturnForValidSingleSource() {
		source = new ClassFileSource(FILE_LOCATION, false);
		assertTrue(source.listClasses().get().size() > 0);
	}
	
	@Test
	public void unknowSourceShouldNotFoundAnyClasses() {
		source = new ClassFileSource(TEMPOLARY_LOCATION);
		assertTrue(source.listClasses().get().size() == 0);
	}
}
