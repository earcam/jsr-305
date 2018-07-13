package edu.umd.cs.findbugs.examples;

import com.github.spotbugs.jsr305.annotation.meta.When;

import edu.umd.cs.findbugs.DottedClassName;
import edu.umd.cs.findbugs.SlashedClassName;

@ParametersAreSlashedByDefault
public class TestDefaults {
	public void foo(String c) {}
	
	public void foo2(@DottedClassName String c) {
		foo(c);
	}

	public void foo3(@SlashedClassName(when=When.UNKNOWN) String c) {
		foo(c);
	}
}
