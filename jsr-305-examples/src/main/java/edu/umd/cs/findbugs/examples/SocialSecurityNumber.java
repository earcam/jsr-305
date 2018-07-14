package edu.umd.cs.findbugs.examples;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.github.spotbugs.jsr305.annotation.MatchesPattern;
import com.github.spotbugs.jsr305.annotation.meta.TypeQualifierNickname;

@Documented
@TypeQualifierNickname
@MatchesPattern("[0-9]{3}-[0-9]{2}-[0-9]{4}")
@Retention(RetentionPolicy.RUNTIME)
public @interface SocialSecurityNumber {
}
