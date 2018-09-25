package com.pig.learn.mybatis.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAop {

   private static final Logger ControllerLog = LoggerFactory.getLogger("controllerLog");

              private static  ThreadLocal<Long> time = new ThreadLocal<Long>();
     /**
       * 在所有标注@Log的地方切入
        * 在切入的方法之前调用这个方法
       * @param joinPoint
        */
               @Before("@annotation(com.pig.learn.mybatis.annotation.Log)")
       public void beforeExec(JoinPoint joinPoint) {
               time.set(System.currentTimeMillis());
                   System.out.println(System.currentTimeMillis()+"-----");
            }

              /**
        * 在切入的方法之后执行这个方法
         * @param joinPoint
        */
              @After("@annotation(com.pig.learn.mybatis.annotation.Log))")
       public void afterExec(JoinPoint joinPoint) {
                 Signature signature = joinPoint.getSignature();
                MethodSignature ms = (MethodSignature) signature;
                 Method method = ms.getMethod();
//                ControllerLog.info("{} method {},cost "+(System.currentTimeMillis() - time.get())+" ms",ms.getDeclaringTypeName(),method.getName());
//
                  System.out.println(ms.getDeclaringTypeName()+"----"+method.getName());
}

              /**
   32.      * 这个环绕方法个人觉得特别不好用，要把处理的结果，在这个方法里也处理才行
   33.      * @param jp
   34.      * @return
   35.      * @throws Throwable
   36.      */
              //我把这个注释掉，是可以使用的，但是要非常注意才行
              ///@Around("@annotation(com.pig.learn.mybatis.annotation.Log))")
               public Object aroundExec(ProceedingJoinPoint jp) throws Throwable {
                 Object obj = null;
                try {
                       //在被切入的方法之前执行 ,自定义前置通知Before
                        long startTime = getStartTime();

                         //执行那个被切入的防范
                         obj = jp.proceed();//将控制权交给被通知的方法，也就是执行sayHello方法

                         //在被切入的方法之后执行,自定义后置通知After
                         getEndTime(jp,startTime);

                     } catch (Throwable throwable) {
                        System.out.println("异常处理~");
                        throwable.printStackTrace();
                     }
                return  obj;
             }

               /**
   59.      *在调用真正的方法之前，执行这个方法获取开始的时间戳
   60.      * @return
   61.      */
              private long getStartTime(){
                 ControllerLog.info("在调用真正的方法之前，执行这个方法获取开始的时间戳");
                 return System.currentTimeMillis();
             }

               /**
   68.      *在调用真正的方法之后，执行这个方法打印出执行时间信息
   69.      * @return
   70.      */
              private void getEndTime(ProceedingJoinPoint jp,long startTime){
                 long endTime = System.currentTimeMillis();

                 Signature signature = jp.getSignature();
                 MethodSignature ms = (MethodSignature) signature;

               Method method = ms.getMethod();

                 ControllerLog.info("{} method {},cost "+(endTime - startTime)+" ms",ms.getDeclaringTypeName(),method.getName());

                 ControllerLog.info("在调用真正的方法之后，执行这个方法算出消耗时间");
             }

}
