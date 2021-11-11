package br.uel.principal;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class ContaInvestimento implements ContaBancaria, Tributavel {
	protected String cliente;
	protected String numeroConta;
	protected BigDecimal saldo;
	
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

	@Override
	public void sacar(BigDecimal saque) {
		if(this.saldo.compareTo(saque) >= 0) {
			this.saldo = this.saldo.subtract(saque);
			System.out.printf("\nSaque de %s realizado, restam %s reais na conta\n", NumberFormat.getInstance(Locale.US).format(saque), NumberFormat.getInstance(Locale.US).format(this.saldo));
		}else {
			System.out.println("\nSaldo insuficiente");
		}
	}

	@Override
	public void depositar(BigDecimal deposito) {
		this.saldo = this.saldo.add(deposito);
		System.out.printf("\nDeposito de %s realizado, totalizando %s reais na conta\n", NumberFormat.getInstance(Locale.US).format(deposito), NumberFormat.getInstance(Locale.US).format(this.saldo));
	}
	
	@Override
	public BigDecimal calcularTributo(BigDecimal taxaRendimento) {
		BigDecimal rendimento = this.saldo.multiply(taxaRendimento);
		BigDecimal lucro = rendimento.multiply(BigDecimal.valueOf(0.005));
		return lucro;
		
	}
	
	protected BigDecimal calcularTaxaAdministracao(BigDecimal taxaRendimento) {
		BigDecimal rendimento = this.saldo.multiply(taxaRendimento);
		BigDecimal lucro = rendimento.multiply(BigDecimal.valueOf(0.01));
		return lucro;
	}

	protected void calcularNovoSaldo(BigDecimal taxaRendimento) {
		BigDecimal rendimento = this.saldo.multiply(taxaRendimento);
		this.saldo = this.saldo.add(rendimento);
	}
	
}
