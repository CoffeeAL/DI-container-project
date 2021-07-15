package com.loiko.alex;

import com.loiko.alex.bean.BeanFactory;
import com.loiko.alex.context.ApplicationContext;
import com.loiko.alex.logger.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class Runner {

    private static Logger logger = LoggerFactory.getLogger();

    public static void main(String[] args) throws ClassNotFoundException {
        logger.info("The application is running");
        Runner runner = new Runner();
        ApplicationContext context = runner.run();
        try {
            InjectorImpl injector = context.getBean(InjectorImpl.class);
            injector.doSuccessfulInjection();
        } catch (InvocationTargetException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
            logger.log(Level.SEVERE, "Injection has been failed");
            throw new RuntimeException(e.getMessage());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e.getMessage());
        }
        logger.info("The application is finishing");
    }

    public ApplicationContext run() {
        logger.info("The application context is running");
        ApplicationContext context = new ApplicationContext();
        BeanFactory factory = new BeanFactory(context);
        context.setFactory(factory);
        return context;
    }
}