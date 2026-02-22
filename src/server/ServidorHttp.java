package server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import service.Banco;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class ServidorHttp {

    public static void iniciar(Banco banco) throws Exception {

        HttpServer servidor = HttpServer.create(new InetSocketAddress(8080), 0);

        servidor.createContext("/saldo", (HttpExchange exchange) -> {

            // ===== CORS =====
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

            // ===== Preflight request =====
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            String query = exchange.getRequestURI().getQuery();
            String resposta;

            try {

                if (query == null || !query.contains("conta=")) {

                    resposta = "Informe numero da conta: /saldo?conta=1";

                } else {

                    int numero = Integer.parseInt(query.split("=")[1]);

                    var conta = banco.buscarConta(numero);

                    if (conta == null) {
                        resposta = "Conta nao encontrada";
                    } else {
                        resposta = "Saldo: " + conta.getSaldo();
                    }
                }

            } catch (Exception e) {
                resposta = "Erro na requisicao: " + e.getMessage();
            }

            byte[] bytes = resposta.getBytes(StandardCharsets.UTF_8);

            exchange.sendResponseHeaders(200, bytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        });

        servidor.start();
        System.out.println("Servidor rodando em http://localhost:8080");
    }
}