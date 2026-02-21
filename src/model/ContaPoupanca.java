package model;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(int numero, Cliente cliente){
        super(numero, cliente);
    }

    @Override
    public void sacar(double valor){
    }
}