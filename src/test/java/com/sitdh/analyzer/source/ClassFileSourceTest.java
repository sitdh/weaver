package com.sitdh.analyzer.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClassFileSourceTest {
	
	private ClassFileSource source;
	
	private final String TEMPOLARY_LOCATION = "/tmp";
	private final String DIRECTORY_LOCATION = "/Users/sitdh/Documents/workspace/weaver/target";
	private final String FILE_LOCATION = DIRECTORY_LOCATION + "/classes/com/sitdh/analyzer/source/AnalyzedSource.class";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
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
		assertTrue(0 < source.listClasses().get().size());
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
