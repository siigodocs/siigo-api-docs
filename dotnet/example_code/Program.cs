using System;
using System.Net.Http.Headers;
using System.Text;
using System.Net.Http;
using System.Web;
using System.Net;
using System.IO;
using Newtonsoft.Json;

namespace example_code
{
    class MainClass
    {

        // Credenciales para consumir api de siigo
        private static String USERNAME = "usuario@apisiigo.onmicrosoft.com";
        private static String PASSWORD = "clave";
        private static String SUSCRIPTIONKEY = "43281f345cbf1c593441f0573c401565";
        private static String APIVERSION = "v1";

        // Constantes para obtener el AccessToken
        private static String AUTHORITY = "https://login.microsoftonline.com/apisiigo.onmicrosoft.com/oauth2/token";
        private static String GRANT_TYPE_PASSWORD = "password";
        private static String RESOURCE = "https://apisiigo.onmicrosoft.com/ApiNubeApp";
        private static String CLIENT_ID = "cbdc27ae-0a0a-4ddb-a2e0-24e04cc2655e";
        private static String CLIENT_SECRET = "B3BmiwSPAZi9Da8nRiFFi2BCqBB9VpQQuHmmGzXvbYo=";

        // Datos de prueba de consumo de operaciones
        private static String accountId = "1234";
        private static String contactId = "1234";
        private static String productId = "1234";

        private static String request_url = "";
        private static String request_name = "";

        public static void Main(string[] args)
        {

            //GetAccessToken();

            //AccountsGetAll();
            AccountsGetByID();

            //ContactsGetAll();
            //ContactsGetAllByAccountID();
            //ContactsGetByID();

            //ProductsGetAll();
            //ProductsGetByID();

            Console.ReadLine();


        }

        static String GetOAuthToken()
        {

            var request = (HttpWebRequest)WebRequest.Create(AUTHORITY);

            var parameters = String.Format("grant_type={0}&resource={1}&client_id={2}&client_secret={3}&username={4}&password={5}", GRANT_TYPE_PASSWORD, RESOURCE, CLIENT_ID, CLIENT_SECRET, USERNAME, PASSWORD);

            var data = Encoding.ASCII.GetBytes(parameters);

            request.Method = "POST";
            request.ContentType = "application/x-www-form-urlencoded";
            request.ContentLength = data.Length;

            using (var stream = request.GetRequestStream())
            {
                stream.Write(data, 0, data.Length);
            }

            var response = (HttpWebResponse)request.GetResponse();
            var responseString = new StreamReader(response.GetResponseStream()).ReadToEnd();
            var myDetails = JsonConvert.DeserializeObject<ResponseToken>(responseString);

            Console.WriteLine();
            Console.WriteLine("OAuth Tokens:");
            Console.WriteLine(JsonConvert.SerializeObject(myDetails, Formatting.Indented));

            return myDetails.access_token;

        }

        public class ResponseToken
        {

            public string token_type
            {
                get;
                set;
            }
            public string scope
            {
                get;
                set;
            }
            public string expires_in
            {
                get;
                set;
            }
            public string ext_expires_in
            {
                get;
                set;
            }
            public string expires_on
            {
                get;
                set;
            }
            public string not_before
            {
                get;
                set;
            }
            public string resource
            {
                get;
                set;
            }
            public string access_token
            {
                get;
                set;
            }
            public string refresh_token
            {
                get;
                set;
            }
        }

        static void GetAccessToken()
        {

            Console.WriteLine();
            Console.WriteLine("Running GetAccessToken ...");

            GetOAuthToken();

            Console.WriteLine();
            Console.WriteLine("Hit ENTER to exit...");

        }

        static void AccountsGetAll()
        {

            request_name = System.Reflection.MethodBase.GetCurrentMethod().Name.ToString();
            request_url = String.Format("https://siigoapi.azure-api.net/nube/api/{0}/Accounts/GetAll", APIVERSION);

            ExecuteRequest();

        }

        static void AccountsGetByID()
        {

            request_name = System.Reflection.MethodBase.GetCurrentMethod().Name.ToString();
            request_url = String.Format("https://siigoapi.azure-api.net/nube/api/{0}/Accounts/GetByID/{1}", APIVERSION, accountId);

            ExecuteRequest();

        }

        static void ContactsGetAll()
        {

            request_name = System.Reflection.MethodBase.GetCurrentMethod().Name.ToString();
            request_url = String.Format("https://siigoapi.azure-api.net/nube/api/{0}/Contacts/GetAll", APIVERSION);

            ExecuteRequest();

        }

        static void ContactsGetAllByAccountID()
        {

            request_name = System.Reflection.MethodBase.GetCurrentMethod().Name.ToString();
            request_url = String.Format("https://siigoapi.azure-api.net/nube/api/{0}/Contacts/GetAllByAccountID/{1}", APIVERSION, accountId);

        }

        static void ContactsGetByID()
        {

            request_name = System.Reflection.MethodBase.GetCurrentMethod().Name.ToString();
            request_url = String.Format("https://siigoapi.azure-api.net/nube/api/{0}/Contacts/GetByID/{1}", APIVERSION, contactId);

            ExecuteRequest();

        }

        static void ProductsGetAll()
        {

            request_name = System.Reflection.MethodBase.GetCurrentMethod().Name.ToString();
            request_url = String.Format("https://siigoapi.azure-api.net/nube/api/{0}/Products/GetAll", APIVERSION);

            ExecuteRequest();

        }

        static void ProductsGetByID()
        {

            request_name = System.Reflection.MethodBase.GetCurrentMethod().Name.ToString();
            request_url = String.Format("https://siigoapi.azure-api.net/nube/api/{0}/Products/GetByID/{1}", APIVERSION, productId);

            ExecuteRequest();

        }

        static async void ExecuteRequest()
        {

            Console.WriteLine();
            Console.WriteLine("Running " + request_name + " ...");

            var client = new HttpClient();

            // Request headers
            client.DefaultRequestHeaders.Add("Ocp-Apim-Subscription-Key", SUSCRIPTIONKEY);
            client.DefaultRequestHeaders.Add("Authorization", GetOAuthToken());

            var r = await client.GetAsync(request_url);

            var result = JsonConvert.SerializeObject(JsonConvert.DeserializeObject(await r.Content.ReadAsStringAsync()), Formatting.Indented);

            Console.WriteLine();
            Console.WriteLine("Response:");
            Console.WriteLine(result);
            Console.WriteLine();
            Console.WriteLine("Hit ENTER to exit...");

        }

    }

}
