package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.funcionarios.FuncionarioDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioBusiness {
	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private FuncionarioDao dao;
	private ExameFuncionarioBusiness business;
	
	public FuncionarioBusiness() {
		this.dao = new FuncionarioDao();
		this.business = new ExameFuncionarioBusiness();
	}
	
	public List<FuncionarioVo> trazerTodosOsFuncionarios(){
		return dao.findAllFuncionario();
	}	
	
	public void salvarFuncionario(FuncionarioVo funcionarioVo) {
		try {
			if(funcionarioVo.getNome().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");
			
			dao.insertFuncionario(funcionarioVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
		
	}	
	
	public void deletarFuncionario(String codigo) {
		try {
			business.deletarExameFuncionarioPorFuncionario(codigo);
			try {
				dao.deleteFuncionario(codigo);
			} catch (Exception e) {
				throw new BusinessException("Nao foi possivel excluir o funcionario");
			}
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel chamar a exclusão dos exames do funcionario");
		}
		
	}
	
	public void editarFuncionario(FuncionarioVo funcionarioVo) {
		try {
			if(funcionarioVo.getNome().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");
			
			dao.updateFuncionario(funcionarioVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a edição do registro");
		}
		
	}
	
	
	public List<FuncionarioVo> filtrarFuncionarios(FuncionarioFilter filter){
		List<FuncionarioVo> exames = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case ID:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					exames.add(dao.findByCodigo(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;

			case NOME:
				exames.addAll(dao.findAllByNome(filter.getValorBusca()));
			break;
		}
		
		return exames;
	}
	
	public FuncionarioVo buscarFuncionarioPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
}
