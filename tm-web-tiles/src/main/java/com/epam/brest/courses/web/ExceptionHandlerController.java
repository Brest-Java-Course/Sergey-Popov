package com.epam.brest.courses.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by beast on 24.11.14. At 12.06
 */
@ControllerAdvice
public class ExceptionHandlerController {

    /**
     *
     */
    //private static final Logger LOGGER = LogManager.getLogger();

    /**
     *
     */
    public static final String DFLT_ERROR_VIEW = "error/error";

    /**
     *
     * @param request request
     * @param exception exception
     * @return mav
     */
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public final ModelAndView defaultErrorHandler(
            final HttpServletRequest request,
            final Exception exception) {

        //LOGGER.error(e.fillInStackTrace());

        ModelAndView mav = new ModelAndView(DFLT_ERROR_VIEW);
        mav.addObject("datetime", new Date());
        mav.addObject("exception", exception);
        mav.addObject("url", request.getRequestURL());

        return mav;

    }

}
