package de.hsba.bi.StuOrgPortal.Validation;

import com.google.common.base.Joiner;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(final ValidPassword arg0) {

    }

    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {
        final PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(6, 30),
                new IllegalSequenceRule(GermanSequenceData.Alphabetical, 5, false), // Prüfung ob PW Sequenz von aufeinanderfolgenden Chars enthält
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false ),
                new WhitespaceRule()));
                // eventuell um weitere Regeln erweitern, Doku: https://www.passay.org/javadocs/

        final RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(Joiner.on(",").join(validator.getMessages(result))).addConstraintViolation();
        return false;
    }

}