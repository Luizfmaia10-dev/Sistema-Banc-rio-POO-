package server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import service.Banco;
import model.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class ServidorHttp {

    public static void iniciar(Banco banco) throws Exception {

        HttpServer servidor = HttpServer.create(new InetSocketAddress(8080), 0);

        servidor.createContext("/", exchange -> {

            adicionarCors(exchange);

            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            String path = exchange.getRequestURI().getPath();
            String metodo = exchange.getRequestMethod();

            try {

                // ================= SALDO =================
                if (path.equals("/saldo") && metodo.equals("GET")) {

                    String query = exchange.getRequestURI().getQuery();

                    if (query == null || !query.contains("conta=")) {
                        responder(exchange, 400, json("erro", "Informe /saldo?conta=1"));
                        return;
                    }

                    int numero = Integer.parseInt(query.split("=")[1]);
                    Conta conta = banco.buscarConta(numero);

                    if (conta == null) {
                        responder(exchange, 404, json("erro", "Conta não encontrada"));
                        return;
                    }

                    responder(exchange, 200,
                            "{ \"conta\": " + numero + ", \"saldo\": " + conta.getSaldo() + " }");
                }

                // ================= DEPOSITAR =================
                else if (path.equals("/depositar") && metodo.equals("POST")) {

                    String body = lerBody(exchange);

                    int numero = Integer.parseInt(extrair(body, "conta"));
                    double valor = Double.parseDouble(extrair(body, "valor"));

                    Conta conta = banco.buscarConta(numero);

                    if (conta == null) {
                        responder(exchange, 404, json("erro", "Conta não encontrada"));
                        return;
                    }

                    conta.depositar(valor);

                    responder(exchange, 200,
                            "{ \"mensagem\": \"Depósito realizado\", \"saldo\": " + conta.getSaldo() + " }");
                }

                // ================= SACAR =================
                else if (path.equals("/sacar") && metodo.equals("POST")) {

                    String body = lerBody(exchange);

                    int numero = Integer.parseInt(extrair(body, "conta"));
                    double valor = Double.parseDouble(extrair(body, "valor"));

                    Conta conta = banco.buscarConta(numero);

                    if (conta == null) {
                        responder(exchange, 404, json("erro", "Conta não encontrada"));
                        return;
                    }

                    if (!conta.sacar(valor)) {
                        responder(exchange, 400, json("erro", "Saldo insuficiente"));
                        return;
                    }

                    responder(exchange, 200,
                            "{ \"mensagem\": \"Saque realizado\", \"saldo\": " + conta.getSaldo() + " }");
                }

                // ================= ROTA INVALIDA =================
                else {
                    responder(exchange, 404, json("erro", "Rota não encontrada"));
                }

            } catch (Exception e) {
                responder(exchange, 500, json("erro", e.getMessage()));
            }

        });

        servidor.start();
        System.out.println("🚀 API rodando → http://localhost:8080");
    }


    // =================================================
    // UTILIDADES
    // =================================================

    private static void adicionarCors(HttpExchange ex) {
        ex.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        ex.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        ex.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        ex.getResponseHeaders().add("Content-Type", "application/json");
    }

    private static void responder(HttpExchange ex, int status, String resposta) throws IOException {
        byte[] bytes = resposta.getBytes(StandardCharsets.UTF_8);
        ex.sendResponseHeaders(status, bytes.length);
        OutputStream os = ex.getResponseBody();
        os.write(bytes);
        os.close();
    }

    private static String lerBody(HttpExchange ex) throws IOException {
        InputStream is = ex.getRequestBody();
        return new String(is.readAllBytes(), StandardCharsets.UTF_8);
    }

    // parser JSON simples
    private static String extrair(String json, String chave) {
        return json.split("\"" + chave + "\":")[1]
                .split("[,}]")[0]
                .replace("\"", "")
                .trim();
    }

    private static String json(String chave, String valor) {
        return "{ \"" + chave + "\": \"" + valor + "\" }";
    }
}