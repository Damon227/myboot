//package com.example.myboot2.pojo.validator;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * @author damon
// * @date 2020/08/10
// */
//public final class JobValidator implements ConstraintValidator<Job, String> {
//
//    private static HashMap<String, Boolean> jobs;
//
//    static {
//        jobs = new HashMap<>();
//        jobs.put("学生", true);
//        jobs.put("教师", true);
//        jobs.put("工人", true);
//        jobs.put("销售", true);
//    }
//
//    private static String[] values;
//
//    @Override
//    public void initialize(Job constraintAnnotation) {
//        values = constraintAnnotation.value();
//    }
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
////        if(value != null){
////            return jobs.containsKey(value);
////        }
//
//
//        return Arrays.asList(values).contains(value);
//
//        //return false;
//    }
//}
