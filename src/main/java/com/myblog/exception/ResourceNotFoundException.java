package com.myblog.exception;
//new ResourceNotFoundException ("post","id",1)---->Whenever the exception will happen we will sypply post id vlaue in the object
//creation of ResourceNotFoundException.and this will supply to parent class constructor of *RuntimeException*.


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Getter
@Setter
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;//Post
    private String fieldName;//Id
    private long fieldValue;//1,2,3,,4...etc

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : '%s'",resourceName,fieldName,fieldValue));//Post Not Found With Id 1



    }
}
