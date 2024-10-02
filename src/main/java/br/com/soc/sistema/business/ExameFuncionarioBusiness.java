package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.examefuncionario.ExameFuncionarioDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.ExameFuncionarioFilter;
import br.com.soc.sistema.vo.ExameFuncionarioVo;

public class ExameFuncionarioBusiness {
	
	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private ExameFuncionarioDao dao;
	
	public ExameFuncionarioBusiness() {
		this.dao = new ExameFuncionarioDao();
	}
	
	public List<ExameFuncionarioVo> trazerTodosOsExamesFuncionarios(){
		return dao.findAllExameFuncionarios();
	}
	
	public void salvarExameFuncionario(ExameFuncionarioVo exameFuncionarioVo) {
		try {
			boolean exist = dao.findExameFucionarioExist(exameFuncionarioVo);
			
			if(!exist) {
				try {
					dao.insertExameFuncionario(exameFuncionarioVo);
				}catch (Exception e) {
					throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
				}	
			}
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
		
	}
	
	public void editarExameFuncionario(ExameFuncionarioVo exameFuncionarioVo) {
		try {
			boolean exist = dao.findExameFucionarioExist(exameFuncionarioVo);
			
			if(!exist) {
				try {
					dao.updateExameFuncionario(exameFuncionarioVo);
				}catch (Exception e) {
					throw new BusinessException("Nao foi possivel realizar a edicao do registro");
				}
				
			}else {
				System.out.println("Não editar");
			}
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a edicao do registro");
		}
	}	

	public void deletarExameFuncionario(String codigo) {
		try {
			dao.deleteExameFuncionario(codigo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel excluir o exame do funcionario");
		}
	}
	
	public void deletarExameFuncionarioPorFuncionario(String codigo) {
		try {
			dao.deleteExameFuncionarioByFuncionario(codigo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel excluir os exames do funcionario");
		}
	}
	
	public List<ExameFuncionarioVo> filtrarExames(ExameFuncionarioFilter filter){
		List<ExameFuncionarioVo> exameFuncionarios = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case ID:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					exameFuncionarios.add(dao.findByCodigo(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;

			case ID_FUNCIONARIO:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					exameFuncionarios.addAll(dao.findAllByIDFuncionario(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;
			
			case ID_EXAME:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					exameFuncionarios.addAll(dao.findAllByIDExame(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;
			
			case FUNCIONARIO:
				exameFuncionarios.addAll(dao.findAllByFuncionario(filter.getValorBusca()));
			break;
			
			case EXAME:
				exameFuncionarios.addAll(dao.findAllByExame(filter.getValorBusca()));
			break;
			
			case DATA:
				exameFuncionarios.addAll(dao.findAllByData(filter.getValorBusca()));
			break;
		}
		
		return exameFuncionarios;
	}

	public ExameFuncionarioVo buscarExameFuncionarioPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}


	
}
