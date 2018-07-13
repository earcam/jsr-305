package edu.umd.cs.findbugs.examples;

import java.lang.annotation.ElementType;

import com.github.spotbugs.jsr305.annotation.meta.TypeQualifierDefault;

import edu.umd.cs.findbugs.SlashedClassName;

@SlashedClassName
@TypeQualifierDefault(ElementType.PARAMETER)
public @interface ParametersAreSlashedByDefault {

}
