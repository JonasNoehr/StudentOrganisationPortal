package de.hsba.bi.StuOrgPortal.Validation;

import de.hsba.bi.StuOrgPortal.web.user.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final UserDto userDto = (UserDto) obj;
        return userDto.getPassword().equals(userDto.getMatchingPassword());
    }

}