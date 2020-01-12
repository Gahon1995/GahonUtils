/**
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gahon.utils.result;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * REST API 返回结果
 * </p>
 *
 * @author geekidea
 * @since 2018-11-08
 */

public class ApiResult<T> {

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 执行时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    public ApiResult() {

    }

    public static <T> ApiResult<T> result(boolean flag) {
        if (flag) {
            return ok();
        }
        return fail("");
    }

    public static <T> ApiResult<T> result(ApiCode apiCode) {
        return result(apiCode, null);
    }

    public static <T> ApiResult<T> result(ApiCode apiCode, T data) {
        return result(apiCode, null, data);
    }

    public static <T> ApiResult<T> result(ApiCode apiCode, String msg, T data) {
        String message = apiCode.getMsg();
        if (msg != null && !"".equals(msg)) {
            message = msg;
        }
        return result(apiCode.getCode(), message, data);
    }

    public static <T> ApiResult<T> result(int code, String msg, T data) {
        final ApiResult<T> apiResult = new ApiResult<T>();
        apiResult.setCode(code);
        apiResult.setMsg(msg);
        apiResult.setData(data);
        apiResult.setTime(new Date());
        return apiResult;
    }

    public static <T> ApiResult<T> ok() {
        return ok(null);
    }

    public static <T> ApiResult<T> ok(T data) {
        return result(ApiCode.SUCCESS, data);
    }

    public static <T> ApiResult<Map<String, T>> ok(String key, T value) {
        Map<String, T> map = new HashMap<>(1);
        map.put(key, value);
        return ok(map);
    }

    public static <T> ApiResult<T> fail(ApiCode apiCode) {
        return result(apiCode, null);
    }

    public static <T> ApiResult<T> fail(String msg) {
        return result(ApiCode.FAIL, msg, null);

    }

    public static <T> ApiResult<T> fail(Integer code, String msg, T data) {
        final ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(code);
        apiResult.setMsg(msg);
        apiResult.setData(data);
        apiResult.setTime(new Date());
        return apiResult;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}