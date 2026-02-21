package service;

import model.*;
import java.util.List;
import java.util.ArrayList;

public class Banco {

    private List<Cliente> clientes = new ArrayList<>();
    private int contadorContas = 1;

    public Cliente criarCliente(String nome, String cpf){
        return null;
    }

    public Conta criarContaCorrente(Cliente cliente){
        return null;
    }

    public Conta criarContaPoupanca(Cliente cliente){
        return null;
    }

    public Cliente buscarCliente(String cpf){
        return null;
    }
}