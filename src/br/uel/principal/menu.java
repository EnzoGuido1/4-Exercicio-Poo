package br.uel.principal;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

//Esta sendo utilizada a formatacao numerica dos estados unidos,
//portanto ponto significa casas decimais e virgula significa casas dos milhares 
//exemplo - "8,000.00" oito mil - "8.20" oito (unidade) e vinte (decimal)
//Utilizar essa formatacao na insercao de quaisquer valores numericos
public class menu {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		input.useLocale(Locale.US);
		
		Integer aux = 1;
		
		Integer tipoConta = 0;
		
		Principal execucao = new Principal();
		
		while(aux != 0) {
			
			System.out.println("\nInsira:\n1 para cadrastrar uma conta\n2 para sacar um valor de uma conta\n3 para atualizar uma conta poupanca com o seu rendimento\n4 para depositar um valor em uma conta\n5 para mostrar o saldo de uma conta\n6 para calcular os tributos de uma conta investimento\n7 para calcular a taxa de administracao de uma conta investimento\n0 para encerrar o programa ");
			
			aux = input.nextInt();
			
			if(aux == 1) {
				System.out.println("\nInsira:\n1 para cadastrar uma conta corrente\n2 para cadastrar uma conta poupanca\n3 para cadastrar uma conta investimento ");
				tipoConta = input.nextInt();
				System.out.println("\nInsira o nome do cliente");
				String cliente = input.next();
				System.out.println("\nInsira o numero da conta");
				String numeroConta = input.next();
				System.out.println("\nInsira o saldo da conta");
				BigDecimal saldo = input.nextBigDecimal();
				execucao.cadastrarConta(tipoConta, cliente, numeroConta, saldo);
			}
			
			if(aux == 2) {
				System.out.println("\nInsira o numero da conta");
				String numeroConta = input.next();
				System.out.println("\nInsira o valor do saque");
				BigDecimal saque = input.nextBigDecimal();
				execucao.sacarValorConta(numeroConta, saque);
				
			}else if(aux == 3) {
				System.out.println("\nInsira o numero da conta poupanca");
				String numeroConta = input.next();
				System.out.println("\nInsira o valor do rendimento em decimal (exemplo: 0.10 = 10%)");
				BigDecimal rendimento = input.nextBigDecimal();
				execucao.atualizarContaPoupanca(numeroConta, rendimento);
				
			}else if(aux == 4) {
				System.out.println("\nInsira o numero da conta");
				String numeroConta = input.next();
				System.out.println("\nInsira o valor do deposito");
				BigDecimal deposito = input.nextBigDecimal();
				execucao.depositarValorConta(numeroConta, deposito);
				
			}else if(aux == 5) {
				System.out.println("\nInsira o numero da conta");
				String numeroConta = input.next();
				execucao.mostrarSaldoConta(numeroConta);
				
			}else if(aux == 6) {
				System.out.println("\nInsira o numero da conta investimento");
				String numeroConta = input.next();
				System.out.println("\nInsira a taxa de rendimento em decimal (exemplo: 0.10 = 10%)");
				BigDecimal rendimento = input.nextBigDecimal();
				execucao.calcularTributo(numeroConta, rendimento);
				
			}else if(aux == 7) {
				System.out.println("\nInsira o numero da conta investimento");
				String numeroConta = input.next();
				System.out.println("\nInsira a taxa de rendimento em decimal (exemplo: 0.10 = 10%)");
				BigDecimal rendimento = input.nextBigDecimal();
				execucao.calcularTaxaAdministracao(numeroConta, rendimento);
				
			}
			
		}
			
		input.close();
	}
}
