package com.on99ft.exception;

public class GetIdException extends NumberFormatException{
    public GetIdException(String s) {
        super("检查Id格式");
    }
}
