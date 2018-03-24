# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

__author__ = "mobiltech"
__date__ = "$20-mar-2018 9:07:37$"

if __name__ == "__main__":
    print "Hello World"

import requests
import  Constantes
import json

# Esta script solo funciona para python version 2.7

# Para correr este script debe tener python request installado en caso de que no lo tenga
# intente ejecutando el siguiente comando en su terminal :  pip install requests 

# Para correr este archivo antes debe configurar los datos de su API en el archivo constantes.py

   
def getaccesstoken():

    url = "https://login.microsoftonline.com/apisiigo.onmicrosoft.com/oauth2/token"
    querystring = {"grant_type":"password"}

    payload = "grant_type=password&resource=" + Constantes.RESOURCE + "&client_id=" + Constantes.CLIENT_ID + "&client_secret=" + Constantes.CLIENT_SECRET + "=&username=" + Constantes.USERNAME + "&password=" + Constantes.PASSWORD
    headers = {
        'Accept': "application/json",
        'Content-Type': "application/x-www-form-urlencoded",
        'Cache-Control': "no-cache"
        }

    response = requests.request("POST", url, data=payload, headers=headers, params=querystring)
    #print(response.text)
    #jsonFull = json.dumps(json.loads(response.text), indent=4, sort_keys=True)
    jsonFull = json.loads(response.text)
    access_token = jsonFull["access_token"]
    return access_token
