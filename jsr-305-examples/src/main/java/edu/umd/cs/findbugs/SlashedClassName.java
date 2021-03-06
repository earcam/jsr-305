package edu.umd.cs.findbugs;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.github.spotbugs.jsr305.annotation.meta.TypeQualifier;
import com.github.spotbugs.jsr305.annotation.meta.When;

@Documented
@TypeQualifier(applicableTo=CharSequence.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface SlashedClassName {
	When when() default When.ALWAYS;
}
