package com.tahoecn.xkc.aspect;

import com.tahoecn.core.map.MapUtil;
import com.tahoecn.core.util.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Aspect
@Component
public class GenericAop {
//    @Pointcut("execution(* com.tahoecn.xkc.service.*.*(..))")
//    public void executeService(){
//
//    }

//    /**
//     * 前置通知，方法调用前被调用
//     * @param joinPoint
//     */
//    @Before("executeService()")
//    public void doBeforeAdvice(JoinPoint joinPoint){
//        System.out.println("（1）前置通知：");
//
//        Object[] objects = joinPoint.getArgs();
//        Signature signature = joinPoint.getSignature();
//
//        System.out.println(signature.toLongString());
//        if(objects.length != 0) {
//            for(Object obj : objects){
//                System.out.println("input:"+obj);
//            }
//        }
//    }


//    /**
//     * 后置返回通知
//     * 这里需要注意的是:
//     *      如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
//     *      如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
//     * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
//     * @param joinPoint
//     * @param result
//     */
//    @AfterReturning(value = "execution(* com.tahoecn.xkc.service..*.*(..))",returning = "result")
//    public void doAfterReturningAdvice(JoinPoint joinPoint,Object result){
//
//        Map<String,Object> map = new LinkedHashMap();
//
//        map.put("function_name",joinPoint.toShortString());
//        map.put("function_input",joinPoint.getArgs());
//        map.put("function_output",result);
//
////        map.put("function_system_info",result);
//
//        map.putAll(com.tahoecn.core.runtime.RuntimeInfo.cpu());
//        map.putAll(com.tahoecn.core.runtime.RuntimeInfo.memory());
//
//        map.put("function_elapsed_time","122ms");
//
//
//        System.out.println(JsonUtil.convertObjectToJson(map));
//    }

//    /**
//     * 后置异常通知
//     *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
//     *  throwing 限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
//     *      对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
//     * @param joinPoint
//     * @param exception
//     */
//    @AfterThrowing(value = "executeService()",throwing = "exception")
//    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
//        System.out.println("（2）后置异常通知");
//        System.out.println(joinPoint.toString());
//        System.out.println(exception.toString());
//    }
//
//    /**
//     * 后置最终通知（目标方法只要执行完了就会执行后置通知方法）
//     * @param joinPoint
//     */
//    @After("execution(* com.tahoecn.xkc.service..*.*(..))")
//    public void doAfterAdvice(JoinPoint joinPoint){
//        Object[] objects = joinPoint.getArgs();
//        if(objects!=null) {
//            if (objects.length != 0) {
//                for (Object object : objects) {
//                    System.out.println("input:" + object);
//                }
//            }
//        }
//
//        System.out.println("output:"+joinPoint.);
//
//
//    }


    /**
     * 环绕通知：
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型

    @Around("execution(* com.tahoecn.xkc.service..*.*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object obj = null;
        Throwable throwable = null;
        try{
            Signature signature = proceedingJoinPoint.getSignature();

            long startTime = System.currentTimeMillis();
            try {
                obj = proceedingJoinPoint.proceed();
            } catch (Throwable t) {
                t.printStackTrace();
                throwable = t;
            }
            long endTime = System.currentTimeMillis();
            long diffTime = endTime - startTime;

            //application log info
            Map<String,Object> map = new LinkedHashMap();
            map.put("function_name",signature.toString());
            map.put("function_input",proceedingJoinPoint.getArgs());
            map.put("function_output",obj);
            map.put("function_elapsed_time",diffTime+"ms");

            //system log info
            Map<String,Object> systemInfoMap = new LinkedHashMap();
            systemInfoMap.putAll(com.tahoecn.core.runtime.RuntimeInfo.cpu());
            systemInfoMap.putAll(com.tahoecn.core.runtime.RuntimeInfo.memory());
            map.put("function_system_info",systemInfoMap);
        }catch (Exception exc){
            throw exc;
        }

        if (throwable != null){
            throw throwable;
        }

        return obj;
    }*/
}
