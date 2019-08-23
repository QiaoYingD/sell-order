package com.imooc.sellorder.utils;


import com.imooc.sellorder.vo.ResultVO;

public class ResultUtils {

    public static ResultVO success(Object obj) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(200);
        resultVO.setMsg("成功");
        resultVO.setData(obj);
        return resultVO;
    }



}
