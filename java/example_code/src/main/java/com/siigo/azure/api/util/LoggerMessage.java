 /*
 * LoggerMessage.java
 *
 * Proyecto: Integrador de Servicios
 * Cliente: SIIGO
 * Copyright 2015 by Mobiltech SAS 
 * All rights reserved
 */
package com.siigo.azure.api.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sys. E. Diego Armando Hernandez
 *
 */
public class LoggerMessage {

    private static LoggerMessage instancia = null;

    /**
     *
     * @return
     */
    public static synchronized LoggerMessage getInstancia() {
        if (instancia == null) {
            instancia = new LoggerMessage();
        }
        return instancia;
    }

    /**
     *
     * @param level
     * @param mensaje
     */
    public void imprimirMensaje(String level, String mensaje) {

        Logger logger = Logger.getLogger("co.mobiltech.siigo");
        Level myLevel = Level.parse(level);
        logger.log(myLevel, mensaje);

    }

    /**
     *
     * @param level
     * @param mensaje
     */
    public void imprimirMensaje(Level level, String mensaje) {

        Logger logger = Logger.getLogger("co.mobiltech.siigo");
        logger.log(level, mensaje);

    }

    /**
     *
     * @param level
     * @param stackTrace
     * @param mensaje
     */
    public void imprimirMensaje(Level level, StackTraceElement stackTrace, String mensaje) {

        Logger logger = Logger.getLogger(stackTrace.getClassName());

        String claseMetodo = stackTrace.getMethodName();

        mensaje = claseMetodo + "|" + mensaje;

        logger.log(level, mensaje);

    }

    /**
     *
     * @param message
     */
    public void loggerMessageDebug(String message) {

        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[2];
        Logger.getLogger(stackTrace.getClassName()).log(Level.INFO, "{0}|{1}\n", new Object[]{stackTrace.getMethodName(), message});

    }

    /**
     *
     * @param message
     * @param uuidOperation
     */
    public void loggerMessageDebug(String uuidOperation, String message) {

        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[2];
        Logger.getLogger(stackTrace.getClassName()).log(Level.INFO, uuidOperation + "|{0}|{1}\n", new Object[]{stackTrace.getMethodName(), message});

    }

    /**
     *
     * @param message
     */
    public void loggerMessageError(String message) {

        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[2];
        Logger.getLogger(stackTrace.getClassName()).log(Level.SEVERE, "{0}|{1}\n", new Object[]{stackTrace.getMethodName(), message});

    }

    /**
     *
     * @param e
     */
    public void loggerMessageException(Exception e) {

        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[2];

        String mensajeObtenido = "";
        StackTraceElement[] elements = e.getStackTrace();

        for (StackTraceElement stackTraceElement : elements) {
            if (stackTraceElement.toString().startsWith(stackTrace.getClassName() + "." + stackTrace.getMethodName())) {
                mensajeObtenido = stackTraceElement.toString().replaceAll(stackTrace.getClassName() + ".", "");
                break;
            }
        }

        Logger logger = Logger.getLogger(stackTrace.getClassName());

        if (e.getMessage() != null) {
            logger.log(Level.SEVERE, "{0}|{1}|{2}\n", new Object[]{mensajeObtenido, e.getClass().getSimpleName(), e.getMessage()});
        } else {
            logger.log(Level.SEVERE, "{0}|{1}\n", new Object[]{mensajeObtenido, e.getClass().getSimpleName()});
        }

    }

    /**
     *
     * @param e
     * @param uuidOperation
     */
    public void loggerMessageException(Exception e, String uuidOperation) {

        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[2];

        String mensajeObtenido = "";
        StackTraceElement[] elements = e.getStackTrace();

        for (StackTraceElement stackTraceElement : elements) {
            if (stackTraceElement.toString().startsWith(stackTrace.getClassName() + "." + stackTrace.getMethodName())) {
                mensajeObtenido = stackTraceElement.toString().replaceAll(stackTrace.getClassName() + ".", "");
                break;
            }
        }

        Logger logger = Logger.getLogger(stackTrace.getClassName());

        if (e.getMessage() != null) {
            logger.log(Level.SEVERE, uuidOperation + "|{0}|{1}|{2}\n", new Object[]{mensajeObtenido, e.getClass().getSimpleName(), e.getMessage()});
        } else {
            logger.log(Level.SEVERE, uuidOperation + "|{0}|{1}\n", new Object[]{mensajeObtenido, e.getClass().getSimpleName()});
        }

    }

}
