package br.com.soc.sistema.infra;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.com.soc.sistema.exception.BusinessException;

public enum OpcoesComboBuscarExameFuncionario {
	ID("1", "ID"), 
	ID_FUNCIONARIO("2", "ID FUNCIONARIO"),
	ID_EXAME("3", "ID EXAME"),
	FUNCIONARIO("4", "FUNCIONARIO"),
	EXAME("5", "EXAME"),
	DATA("6", "DATA");
	
	private String codigo;
	private String descricao;
	private final static Map<String, OpcoesComboBuscarExameFuncionario> opcoes = new HashMap<>();
	
	static {
		Arrays.asList(OpcoesComboBuscarExameFuncionario.values())
		.forEach(
			opcao -> opcoes.put(opcao.getCodigo(), opcao)
		);
	}
	
	private OpcoesComboBuscarExameFuncionario(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static OpcoesComboBuscarExameFuncionario buscarPor(String codigo) throws IllegalArgumentException {
		if(codigo == null)
			throw new IllegalArgumentException("informe um codigo valido");
		
		OpcoesComboBuscarExameFuncionario opcao = getOpcao(codigo)
				.orElseThrow(() -> new BusinessException("Codigo informado nao existe"));
		
		return opcao;
	}
	
	private static Optional<OpcoesComboBuscarExameFuncionario> getOpcao(String codigo){
		return Optional.ofNullable(opcoes.get(codigo));
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
