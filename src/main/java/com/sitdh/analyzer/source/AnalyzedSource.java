package com.sitdh.analyzer.source;

import java.util.List;
import java.util.Optional;

import org.apache.bcel.classfile.JavaClass;

public interface AnalyzedSource {
	
	public Optional<List<JavaClass>> listClasses();

}
