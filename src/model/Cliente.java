package model;

import java.util.List;
import java.util.ArrayList;

public class Cliente {

    private String nome;
    private String cpf;
    private List<Conta> contas = new ArrayList<>();

    public Cliente(String nome, String cpf){
    }

    public void adicionarConta(Conta conta){
    }

    public List<Conta> getContas(){
        return null;
    }

    public String getNome(){
        return null;
    }

    public String getCpf(){
        return null;
    }
}