package com.shengruitn.utils;
/*
 *
 *   统一返回
 *   @author tangah
 *   @create 2018-06-26 22:37
 */

import com.shengruitn.VO.ProductVO;
import com.shengruitn.VO.ResultVO;

import java.util.List;

public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success() {
        return success(null );
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(null);
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
