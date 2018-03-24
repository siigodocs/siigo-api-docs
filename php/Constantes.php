<?php

error_reporting(E_ALL);
ini_set("display_errors", "On");

class Constantes {

    const USERNAME = "usuario@apisiigo.onmicrosoft.com";
    const PASSWORD = "clave";
    
    
    const SUSCRIPTION_KEY = "43281f345cbf1c593441f0573c401565";
    const API_VERSION = "v1";
    const AUTHORITY = "https://login.microsoftonline.com/apisiigo.onmicrosoft.com/oauth2/token";
    const GRANT_TYPE_PASSWORD = "password";
    const RESOURCE = "https://apisiigo.onmicrosoft.com/ApiNubeApp";
    const CLIENT_ID = "cbdc27ae-0a0a-4ddb-a2e0-24e04cc2655e";
    const CLIENT_SECRET = "B3BmiwSPAZi9Da8nRiFFi2BCqBB9VpQQuHmmGzXvbYo";

    public function __getUserName() {
        return self::USERNAME;
    }

    public function __getPassword() {
        return self::PASSWORD;
    }

    public function __getSuscriptionKey() {
        return self::SUSCRIPTION_KEY;
    }

    public function __getApiVersion() {
        return self::API_VERSION;
    }

    public function __getAuthority() {
        return self::AUTHORITY;
    }

    public function __getGranTypePassword() {
        return self::GRANT_TYPE_PASSWORD;
    }

    public function __getRespurce() {
        return self::RESOURCE;
    }

    public function __getClientId() {
        return self::CLIENT_ID;
    }

    public function __getClientSecret() {
        return self::CLIENT_SECRET;
    }

}
