import json
import  Constantes
import httplib, urllib, base64
import SiigoOAuth




########### Python 2.7 #############

# Esta script solo funciona para python version 2.7
# Para correr este archivo antes debe configurar los datos de su API en el archivo constantes.py

headers = {
    # Request headers
    'Ocp-Apim-Subscription-Key': Constantes.SUSCRIPTION_KEY,
    'Authorization': SiigoOAuth.getaccesstoken(),
}

params = urllib.urlencode({})

try:
    conn = httplib.HTTPSConnection('siigoapi.azure-api.net')
    conn.request("GET", "/nube/api/" + Constantes.API_VERSION + "/Contacts/GetAll?%s" % params, "{body}", headers)
    response = conn.getresponse()
    data = response.read()
    
    print json.dumps(json.loads(data), indent=4, sort_keys=True)
    
    conn.close()
except Exception as e:
    print("[Errno {0}] {1}".format(e.errno, e.strerror))

####################################