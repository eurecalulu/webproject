package cn.cbirc.valid;

import cn.cbirc.model.vo.ResponseVO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数校验全局异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVO handlerValidator(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<ObjectError> allErrors = result.getAllErrors();
        return ResponseVO.buildError(allErrors);
    }
}