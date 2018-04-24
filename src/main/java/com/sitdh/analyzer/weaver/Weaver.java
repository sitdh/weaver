package com.sitdh.analyzer.weaver;

import com.sitdh.analyzer.source.AnalyzedSource;

import lombok.Getter;
import lombok.Setter;

public class Weaver {
	
	@Getter @Setter
	private AnalyzedSource source;
	
	public void attachAnalyzedSource(AnalyzedSource source) {
		this.source = source;
	}

}
