package model;

public class ContaCorrente extends Conta {

    private double taxa = 2.5;

    public ContaCorrente(int numero, Cliente cliente){
        super(numero, cliente);
    }

    @Override
    public void sacar(double valor){
    }
}