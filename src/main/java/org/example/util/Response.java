package org.example.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    //    public static final int NO_LOGIN = -1;
    final public static int  SUCCESS = 0;
    final public static int FAIL = 1;
//    public static final int ERROR_ROLE = 2;
//    public static final int NO_PERMISSION = 3;
//    public static final int INVALID_PARAMETER = 4;
//    public static final int SERVER_ERROR = 10;
//    private static final long serialVersionUID = 1L;

    private Integer code;   // 为各种状态进行编码
    private String  msg;    // 详细信息
    private T data;         // 返回的数据

    public T getData() {
        return data;
    }
}
