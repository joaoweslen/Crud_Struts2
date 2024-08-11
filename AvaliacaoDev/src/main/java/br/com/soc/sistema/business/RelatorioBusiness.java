package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.dao.relatorio.RelatorioDao;
import br.com.soc.sistema.service.POIXLS;
import br.com.soc.sistema.vo.ExameFuncionarioVo;
import br.com.soc.sistema.vo.RelatorioVo;

public class RelatorioBusiness {
	private RelatorioDao dao;
	private POIXLS poi;
	
	public RelatorioBusiness() {
		this.dao = new RelatorioDao();
		this.poi = new POIXLS();
	}

	public List<ExameFuncionarioVo> buscarExamesPorPeriodo(RelatorioVo relatorioVo) {
		return dao.findExameFuncionariosByPeriod(relatorioVo);
	}
	
	public void gerarRelatorio(List<ExameFuncionarioVo> examesRelatorio) {
		poi.criarRelatorioXLS(examesRelatorio);
	}
}
