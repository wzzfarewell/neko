package com.wzz.neko.config;

import com.wzz.neko.core.Result;
import com.wzz.neko.core.ResultCode;
import com.wzz.neko.exceptions.AuthException;
import com.wzz.neko.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;

/**
 * GlobalExceptionHandler
 *
 * @author wzzfarewell
 * @date 2020/1/9
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }
    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
    }

    /**
     * 全局异常捕获处理
     * @param e 异常
     * @return 自定义json格式返回
     */
    @ExceptionHandler(Exception.class)
    public Result apiExceptionHandler(Exception e) {
//        log.warn("ApiException 异常抛出：{}", e.toString());
        Result result = new Result();
        if(e instanceof AuthException){
            result.setCode(ResultCode.UNAUTHORIZED).setMessage(e.getMessage());
        }else if(e instanceof ServiceException){
            result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
        }else if( e instanceof NoHandlerFoundException){
            result.setCode(ResultCode.NOT_FOUND).setMessage(e.getMessage());
        }else if (e instanceof ServletException){
            result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
        }else if(e instanceof DuplicateKeyException){
            result.setCode(ResultCode.FAIL).setMessage("重复操作");
        }else{
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
        }
        return result;
    }
}
