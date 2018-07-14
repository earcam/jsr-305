package edu.umd.cs.findbugs.examples;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.github.spotbugs.jsr305.annotation.meta.TypeQualifier;
import com.github.spotbugs.jsr305.annotation.meta.TypeQualifierValidator;
import com.github.spotbugs.jsr305.annotation.meta.When;

@Documented
@TypeQualifier(applicableTo=String.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface FixedLengthString {
	int value();

	class Checker implements TypeQualifierValidator<FixedLengthString> {

		public When forConstantValue(FixedLengthString annotation, Object v) {
			if (!(v instanceof String))
				return When.NEVER;
			String s = (String) v;
			if (s.length() == annotation.value())
				return When.ALWAYS;
			return When.NEVER;
		}
	}
}