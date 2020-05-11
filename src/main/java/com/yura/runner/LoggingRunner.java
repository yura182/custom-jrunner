package com.yura.runner;

import com.yura.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class LoggingRunner extends BlockJUnit4ClassRunner {
    private final static Logger LOGGER = LogManager.getLogger(LoggingRunner.class);

    public LoggingRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected Statement methodInvoker(FrameworkMethod method, Object test) {
        if (isAnnotated(method)) {
            logTestMethod(method);
        }

        return super.methodInvoker(method, test);
    }

    private void logTestMethod(FrameworkMethod method) {
        LOGGER.info(method.getDeclaringClass().getSimpleName() + ".class " + method.getName() + "()");
    }

    private boolean isAnnotated(FrameworkMethod method) {
        return method.getAnnotation(Log.class) != null;
    }
}
