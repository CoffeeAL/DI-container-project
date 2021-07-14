package com.loiko.alex;

import com.loiko.alex.bean.BeanFactory;
import com.loiko.alex.context.ApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class Runner {

    private static final Logger logger = Logger.getLogger(Runner.class.getName());
    private static FileHandler fileHandler;
    private static final SimpleFormatter simpleFormatter = new SimpleFormatter();
    private static final String LOG_FILE = "runner-log.txt";

    static {
        try {
            fileHandler = new FileHandler(LOG_FILE);
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        logger.info("The application is running");
        Runner runner = new Runner();
        ApplicationContext context = runner.run();
        try {
            InjectorImpl injector = context.getBean(InjectorImpl.class);
            injector.doSuccessfulInjection();
        } catch (InvocationTargetException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
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