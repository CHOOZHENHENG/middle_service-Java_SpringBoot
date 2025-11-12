package middle.service.demo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import middle.service.demo.Enums.ResultEnum;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    Meta meta;
    T data;
    String message;

    public static <T> Result<T> success(){
        return success(ResultEnum.SUCCESS);
    }

    public static <T> Result<T> success(ResultEnum resultEnum){
        return getStatus(resultEnum);
    }

    private static <T> Result<T> getStatus(ResultEnum resultEnum){
        Result<T> result = new Result<T>();
        /*Meta meta = new Meta();
        meta.setCode(resultEnum.getCode());*/
        return result.setMeta(new Meta().setCode(resultEnum.getCode()));
    }

    public static <T> Result<T> success(T data){
        return success(ResultEnum.SUCCESS, data);
    }

    public static <T> Result<T> success(ResultEnum resultEnum, T data){
        Result<T> result = new Result<T>();
        Meta meta = new Meta();
        if(data instanceof List<?>){
            meta.setRecordCount(((List<?>) data).size());
        }
        return result.setMeta(meta.setCode(resultEnum.getCode())).setData(data);
    }


    public static <T> Result<T> error(){
        return error(ResultEnum.ERROR_500);
    }

    public static <T> Result<T> error(ResultEnum resultEnum){
        return getStatus(resultEnum, resultEnum.getMessage());
    }

    public static <T> Result<T> error(String message){
        return getStatus(ResultEnum.ERROR_500, message);
    }

    public static <T> Result<T> getStatus(ResultEnum resultEnum, String message){
        Result<T> result = new Result<T>();
        return result.setMeta(new Meta().setCode(resultEnum.getCode())).setMessage(message);
    }
}