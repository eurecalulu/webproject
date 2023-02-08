package valid;

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
@Constraint(validatedBy = TimeChecker.TimeCheck.class) //指定校验规则的类
public @interface TimeChecker {

    String message() default "日期格式为yyy-MM-dd或空串";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 校验规则
     */
    @Component
    class TimeCheck implements ConstraintValidator<TimeChecker, String> {


        @Override
        public void initialize(TimeChecker time) {

        }

        @Override
        public boolean isValid(String time, ConstraintValidatorContext constraintValidatorContext) {
            // 空
            if(time==null||"".equals(time)){
                return true;
            }
            Pattern pattern = Pattern.compile("^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$");
            if (pattern.matcher(time).matches()) {
                return true;
            }
            return false;
        }
    }
}