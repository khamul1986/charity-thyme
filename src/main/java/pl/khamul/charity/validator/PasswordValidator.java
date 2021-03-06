package pl.khamul.charity.validator;

import pl.khamul.charity.user.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator< ValidPassword, Object> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserDto user = (UserDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }



}
