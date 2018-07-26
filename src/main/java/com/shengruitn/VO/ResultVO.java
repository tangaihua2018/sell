package com.shengruitn.VO;

/*
 *   HTTP请求返回的最外层对象
 *   @author tangah
 *   @create 2018-06-26 15:58
 */

import lombok.Data;

import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO <T>{

    /* 错误码 */
    private Integer code;

    /* 错误信息 */
    private String msg;

    /* 返回数据 */
    private T data;
}
