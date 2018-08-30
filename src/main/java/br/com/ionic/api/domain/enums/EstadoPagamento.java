package br.com.ionic.api.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String status;
	
	private EstadoPagamento(int cod, String status) {
		this.cod = cod;
		this.status = status;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(EstadoPagamento e : EstadoPagamento.values()) {
			if(cod.equals(e.getCod())) {
				return e;
			}
		}
		
		throw new IllegalArgumentException("Codigo de pagamento inválido");
	}
	
	
}
