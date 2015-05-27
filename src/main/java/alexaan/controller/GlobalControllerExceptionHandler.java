package alexaan.controller;

import alexaan.exception.IllegalArgumentsException;
import alexaan.exception.ResourceNotFoundException;
import alexaan.resourcesupport.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
class GlobalControllerExceptionHandler {
    @ExceptionHandler(IllegalArgumentsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public HttpEntity handleIllegalArgumentsException(IllegalArgumentsException ex){
        ErrorResourceSupport error = new ErrorResourceSupport(ex.getClass()+ ": "+ex.getThrowMessage());
        //error.add(linkTo(methodOn(GlobalControllerExceptionHandler.class).handleIllegalArgumentsException(ex)).withSelfRel());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public HttpEntity handleResourceNotFoundException(ResourceNotFoundException ex)
    {
        ErrorResourceSupport error = new ErrorResourceSupport(ex.getClass()+ ": "+ex.getThrowMessage());
        //error.add(linkTo(methodOn(GlobalControllerExceptionHandler.class).handleResourceNotFoundException(ex)).withSelfRel());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}