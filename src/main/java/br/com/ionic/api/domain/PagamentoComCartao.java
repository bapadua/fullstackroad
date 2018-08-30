package br.com.ionic.api.domain;

import javax.persistence.Entity;

import br.com.ionic.api.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;

	private Integer numeroParcelas;

	public PagamentoComCartao() {
	}

	public PagamentoComCartao(Integer id, EstadoPagamento pagamento, Pedido pedido, Integer parcelas) {
		super(id, pagamento, pedido);
		this.numeroParcelas = parcelas;

	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	
}
