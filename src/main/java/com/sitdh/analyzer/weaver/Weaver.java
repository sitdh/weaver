package com.sitdh.analyzer.weaver;

import java.util.List;
import java.util.Optional;

import org.apache.bcel.classfile.JavaClass;

import com.sitdh.analyzer.observer.SourceCodeObserver;
import com.sitdh.analyzer.source.AnalyzedSource;

import lombok.Getter;
import lombok.Setter;

public class Weaver {
	
	@Getter @Setter
	private AnalyzedSource source;
	
	private Optional<List<JavaClass>> sourceFiles;
	
	public void attachAnalyzedSource(AnalyzedSource source) {
		this.sourceFiles = source.listClasses();
	}
	
	public void parse(Optional<List<SourceCodeObserver>> observers) {
		this.sourceFiles.ifPresent(javaClasses -> {
			javaClasses.forEach(jc -> {
				
			});
		});
		
	}

}
