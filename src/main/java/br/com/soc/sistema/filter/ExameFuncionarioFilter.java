package br.com.soc.sistema.filter;

import br.com.soc.sistema.infra.OpcoesComboBuscarExameFuncionario;

public class ExameFuncionarioFilter {
	private OpcoesComboBuscarExameFuncionario opcoesCombo;
	private String valorBusca;

	public String getValorBusca() {
		return valorBusca;
	}

	public ExameFuncionarioFilter setValorBusca(String valorBusca) {
		this.valorBusca = valorBusca;
		return this;
	}

	public OpcoesComboBuscarExameFuncionario getOpcoesCombo() {
		return opcoesCombo;
	}

	public ExameFuncionarioFilter setOpcoesCombo(String codigo) {
		this.opcoesCombo = OpcoesComboBuscarExameFuncionario.buscarPor(codigo);
		return this;
	}	
	
	public boolean isNullOpcoesCombo() {
		return this.getOpcoesCombo() == null;
	}
	
	public static ExameFuncionarioFilter builder() {
		return new ExameFuncionarioFilter();
	}
}
