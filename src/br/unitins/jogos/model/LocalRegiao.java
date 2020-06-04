package br.unitins.jogos.model;

public enum LocalRegiao {
	
	NAO_DEFINIDO(0, "Selecione uma regi�o..."),
	BRASIL(1, "Brasil"), 
	ESPANHA(2, "Espanha"),
	FRANCA(3, "Fran�a"),
	JAPAO(4, "Jap�o");
	
	private int id;
	private String label;
	
	private LocalRegiao(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static LocalRegiao valueOf(int valor) {
		for (LocalRegiao localRegiao : LocalRegiao.values()) {
			if (valor == localRegiao.getId())
				return localRegiao;
		} 
		return null;
	}
	
}
