package lt.vu.interceptors;

import lt.vu.exceptions.DateException;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@CheckedForDateException
public class DateCheckHandler implements Serializable {
    @AroundInvoke
    public Object handleDateExceptions(InvocationContext context) throws Exception {
        try {
            return context.proceed();
        } catch (DateException e) {
            return "errorPage";
        }
    }
}
