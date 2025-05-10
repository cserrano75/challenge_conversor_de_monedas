import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        Scanner opcionMenu = new Scanner(System.in);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);

        while (true) {
            System.out.println("*********************************************************");
            System.out.println("Bienvenido(a) al conversor de monedas!! " + "\n" +
                    "Ingrese la opción del menu, según la conversión que desea realizar: " + "\n");

            System.out.println("1) Dolar =>> peso argentino");
            System.out.println("2) Peso argentino =>> dolar");
            System.out.println("3) Dolar =>> Real brasileño");
            System.out.println("4) Real brasileño =>> dolar");
            System.out.println("5) Dolar =>> peso chileno");
            System.out.println("6) Peso chileno =>> dolar");
            System.out.println("7) Salir" + "\n");
            System.out.println("*********************************************************");
            System.out.println("Elija una opción válida:");

            var busqueda = opcionMenu.nextLine();

                switch(busqueda) {
                    case "1":
                        System.out.println("Ingrese el valor que desea convertir");

                        Scanner valorConversion = new Scanner(System.in);
                        String monedaOrigen="USD";
                        String monedaDestino="ARS";


                        break;
                    case "2":
                        System.out.println("Opcion2");

                        break;
                    case "7":
                        break;
                }

            String linkApi = "https://v6.exchangerate-api.com/v6/c20a1c91eaf3bb998dd79e03/pair" + "/"+monedaOrigen+"/";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(linkApi))
                    .build();

            HttpResponse<String> response = null;
            try {
                response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
            } catch (Exception e) {
                System.out.println("Ocurrio un error");
                System.out.println(e.getMessage());

            }

            String json = response.body();

            RegistroDeConversion miRegistroDeConversion = gson.fromJson(json, RegistroDeConversion.class);
            System.out.println(json);

        }
    }

}
