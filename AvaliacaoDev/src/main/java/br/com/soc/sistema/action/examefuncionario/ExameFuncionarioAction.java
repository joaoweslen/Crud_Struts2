package br.com.soc.sistema.action.examefuncionario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.ExameFuncionarioBusiness;
import br.com.soc.sistema.filter.ExameFuncionarioFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarExameFuncionario;
import br.com.soc.sistema.vo.ExameFuncionarioVo;

public class ExameFuncionarioAction extends Action{
	private List<ExameFuncionarioVo> exameFuncionarios = new ArrayList<>();
	private ExameFuncionarioBusiness business = new ExameFuncionarioBusiness();
	private ExameFuncionarioFilter filtrar = new ExameFuncionarioFilter();
	private ExameFuncionarioVo exameFuncionarioVo = new ExameFuncionarioVo();
	
	public String todos() {
		exameFuncionarios.addAll(business.trazerTodosOsExamesFuncionarios());

		return SUCCESS;
	}
	
	public String filtrar() {
		if(filtrar.isNullOpcoesCombo())
			return REDIRECT;
		
		exameFuncionarios = business.filtrarExames(filtrar);
	
		return SUCCESS;
	}
	
	public String novo() {
		if(exameFuncionarioVo.getRowid() == null)
			return INPUT;
		
		business.salvarExameFuncionario(exameFuncionarioVo);
		
		return REDIRECT;
	}
	
	public String deletar() {
		business.deletarExameFuncionario(exameFuncionarioVo.getRowid());
		
		return REDIRECT;
	}
	
	public String editar() {
		exameFuncionarioVo = business.buscarExameFuncionarioPor(exameFuncionarioVo.getRowid());
		
		return ALTER;
	}
	
	public String salvarEdicao() {
		business.editarExameFuncionario(exameFuncionarioVo);
		
		return REDIRECT;
	}
	
	public void validate() {
		if(exameFuncionarioVo.getRowid() != null) {
			try {
				if(exameFuncionarioVo.getFuncionario().getRowid().length() != 0) {
					try {
						Integer.parseInt(exameFuncionarioVo.getFuncionario().getRowid());
					} catch (NumberFormatException e) {
						addFieldError("exameFuncionarioVo.funcionario.rowid", "Código funcionário precisa ser um número");
					}
				}else {
					addFieldError("exameFuncionarioVo.funcionario.rowid", "Código funcionário não pode ser em branco");
				}
				
				if(exameFuncionarioVo.getExame().getRowid().length() != 0) {
					try {
						Integer.parseInt(exameFuncionarioVo.getExame().getRowid());
					} catch (NumberFormatException e) {
						addFieldError("exameFuncionarioVo.exame.rowid", "Código exame precisa ser um número");
					}
				}else {
					addFieldError("exameFuncionarioVo.exame.rowid", "Código exame não pode ser em branco");
				}

				try {
					exameFuncionarioVo.getData();
				} catch (Exception e) {
					addFieldError("exameFuncionarioVo.data", "Data não pode ser em branco");
				}
			}catch(Exception e) {
				e.getMessage();
			}
		}
	}
	
	public List<OpcoesComboBuscarExameFuncionario> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscarExameFuncionario.values());
	}
	
	public List<ExameFuncionarioVo> getExameFuncionarios() {
		return exameFuncionarios;
	}
	
	public void setExameFuncionarios(List<ExameFuncionarioVo> exameFuncionarios) {
		this.exameFuncionarios = exameFuncionarios;
	}
	
	public ExameFuncionarioFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(ExameFuncionarioFilter filtrar) {
		this.filtrar = filtrar;
	}
	
	public ExameFuncionarioVo getExameFuncionarioVo() {
		return exameFuncionarioVo;
	}

	public void setExameFuncionarioVo(ExameFuncionarioVo exameFuncionarioVo) {
		this.exameFuncionarioVo = exameFuncionarioVo;
	}
}
