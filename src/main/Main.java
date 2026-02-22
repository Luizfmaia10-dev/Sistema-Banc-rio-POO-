package main;

import service.Banco;
import server.ServidorHttp;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Banco banco = new Banco();
        ServidorHttp.iniciar(banco);

        Scanner sc = new Scanner(System.in);
        int opcao;

        do{
            System.out.println("1 Criar cliente");
            System.out.println("2 Criar conta");
            System.out.println("3 Depositar");
            System.out.println("4 Sacar");
            System.out.println("5 Ver saldo");
            System.out.println("0 Sair");

            opcao = sc.nextInt();

            switch(opcao){

                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.next();

                    System.out.print("CPF: ");
                    String cpf = sc.next();

                    banco.criarCliente(nome, cpf);
                    System.out.println("Cliente criado!");
                    break;

                case 2:
                    System.out.print("CPF do cliente: ");
                    String cpfConta = sc.next();

                    var cliente = banco.buscarCliente(cpfConta);

                    if(cliente == null){
                        System.out.println("Cliente nao encontrado");
                        break;
                    }

                    System.out.println("1 Corrente | 2 Poupanca");
                    int tipo = sc.nextInt();

                    var conta = (tipo == 1)
                            ? banco.criarContaCorrente(cliente)
                            : banco.criarContaPoupanca(cliente);

                    System.out.println("Conta criada numero: " + conta.getNumero());
                    break;

                case 3:
                    System.out.print("Numero conta: ");
                    int numDep = sc.nextInt();

                    var contaDep = banco.buscarConta(numDep);

                    if(contaDep == null){
                        System.out.println("Conta nao encontrada");
                        break;
                    }

                    System.out.print("Valor: ");
                    double valorDep = sc.nextDouble();

                    contaDep.depositar(valorDep);
                    System.out.println("Deposito realizado");
                    break;

                case 4:
                    System.out.print("Numero conta: ");
                    int numSac = sc.nextInt();

                    var contaSac = banco.buscarConta(numSac);

                    if(contaSac == null){
                        System.out.println("Conta nao encontrada");
                        break;
                    }

                    System.out.print("Valor: ");
                    double valorSac = sc.nextDouble();

                    if(contaSac.sacar(valorSac))
                        System.out.println("Saque realizado");
                    else
                        System.out.println("Saldo insuficiente");
                    break;

                case 5:
                    System.out.print("Numero conta: ");
                    int numSaldo = sc.nextInt();

                    var contaSaldo = banco.buscarConta(numSaldo);

                    if(contaSaldo == null)
                        System.out.println("Conta nao encontrada");
                    else
                        System.out.println("Saldo: " + contaSaldo.getSaldo());
                    break;
            }

        }while(opcao != 0);
    }
}