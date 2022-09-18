package com.on99ft.Controller;

import com.on99ft.exception.GetIdException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLSyntaxErrorException;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NumberFormatException.class)
    public Result GetIdException(NumberFormatException gie){
        System.out.println("gie = " + gie);
        return new Result(Code.NUMBER_ERR,"请检查输入的格式!",null);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result DeleteMethodException(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException){
        System.out.println("httpRequestMethodNotSupportedException = " + httpRequestMethodNotSupportedException);
        return new Result(Code.METHOD_ERR,"方法错误!",null);
    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public Result SQLProblem(SQLSyntaxErrorException sqlSyntaxErrorException){
        System.out.println("sqlSyntaxErrorException = " + sqlSyntaxErrorException);
        return new Result(Code.SQL_YJ_ERR,"SQL语句错误!",null);
    }
}
