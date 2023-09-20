package sa.account.management.controller.advice;

import sa.account.management.constant.Exceptions;
import sa.account.management.exception.ApiException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandlingAdvice {

    public static final String ERROR = "error";

    @ExceptionHandler(value = ApiException.class)
    public String apiException(Model model, ApiException e) {
        model.addAttribute(ERROR, e.getMessage());
        return ERROR;
    }

    @ExceptionHandler(value = Exception.class)
    public String exception(Model model) {
        model.addAttribute(ERROR, Exceptions.GENERAL_EXCEPTION.get());
        return ERROR;
    }

}
