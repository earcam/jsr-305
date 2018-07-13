import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.github.spotbugs.jsr305.annotation.MatchesPattern;
import com.github.spotbugs.jsr305.annotation.meta.TypeQualifier;
import com.github.spotbugs.jsr305.annotation.meta.TypeQualifierValidator;
import com.github.spotbugs.jsr305.annotation.meta.When;

@Documented
@TypeQualifier
@MatchesPattern("[0-9]{16}")
@Retention(RetentionPolicy.RUNTIME)
public @interface CreditCardNumber {
	class Checker implements TypeQualifierValidator<CreditCardNumber> {

		public When forConstantValue(CreditCardNumber annotation, Object v) {
			if (!(v instanceof String))
				return When.NEVER;
			String s = (String) v;
			if (LuhnVerification.checkNumber(s))
				return When.ALWAYS;
			return When.NEVER;
		}
	}
}
