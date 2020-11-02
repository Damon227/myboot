package com.example.myboot2.pojo.validator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 检验参数是否是合法的职位
 *
 * @author damon
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {Job.JobValidator.class})
public @interface Job {
    // 默认错误消息
    String message() default "职位错误";

    // 分组
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};

    String[] value();

    final class JobValidator implements ConstraintValidator<Job, String> {

        private static String[] values;

        @Override
        public void initialize(Job constraintAnnotation) {
            values = constraintAnnotation.value();
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
            if(values.length == 0){
                return true;
            }

            return Arrays.asList(values).contains(value);
        }
    }
}
