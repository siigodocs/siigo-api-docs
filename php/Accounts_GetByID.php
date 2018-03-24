<?php

// This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
error_reporting(E_ALL);
ini_set("display_errors", "On");


require_once 'HTTP/Request2.php';
require_once './Constantes.php';
require_once './SiigoOAuth.php';


$idAccount ="111111";

//Dirección URL de la solicitud: https://siigoapi.azure-api.net/nube/api/{Version}/Accounts/GetByID/{Id}
$request = new Http_Request2('https://siigoapi.azure-api.net/nube/api/'.Constantes::API_VERSION.'/Accounts/GetByID/'.$idAccount);
$url = $request->getUrl();

$headers = array(
    // Request headers
    'Ocp-Apim-Subscription-Key' => Constantes::SUSCRIPTION_KEY,
    'Authorization' => GetTokenUser(),
);

$request->setHeader($headers);

$parameters = array(
    // Request parameters
);

$url->setQueryVariables($parameters);

$request->setMethod(HTTP_Request2::METHOD_GET);

// Request body
$request->setBody("{body}");

try {
    $response = $request->send();
    echo $response->getBody();
} catch (HttpException $ex){
    echo $ex;
}
?>