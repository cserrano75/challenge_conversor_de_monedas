import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        Scanner opcionMenu = new Scanner(System.in);
        String monedaOrigen="";
        String monedaDestino="";

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson =  gsonBuilder.setPrettyPrinting().create();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);
        List<RegistroDeConversion> miListaDeConversiones= new ArrayList<>();

        while (true) {

            String valorConversion="";

            System.out.println("*********************************************************");
            System.out.println("Bienvenido(a) al conversor de monedas!! " + "\n" +
                    "Ingrese la opción del menu, según la conversión que desea realizar: " + "\n");

            System.out.println("1) Dolar =>> peso argentino");
            System.out.println("2) Peso argentino =>> dolar");

            System.out.println("3) Dolar =>> Real brasileño");
            System.out.println("4) Real brasileño =>> dolar");

            System.out.println("5) Dolar =>> peso chileno");
            System.out.println("6) Peso chileno =>> dolar");

            System.out.println("7) Dolar  =>> Renminbi chino");
            System.out.println("8) Renminbi chino =>> dolar");

            System.out.println("9) Salir" + "\n");
            System.out.println("*********************************************************");
            System.out.println("Elija una opción válida:");

            var opcionDeUsuario = opcionMenu.nextLine();

            if (opcionDeUsuario.equalsIgnoreCase("9")){
                System.out.println("Proceso Finalizado!!");
                break;
            }else
                switch(opcionDeUsuario) {

                    case "1":
                        System.out.println("Ingrese el valor que desea convertir (USD a ARS): ");
                        valorConversion = opcionMenu.nextLine();
                        monedaOrigen="USD";
                        monedaDestino="ARS";
                        break;
                    case "2":
                        System.out.println("Ingrese el valor que desea convertir (ARS a USD): ");
                        valorConversion = opcionMenu.nextLine();
                        monedaOrigen="ARS";
                        monedaDestino="USD";
                        break;
                    case "3":
                        System.out.println("Ingrese el valor que desea convertir (USD a BRL): ");
                        valorConversion = opcionMenu.nextLine();
                        monedaOrigen="USD";
                        monedaDestino="BRL";
                        break;
                    case "4":
                        System.out.println("Ingrese el valor que desea convertir (BRL a USD): ");
                        valorConversion = opcionMenu.nextLine();
                        monedaOrigen="BRL";
                        monedaDestino="USD";
                        break;
                    case "5":
                        System.out.println("Ingrese el valor que desea convertir (USD a CLP): ");
                        valorConversion = opcionMenu.nextLine();
                        monedaOrigen="USD";
                        monedaDestino="CLP";
                        break;
                    case "6":
                        System.out.println("Ingrese el valor que desea convertir (CLP a USD) :");
                        valorConversion = opcionMenu.nextLine();
                        monedaOrigen="CLP";
                        monedaDestino="USD";
                        break;
                    case "7":
                        System.out.println("Ingrese el valor que desea convertir (USD a CNY): ");
                        valorConversion = opcionMenu.nextLine();
                        monedaOrigen="USD";
                        monedaDestino="CNY";
                        break;
                    case "8":
                        System.out.println("Ingrese el valor que desea convertir (CNY a USD) :");
                        valorConversion = opcionMenu.nextLine();
                        monedaOrigen="CNY";
                        monedaDestino="USD";
                        break;
                    default:
                        System.out.println("La opcion ingresada no es valida");
                        break;
                }

            try {
                String linkApi = "https://v6.exchangerate-api.com/v6/c20a1c91eaf3bb998dd79e03/pair" + "/" + monedaOrigen + "/"
                        + monedaDestino + "/" +valorConversion;

                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(linkApi))
                        .build();

                HttpResponse<String> response = null;

                response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = null;
                if (response != null) {
                    json = response.body();
                }

                RegistroDeConversion miRegistroDeConversion = gson.fromJson(json, RegistroDeConversion.class);
                miListaDeConversiones.add(miRegistroDeConversion);

                System.out.println(valorConversion + " [" +miRegistroDeConversion.base_code()+"]" +
                        " corresponden a : " +miRegistroDeConversion.conversion_result()+
                        " ["+miRegistroDeConversion.target_code()+"]");

                System.out.println("Mi lista de conversiones: " +miListaDeConversiones);

            } catch (Exception e) {
                System.out.println("Ocurrio un error");
                System.out.println(e.getMessage());
            }

        }

    }
}
