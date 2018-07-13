package com.github.spotbugs.jsr305.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.regex.Pattern;

import com.github.spotbugs.jsr305.annotation.meta.TypeQualifier;
import com.github.spotbugs.jsr305.annotation.meta.TypeQualifierValidator;
import com.github.spotbugs.jsr305.annotation.meta.When;

/**
 * This annotation is used to denote String values that should always match given pattern.
 * <p>
 * When this annotation is applied to a method it applies to the method return value.
 */
@Documented
@TypeQualifier(applicableTo = String.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchesPattern {
    @RegEx
    String value();

    int flags() default 0;

    static class Checker implements TypeQualifierValidator<MatchesPattern> {
        public When forConstantValue(MatchesPattern annotation, Object value) {
            Pattern p = Pattern.compile(annotation.value(), annotation.flags());
            if (p.matcher(((String) value)).matches())
                return When.ALWAYS;
            return When.NEVER;
        }

    }
}
