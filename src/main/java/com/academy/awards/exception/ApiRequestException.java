package com.academy.awards.exception;



public class ApiRequestException extends RuntimeException
{

    public ApiRequestException(String message ){
     super(message);
   }

}
