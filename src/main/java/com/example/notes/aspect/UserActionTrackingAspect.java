package com.example.notes.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

@Aspect
@Component
public class UserActionTrackingAspect {
    private final Map<String, String> mapAction = Map.of(
            "getAllNotes", "Запрос всех записей",
            "addNote", "Добавление записи",
            "getNoteById", "Получение записи по ID",
            "updateNote","Обновление записи по ID",
            "deleteNote","Удаление записи по ID"
    );

    @Around("@annotation(TrackUserAction)")
    public Object trackUserAction(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String methodName = mapAction.get(joinPoint.getSignature().getName());
        String arguments = Arrays.toString(args);
        System.out.print(methodName+", аргументы: " + arguments);
        return joinPoint.proceed();
    }
}