package service;

import model.*;
import java.util.List;
import java.util.ArrayList;

public class Banco {

    private List<Cliente> clientes = new ArrayList<>();
    private List<Conta> contas = new ArrayList<>();
    private int contadorContas = 1;

    // cria cliente
    public Cliente criarCliente(String nome, String cpf){
        Cliente c = new Cliente(nome, cpf);
        clientes.add(c);
        return c;
    }

    // cria conta corrente
    public Conta criarContaCorrente(Cliente cliente){
        Conta conta = new ContaCorrente(contadorContas++, cliente);
        contas.add(conta);
        return conta;
    }

    // cria conta poupanca
    public Conta criarContaPoupanca(Cliente cliente){
        Conta conta = new ContaPoupanca(contadorContas++, cliente);
        contas.add(conta);
        return conta;
    }

    // busca cliente pelo cpf
    public Cliente buscarCliente(String cpf){
        for(Cliente c : clientes){
            if(c.getCpf().equals(cpf)){
                return c;
            }
        }
        return null;
    }

    // busca conta pelo numero
    public Conta buscarConta(int numero){
        for(Conta c : contas){
            if(c.getNumero() == numero){
                return c;
            }
        }
        return null;
    }
}