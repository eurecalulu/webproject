package cn.cbirc.valid;

import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.regex.Pattern;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER}) //定义可以在字段上使用
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StatusChecker.StatusCheck.class) //指定校验规则的类
public @interface StatusChecker {

    String message() default "政策解读状态可能为PUBLIC、DRAFT、ABOLISH";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 校验规则
     */
    @Component
    class StatusCheck implements ConstraintValidator<StatusChecker, String> {


        @Override
        public void initialize(StatusChecker statusChecker) {

        }

        @Override
        public boolean isValid(String status, ConstraintValidatorContext constraintValidatorContext) {
            // 空
            if("PUBLIC".equals(status)||"DRAFT".equals(status)||"ABOLISH".equals(status)){
                return true;
            }
            return false;
        }
    }
}