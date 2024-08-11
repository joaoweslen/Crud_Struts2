package br.com.soc.sistema.action.funcionario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarFuncionarios;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioAction extends Action {
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private FuncionarioBusiness business = new FuncionarioBusiness();
	private FuncionarioFilter filtrar = new FuncionarioFilter();
	private FuncionarioVo funcionarioVo = new FuncionarioVo();
	
	public String todos() {
		funcionarios.addAll(business.trazerTodosOsFuncionarios());	

		return SUCCESS;
	}
	
	public String filtrar() {
		if(filtrar.isNullOpcoesCombo())
			return REDIRECT;
		
		funcionarios = business.filtrarFuncionarios(filtrar);
		
		return SUCCESS;
	}
	
	public String novo() {
		if(funcionarioVo.getNome() == null) {
			return INPUT;}
		
		business.salvarFuncionario(funcionarioVo);
		
		return REDIRECT;
	}
	
	public String deletar() {
		business.deletarFuncionario(funcionarioVo.getRowid());
		
		return REDIRECT;
	}
	
	public String editar() {
		funcionarioVo = business.buscarFuncionarioPor(funcionarioVo.getRowid());
		
		return ALTER;
	}
	
	public String salvarEdicao() {
		business.editarFuncionario(funcionarioVo);
		
		return REDIRECT;
	}
	
	public void validate() {
		if(funcionarioVo.getNome() != null) {
			if (funcionarioVo.getNome().length() == 0) {
		        addFieldError("funcionarioVo.nome", "Nome não pode ser em branco");
		    }
		}
	}
	
	public List<OpcoesComboBuscarFuncionarios> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscarFuncionarios.values());
	}
	
	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}

	public void setExames(List<FuncionarioVo> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public FuncionarioFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(FuncionarioFilter filtrar) {
		this.filtrar = filtrar;
	}

	public FuncionarioVo getFuncionarioVo() {
		return funcionarioVo;
	}

	public void setFuncionarioVo(FuncionarioVo funcionarioVo) {
		this.funcionarioVo = funcionarioVo;
	}
}
