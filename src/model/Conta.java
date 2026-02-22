package model;

public abstract class Conta {

    protected int numero;
    protected Cliente cliente;
    protected double saldo;

    public Conta(int numero, Cliente cliente){
        this.numero = numero;
        this.cliente = cliente;
    }

    public int getNumero(){
        return numero;
    }

    public double getSaldo(){
        return saldo;
    }

    public void depositar(double valor){
        saldo += valor;
    }

    public boolean sacar(double valor){
        if(valor <= saldo){
            saldo -= valor;
            return true;
        }
        return false;
    }
}