package com.tyss.optimize.config.aspect;

import com.tyss.optimize.config.KafkaRequestAttribute;
import com.tyss.optimize.config.SpringBeans;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;


@Aspect
@Component
@Slf4j
public class KafkaListenerAnnotationProcessor {

    @Before("@annotation(FireflinkKafkaListener)")
    public void setContext(final JoinPoint joinPoint) {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final Object[] args = joinPoint.getArgs();
        final Method method = methodSignature.getMethod();
        final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        RequestContextHolder.setRequestAttributes(new KafkaRequestAttribute());
        setRequestHeaders(args);
        log.info("Setting request header in the context={}", SpringBeans.getReqHeaderBean());
    }
    @After("@annotation(FireflinkKafkaListener)")
    public void resetContext(final JoinPoint joinPoint) {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final Object[] args = joinPoint.getArgs();
        final Method method = methodSignature.getMethod();
        final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        log.info("Resetting project id in the context={}", SpringBeans.getReqHeaderBean().getProjectId());
        RequestContextHolder.resetRequestAttributes();
    }

    private void setRequestHeaders(Object[] args) {

        ConsumerRecord<String, Object> record = args.length > 0? (ConsumerRecord<String, Object>) args[0] : null;

        if (record.headers() == null) return;

        Header[] kafkaHeaders = record.headers().toArray();

        for (Header header: kafkaHeaders) {
            if (Objects.nonNull(RequestHeaderEnum.labelOf(header.key()))) {
                String key = header.key();
                String value = new String(header.value());
                log.info("header key: {} header value: {}", key, value);
                RequestHeaderEnum.labelOf(key).setRequestHeader(value);
            }
        }
    }
}

