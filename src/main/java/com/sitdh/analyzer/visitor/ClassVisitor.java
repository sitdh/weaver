package com.sitdh.analyzer.visitor;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.EmptyVisitor;
import org.apache.bcel.classfile.JavaClass;

import com.sitdh.analyzer.observer.SourceCodeObserver;

public class ClassVisitor extends EmptyVisitor {

	private JavaClass jc;
	
	private Optional<SourceCodeObserver> observer;
	
	private Consumer<String> consume;
	
	public ClassVisitor(JavaClass jc, Optional<SourceCodeObserver> observer) {
		this.jc = jc;
		this.observer = observer;
	}
	
	public void visitConstantPool(ConstantPool cp) {
		Constant[] constants = cp.getConstantPool();
		for(Constant c : constants) {
			if (c != null && 7 == c.getTag()) {
				this.consume.accept(cp.constantToString(c));
			}
		}
	}
	
	public void start(Consumer<String> consume) {
		this.consume = consume;
		jc.getConstantPool().accept(this);
	}
}
