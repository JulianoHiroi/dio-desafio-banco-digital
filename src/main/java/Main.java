package main.java;

import java.util.List;
import java.util.Scanner;

public class Main {

	private static Banco banco;
	private static Scanner input;
	
	public static void main(String[] args) {
		banco = new Banco("DIO");
		input = new Scanner(System.in);
		while(true) {
			System.out.println("---- Banco do" + banco.getNome() + " ----");
			System.out.println("Qual é o nome do usuário:");
			String nome = input.next();
			Cliente cliente = new Cliente(nome);
			while (true) {
				System.out.println("Qual acao voce gostaria de realizar:");
				System.out.println(" 1 - Criar conta");
				System.out.println(" 2 - Operacao na conta");
				System.out.println(" 3 - Sair");
				int opcao = input.nextInt();
				if(opcao == 1) {
					criarConta(cliente);
				}else if(opcao == 2) {
					operacao(cliente);
					System.out.println();
					System.out.println("Fim de operacao");
					System.out.println();
					
				}else {
					System.out.println("Tchau. Banco " + banco.getNome() + " sempre a disposicacao! " );
					break;
				}
			}
		}
	}
	
	static public void criarConta(Cliente cliente) {
		System.out.println("Qual tipo de conta voce quer?");
		System.out.println(" 1 - Conta corrente");
		System.out.println(" 2 - Conta poupança");
		int opcao = input.nextInt();
		Conta conta = null;
		if(opcao ==  1) {
			conta = new ContaCorrente(cliente);
		}else if(opcao == 2) {
			conta = new ContaCorrente(cliente);
		}else {
			System.out.println("Algo deu errado.");
			return;
		}
		banco.adicionarConta(conta);
	}
	
	
	static public void  operacao(Cliente cliente) {
		
		List<Conta> contas = banco.getContasPorNome(cliente);
		if(contas.size() == 0) {
			System.out.println("O Cliente não possui nenhuma conta. Por favor crie uma antes.");
			return;
		}
		System.out.println("De qual conta voce deseja realizar a operacao?");
		for(int i = 0 ; i < contas.size() ; i ++ ) {
			Conta conta = contas.get(i);
			System.out.println(i + " - " + conta.numero);
		}
		System.out.println("Digite o indice da conta:");
		int opcao = input.nextInt();
		Conta conta = null;
		try {
			conta = contas.get(opcao);
		}catch(IndexOutOfBoundsException e) {
			System.out.println("Seleção inválida");
			return;
		}
		System.out.println("Qual operação voce deseja realizar?");
		System.out.println(" 0 - Depositar");
		System.out.println(" 1 - Sacar");
		System.out.println(" 2 - Transferir");
		System.out.println(" 3 - Conferir Saldo");
		System.out.println(" 4 - Imprimir Extrato");
		System.out.println(" 5 - Cancelar");
		opcao = input.nextInt();
		if(opcao ==  0) {
			System.out.println("Quantos você deseja depositar?");
			double deposito = input.nextDouble();
			conta.depositar(deposito);
		
		}else if(opcao ==  1) {
			System.out.println("Quantos você deseja sacar?");
			double saque = input.nextDouble();
			conta.sacar(saque);
		}else if(opcao == 2) {
			System.out.println("Quantos você deseja tranferir?");
			double transferencia = input.nextDouble();
			System.out.println("Para qual conta?");
			List<Conta> contasTransferencia = banco.getContas(); // retorna um array list de contas 
			for(int i = 0 ; i < contas.size() ; i ++ ) {
				Conta c = contas.get(i);
				System.out.println(i + " - " + c.numero);
			}
			System.out.println("Digite o indice da conta:");
			int escolha = input.nextInt();
			Conta contaEscolhida = contasTransferencia.get(escolha);
			conta.transferir(transferencia, contaEscolhida);
		}else if(opcao ==  3) {
			System.out.println("O seu saldo é : " + conta.getSaldo());
		}else if(opcao ==  4) {
			conta.imprimirExtrato();
		}else {
			System.out.println("Operacao cancelada");
		}
	}

}