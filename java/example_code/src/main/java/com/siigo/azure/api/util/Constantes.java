/*
 * Constantes.java
 *
 * Proyecto: Integrador de Servicios
 * Cliente: SIIGO
 * Copyright 2015 by Mobiltech SAS 
 * All rights reserved
 */
package com.siigo.azure.api.util;

/**
 *
 * @author Sys. E. Diego Armando Hernandez
 *
 */
public interface Constantes {

    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------    
    // Credenciales para consumir api de siigo
    public static final String USERNAME = "usuario@apisiigo.onmicrosoft.com";
    public static final String PASSWORD = "clave";
    public static final String SUSCRIPTIONKEY = "43281f345cbf1c593441f0573c401565";
    public static final String APIVERSION = "v1";

    //----------------------------------------------------------------------------
    //Constantes para generar el accessToken
    public static final String AUTHORITY = "https://login.microsoftonline.com/apisiigo.onmicrosoft.com/oauth2/token";
    public static final String GRANT_TYPE_PASSWORD = "password";
    public static final String RESOURCE = "https://apisiigo.onmicrosoft.com/ApiNubeApp";
    
    public static final String CLIENT_ID = "cbdc27ae-0a0a-4ddb-a2e0-24e04cc2655e";
    public static final String CLIENT_SECRET = "B3BmiwSPAZi9Da8nRiFFi2BCqBB9VpQQuHmmGzXvbYo";



}
