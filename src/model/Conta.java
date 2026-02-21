package model;

public abstract class Conta {

    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(int numero, Cliente cliente){
    }

    public void depositar(double valor){
    }

    public abstract void sacar(double valor);

    public double getSaldo(){
        return 0;
    }

    public int getNumero(){
        return 0;
    }
}