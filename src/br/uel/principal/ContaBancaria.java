package br.uel.principal;

import java.math.BigDecimal;

public interface ContaBancaria {
	void sacar(BigDecimal saque);
	void depositar(BigDecimal deposito);
}
