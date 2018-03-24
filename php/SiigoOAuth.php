<?php

// This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)

error_reporting(E_ALL);
ini_set("display_errors", "On");



require_once 'HTTP/Request2.php';
require_once './Constantes.php';

function GetTokenUser() {
    $body = "grant_type=" . Constantes::GRANT_TYPE_PASSWORD . "&resource=" . Constantes::RESOURCE
          . "&client_id=" . Constantes::CLIENT_ID . "&client_secret=" . Constantes::CLIENT_SECRET . ""
          . "=&username=" . Constantes::USERNAME . "&password=" . Constantes::PASSWORD;


    $request = new Http_Request2(Constantes::AUTHORITY);
    $url = $request->getUrl();

    $headers = array(
        // Request headers
        "Accept" => "application/json",
        'Content-type' => 'application/x-www-form-urlencoded',
    );

    $request->setHeader($headers);

    $parameters = array();

    $url->setQueryVariables($parameters);

    $request->setMethod(HTTP_Request2::METHOD_POST);

// Request body
    $request->setBody($body);

//echo $request->getBody();

    try {
        $response = $request->send();
        $data = json_decode($response->getBody());
        return $data->{'access_token'};   
    } catch (HttpException $ex) {
        echo $ex;
    }
}
?>


