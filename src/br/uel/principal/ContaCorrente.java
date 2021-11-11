package br.uel.principal;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class ContaCorrente implements ContaBancaria {
	protected String cliente;
	protected String numeroConta;
	protected BigDecimal saldo;
	protected BigDecimal limite;
	
	protected String getCliente() {
		return cliente;
	}
	
	protected void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	protected String getNumeroConta() {
		return numeroConta;
	}
	
	protected void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	
	protected BigDecimal getSaldo() {
		return saldo;
	}
	
	protected void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	protected BigDecimal getLimite() {
		return limite;
	}
	
	protected void setLimite(BigDecimal limite) {
		this.limite = limite;
	}
	
	@Override
	public void sacar(BigDecimal saque) {
		BigDecimal menosUm = new BigDecimal(-1);
		BigDecimal limitante = this.limite.multiply(menosUm);
		BigDecimal soma = new BigDecimal(0);
		soma = this.saldo.subtract(saque);
		if(soma.compareTo(limitante) >= 0) {
			this.saldo = this.saldo.subtract(saque);
			System.out.printf("\nSaque de %s realizado, restam %s reais na conta\n", NumberFormat.getInstance(Locale.US).format(saque), NumberFormat.getInstance(Locale.US).format(this.saldo));
		}else {
			System.out.println("\nSaldo e limite insuficientes");
		}
	}

	@Override
	public void depositar(BigDecimal deposito) {
		this.saldo = this.saldo.add(deposito);
		System.out.printf("\nDeposito de %s realizado, totalizando %s reais na conta\n", NumberFormat.getInstance(Locale.US).format(deposito), NumberFormat.getInstance(Locale.US).format(this.saldo));
	}
	
}
