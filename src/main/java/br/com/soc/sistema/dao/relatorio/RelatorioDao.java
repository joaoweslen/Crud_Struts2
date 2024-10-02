package br.com.soc.sistema.dao.relatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.ExameFuncionarioVo;
import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.FuncionarioVo;
import br.com.soc.sistema.vo.RelatorioVo;

public class RelatorioDao extends Dao{

	public List<ExameFuncionarioVo> findExameFuncionariosByPeriod(RelatorioVo relatorioVo) {
		StringBuilder query = new StringBuilder("SELECT e.id_exame, e.nm_exame, f.id_funcionario,")
										.append("f.nm_funcionario, dt_exame_funcionario data ")
										.append("FROM  tb_exame_funcionario ef ")
										.append("JOIN tb_exame e ON ef.id_exame = e.id_exame ")
										.append("JOIN tb_funcionario f ON ef.id_funcionario = f.id_funcionario ")
										.append("WHERE dt_exame_funcionario BETWEEN ? AND ?;");
		try (
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i = 1;
			ps.setString(i++, relatorioVo.getDataInicial());
			ps.setString(i++, relatorioVo.getDataFinal());
			
			try(ResultSet rs = ps.executeQuery()){
				ExameFuncionarioVo vo = null;
				FuncionarioVo funcionario = null;
				ExameVo exame = null;
				
				List<ExameFuncionarioVo> relatorios = new ArrayList<>();
				while (rs.next()) {
					vo = new ExameFuncionarioVo();
					funcionario = new FuncionarioVo();
					exame = new ExameVo();
					
					funcionario.setRowid(rs.getString("id_funcionario"));
					funcionario.setNome(rs.getString("nm_funcionario"));
					
					exame.setRowid(rs.getString("id_exame"));
					exame.setNome(rs.getString("nm_exame"));
					
					vo.setExame(exame);
					vo.setFuncionario(funcionario);
					vo.setData(rs.getDate("data"));

					relatorios.add(vo);
				}
				return relatorios;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

}
