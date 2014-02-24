package net.navinet.logging.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Andrew on 22/02/14.
 */
public class TimingAspect
{
    private final Logger _logger = Logger.getLogger(TimingAspect.class.getSimpleName());

    public Object messageProfiling(ProceedingJoinPoint joinPoint) throws Throwable
    {
        StopWatch timer = new StopWatch();

        timer.start();
        Object retVal = joinPoint.proceed();
        timer.stop();

        _logger.logp(Level.INFO, joinPoint.getTarget().getClass().getName(), "onMessage" ,String.format("Message Processing: [%d] milliseconds", timer.getTotalTimeMillis()));

        return retVal;
    }
}
