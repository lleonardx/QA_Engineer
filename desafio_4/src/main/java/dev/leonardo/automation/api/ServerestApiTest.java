package dev.leonardo.automation.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerestApiTest {
    private static final String BASE_URL = "http://localhost:3000";
    private static String authToken;

    private static HttpResponse sendRequest(HttpRequestBase request) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        if (authToken != null) {
            request.setHeader("Authorization", "Bearer " + authToken);
        }
        // Log antes de enviar a requisição
        System.out.println("Sending " + request.getMethod() + " request to: " + request.getURI());

        HttpResponse response = httpClient.execute(request);

        // Log após receber a resposta
        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());

        return response;
    }
    private static String extractToken(String responseBody) {
        try {
            JSONObject jsonResponse = new JSONObject(responseBody);
            return jsonResponse.getString("token");
        } catch (JSONException e) {
            System.out.println("Token not found in response: " + responseBody);
            return null;
        }
    }

    private static String login() throws Exception {
        HttpPost httpPost = new HttpPost(BASE_URL + "/login");

        // Substitua 'seu_usuario' e 'sua_senha' pelos dados reais
        String jsonBody = "{\"email\": \"devjavaleonardo@gmail.com\", \"password\": \"testejava\"}";

        httpPost.setEntity(new StringEntity(jsonBody));
        httpPost.setHeader("Content-type", "application/json");

        HttpResponse response = sendRequest(httpPost);
        System.out.println("Response Code after login: " + response.getStatusLine().getStatusCode());

        // Exibir a resposta completa do servidor
        String responseBody = EntityUtils.toString(response.getEntity());
        System.out.println("Login Response Body: " + responseBody);

        return extractTokenFromLogin(responseBody);
    }
    private static String extractTokenFromLogin(String responseBody) {
        try {
            JSONObject jsonResponse = new JSONObject(responseBody);
            return jsonResponse.getString("authorization");
        } catch (JSONException e) {
            System.out.println("Token not found in login response: " + responseBody);
            return null;
        }
    }

    private static void createUser() throws Exception {
        // Substitua com os dados do usuário que você deseja criar
        String jsonBody = "{\"nome\": \"Leonardo Teste\", \"email\": \"devjavaleonardo@gmail.com\", \"password\": \"testejava\", \"administrador\": \"true\"}";

        System.out.println("Input for Create User: " + jsonBody);

        HttpPost httpPost = new HttpPost(BASE_URL + "/usuarios");
        httpPost.setEntity(new StringEntity(jsonBody));
        httpPost.setHeader("Content-type", "application/json");

        HttpResponse response = sendRequest(httpPost);
        System.out.println("Create User Response Code: " + response.getStatusLine().getStatusCode());

        // Exibir a resposta completa do servidor
        String responseBody = EntityUtils.toString(response.getEntity());
        System.out.println("Create User Response Body: " + responseBody);

        if (response.getStatusLine().getStatusCode() == 201) {
            authToken = login();
        }
    }
    private static void checkUser() throws Exception {

        HttpGet httpGet = new HttpGet(BASE_URL + "/usuarios");

        HttpResponse response = sendRequest(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Verificando Usuario Response Code: " + statusCode);

        if (statusCode == 200) {
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Verificando Usuario Response Body: " + responseBody);
        }
    }

    private static void createProduct() throws Exception {
        // Substitua com os dados do produto que você deseja criar
        String jsonBody = "{\"nome\": \"Samsung Odyssey \", \"preco\": 6500, \"descricao\": \"Monitor\", \"quantidade\": 50}";

        System.out.println("Input for Create Product: " + jsonBody);

        try {
            // Verificar se o token está presente
            if (authToken == null) {
                authToken = login(); // Realizar login se o token não estiver presente
                if (authToken == null) {
                    throw new RuntimeException("Falha ao obter o token de acesso. Não é possível criar o produto.");
                } else {
                    System.out.println("Token de acesso obtido com sucesso: " + authToken);
                }
            }

            // Agora que temos o token, criar a solicitação HTTP
            HttpPost httpPost = new HttpPost(BASE_URL + "/produtos");
            httpPost.setEntity(new StringEntity(jsonBody));
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Authorization", "Bearer " + authToken);

            HttpResponse response = sendRequest(httpPost);
            System.out.println("Create Product Response Code: " + response.getStatusLine().getStatusCode());

            // Exibir a resposta completa do servidor
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Create Product Response Body: " + responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void checkProduct() throws Exception {
        HttpGet httpGet = new HttpGet(BASE_URL + "/produtos");

        HttpResponse response = sendRequest(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Verificando Produto Response Code: " + statusCode);

        if (statusCode == 200) {
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Verificando Produto Response Body: " + responseBody);
        }
    }

    public static void executeCrudOperations() {
        try {
            // Cadastrar usuário
            createUser();

            // Verificar se o usuário foi criado
            checkUser();

            // Se autorizado, criar o produto
            createProduct();

            // Verificar se o produto foi criado
            checkProduct();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
