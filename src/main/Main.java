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
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }

        }while(opcao != 0);
    }
}