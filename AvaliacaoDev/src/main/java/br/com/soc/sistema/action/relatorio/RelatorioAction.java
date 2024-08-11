package br.com.soc.sistema.action.relatorio;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.RelatorioBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.ExameFuncionarioVo;
import br.com.soc.sistema.vo.RelatorioVo;

public class RelatorioAction extends Action{
	private List<ExameFuncionarioVo> relatorios = new ArrayList<>();
	private RelatorioBusiness business = new RelatorioBusiness();
	private RelatorioVo relatorioVo = new RelatorioVo();
	
	public String browser() {
		
		return SUCCESS;
	}
	
	public String buscar() {
		relatorios = business.buscarExamesPorPeriodo(relatorioVo);
		
		return SUCCESS;
	}
	
	public String criar() {
		relatorios = business.buscarExamesPorPeriodo(relatorioVo);
		business.gerarRelatorio(relatorios);
	
		return SUCCESS;
	}
	
	public void validate() {
		try {
			relatorioVo.getDataInicial();
		} catch (Exception e) {
			addFieldError("relatorioVo.dataInicial", "Data Inicial não pode ser em branco");
		}
			
		try {
			relatorioVo.getDataFinal();
		} catch (Exception e) {
			addFieldError("relatorioVo.dataFinal", "Data Final não pode ser em branco");
		}
	}
	
	public List<ExameFuncionarioVo> getRelatorios() {
		return relatorios;
	}

	public void setRelatorios(List<ExameFuncionarioVo> relatorios) {
		this.relatorios = relatorios;
	}
	
	public RelatorioVo getRelatorioVo() {
		return relatorioVo;
	}

	public void setRelatorioVo(RelatorioVo relatorioVo) {
		this.relatorioVo = relatorioVo;
	}
}
