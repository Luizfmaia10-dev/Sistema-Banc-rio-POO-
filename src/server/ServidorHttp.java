package server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import service.Banco;

import java.io.OutputStream;
import java.net.InetSocketAddress;

public class ServidorHttp {

    private static Banco banco = new Banco();

    public static void iniciar() throws Exception {

        HttpServer servidor = HttpServer.create(new InetSocketAddress(8080), 0);

        servidor.createContext("/saldo", (HttpExchange exchange) -> {

            String resposta = "API do Banco funcionando";

            exchange.sendResponseHeaders(200, resposta.length());
            OutputStream os = exchange.getResponseBody();
            os.write(resposta.getBytes());
            os.close();
        });

        servidor.start();
        System.out.println("Servidor rodando em http://localhost:8080");
    }
}