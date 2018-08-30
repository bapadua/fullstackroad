package br.com.ionic.api.domain.enums;

public enum TipoCliente {
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String desc;
	
	TipoCliente(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public String getDesc() {
		return this.desc;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(TipoCliente t : TipoCliente.values()) {
			if(t.getCod() == t.getCod()) {
				return t;
			}
		}
		 throw new IllegalArgumentException("Tipo inválido: cod " + cod);
	}

}
