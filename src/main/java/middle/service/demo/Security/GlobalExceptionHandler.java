package middle.service.demo.Security;

import middle.service.demo.Enums.ResultEnum;
import middle.service.demo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

@RestControllerAdvice//combine @ControllerAdvice and @ResponseBody(method return value as json/xml)
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({IOException.class, RuntimeException.class})
    public Result<?> exception500(Exception ex) {
        return Result.error(ResultEnum.ERROR_500);
    }

    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<?> exception501(Exception e){
        return Result.error(ResultEnum.ERROR_501);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public Result<?> exception401(Exception e){
        return Result.error(ResultEnum.ERROR_401);
    }
}
