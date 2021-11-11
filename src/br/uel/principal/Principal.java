package br.uel.principal;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Principal {
	public ArrayList<ContaCorrente> cc = new ArrayList<ContaCorrente>();
	public ArrayList<ContaPoupanca> cp = new ArrayList<ContaPoupanca>();
	public ArrayList<ContaInvestimento> ci = new ArrayList<ContaInvestimento>();
	
	int tipoConta;
	
	public ArrayList<ContaCorrente> getContaCorrente() {
		return cc;
	}
	
	public void setContaCorrente(ArrayList<ContaCorrente> contaCorrente) {
		this.cc = contaCorrente;
	}
	
	public ArrayList<ContaPoupanca> getContaPoupanca() {
		return cp;
	}
	
	public void setContaPoupanca(ArrayList<ContaPoupanca> contaPoupanca) {
		this.cp = contaPoupanca;
	}
	
	public ArrayList<ContaInvestimento> getContaInvestimento() {
		return ci;
	}
	
	public void setContaInvestimento(ArrayList<ContaInvestimento> contaInvestimento) {
		this.ci = contaInvestimento;
	}
	
	private Object percorreContas(String numeroConta) {
		for(ContaCorrente contaCorrente : this.cc) {					
			if(contaCorrente.getNumeroConta().equals(numeroConta)) {
				return contaCorrente;
			}
		}
		
		for(ContaPoupanca contaPoupanca : this.cp) {
			if(contaPoupanca.getNumeroConta().equals(numeroConta)) {
				return contaPoupanca;
			}
		}
		
		for(ContaInvestimento contaInvestimento : this.ci) {
			if(contaInvestimento.getNumeroConta().equals(numeroConta)) {
				return contaInvestimento;
			}
		}
		
		System.out.println("\nNao existe conta com este numero");
		return null;
	}
	
	
	public void cadastrarConta(Integer tipo, String cliente, String numeroConta, BigDecimal saldo) {
		Scanner input = new Scanner(System.in);
		input.useLocale(Locale.US);
		
		tipoConta = tipo;
		
		if(tipo == 1) { //cc
			ContaCorrente checkingAccount = new ContaCorrente();
			checkingAccount.setCliente(cliente);
			checkingAccount.setNumeroConta(numeroConta);
			checkingAccount.setSaldo(saldo);
			System.out.printf("\nInsira o limite da conta corrente\n");
			BigDecimal limit = input.nextBigDecimal();
			checkingAccount.setLimite(limit);
			this.cc.add(checkingAccount);
			
		}else if(tipo == 2) { //cp
			ContaPoupanca savingsAccount = new ContaPoupanca();
			savingsAccount.setCliente(cliente);
			savingsAccount.setNumeroConta(numeroConta);
			savingsAccount.setSaldo(saldo);
			System.out.printf("\nInsira o rendimento diario da conta poupanca\n");
			Integer dailyIncome = input.nextInt();
			savingsAccount.setDiaRendimento(dailyIncome);
			this.cp.add(savingsAccount);
			input.nextLine();
			
		}else if(tipo == 3) { //ci
			ContaInvestimento investmentAccount = new ContaInvestimento();
			investmentAccount.setCliente(cliente);
			investmentAccount.setNumeroConta(numeroConta);
			investmentAccount.setSaldo(saldo);
			this.ci.add(investmentAccount);
			
		}
		
	}
	
	public void sacarValorConta(String numeroConta, BigDecimal saque) {
		Object conta = percorreContas(numeroConta);
		
		if(conta == null)
			return;
		
		if (conta instanceof ContaCorrente) 
			((ContaCorrente) conta).sacar(saque);
		
		
		if (conta instanceof ContaPoupanca) 
			((ContaPoupanca) conta).sacar(saque);
		
		
		if (conta instanceof ContaInvestimento) 
			((ContaInvestimento) conta).sacar(saque);
		
	}
	
	public void atualizarContaPoupanca(String numeroConta, BigDecimal taxaRendimento) {
		int comparador = 0;
		
		for(ContaPoupanca contaPoupanca : this.cp) {
			if(contaPoupanca.getNumeroConta().equals(numeroConta)) {
				contaPoupanca.calcularNovoSaldo(taxaRendimento);
				comparador = 1;
				break;
			}
		}
		
		if(comparador == 0) 
			System.out.println("\nNao existe conta poupanca com este numero de conta");
	}
	
	public void depositarValorConta(String numeroConta, BigDecimal deposito) {
		Object conta = percorreContas(numeroConta);
		
		if(conta == null)
			return;
		
		if (conta instanceof ContaCorrente) 
			((ContaCorrente) conta).depositar(deposito);
		
		
		if (conta instanceof ContaPoupanca) 
			((ContaPoupanca) conta).depositar(deposito);
		
		
		if (conta instanceof ContaInvestimento) 
			((ContaInvestimento) conta).depositar(deposito);
	}
	
	public void mostrarSaldoConta(String numeroConta) {
		Object conta = percorreContas(numeroConta);
		
		if(conta == null)
			return;
		
		if (conta instanceof ContaCorrente) {
			BigDecimal saldo = ((ContaCorrente) conta).getSaldo();
			System.out.printf("\nSaldo atual da conta corrente: %s\n", NumberFormat.getInstance(Locale.US).format(saldo));
		}
		
		
		if (conta instanceof ContaPoupanca) {
			BigDecimal saldo = ((ContaPoupanca) conta).getSaldo();
			System.out.printf("\nSaldo atual da conta poupanca: %s\n", NumberFormat.getInstance(Locale.US).format(saldo));
		}
		
		if (conta instanceof ContaInvestimento) {
			BigDecimal saldo = ((ContaInvestimento) conta).getSaldo();
			System.out.printf("\nSaldo atual da conta investimento: %s\n", NumberFormat.getInstance(Locale.US).format(saldo));
		}
		
	}
	
	public void calcularTributo(String numeroConta, BigDecimal taxaRendimento) {
		int comparador = 0;
		
		for(ContaInvestimento contaInvestimento : this.ci) {
			if(contaInvestimento.getNumeroConta().equals(numeroConta)) {
				BigDecimal lucro = contaInvestimento.calcularTributo(taxaRendimento);
				System.out.printf("\nTributo da conta investimento: %s\n", NumberFormat.getInstance(Locale.US).format(lucro));
				comparador = 1;
				break;
			}
		}
		
		if(comparador == 0) 
			System.out.println("\nNao existe conta investimento com este numero de conta");
	}
	
	public void calcularTaxaAdministracao(String numeroConta, BigDecimal taxaRendimento) {
		int comparador = 0;
		
		for(ContaInvestimento contaInvestimento : this.ci) {
			if(contaInvestimento.getNumeroConta().equals(numeroConta)) {
				BigDecimal lucro = contaInvestimento.calcularTaxaAdministracao(taxaRendimento);
				System.out.printf("\nTaxa de administracao da conta investimento: %s\n", NumberFormat.getInstance(Locale.US).format(lucro));
				comparador = 1;
				break;
			}
		}
		
		if(comparador == 0) 
			System.out.println("\nNao existe conta investimento com este numero de conta");
	}
	
}
