package com.example.spring_boot_crud.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    private String message;
    private int status;

    //Success
    public static <T> ApiResponse<T> success(T data, int status) {
        return new ApiResponse<>(data, "Success", status); // 200 OK
    }

    //Fail
    public static <T> ApiResponse<T> error(String message, int status) {
        return new ApiResponse<>(null, message, status);
    }
}
