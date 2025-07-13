package xyz.ibudai.authority.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultData<T> {

    private int code;

    private String message;

    private T data;


    public static <T> ResultData<T> success(T data) {
        ResultData<T> response = new ResultData<>();
        response.setCode(200);
        response.setMessage("success");
        response.setData(data);
        return response;
    }

    public static <T> ResultData<T> reject(String message) {
        ResultData<T> response = new ResultData<>();
        response.setCode(203);
        response.setMessage(message);
        response.setData(null);
        return response;
    }

    public static <T> ResultData<T> denies(String message) {
        ResultData<T> response = new ResultData<>();
        response.setCode(403);
        response.setMessage(message);
        response.setData(null);
        return response;
    }

    public static <T> ResultData<T> failed(String message) {
        ResultData<T> response = new ResultData<>();
        response.setCode(500);
        response.setMessage(message);
        response.setData(null);
        return response;
    }
}
