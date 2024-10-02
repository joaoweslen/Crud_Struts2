package br.com.soc.sistema.dao.examefuncionario;

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

public class ExameFuncionarioDao extends Dao {
	public void insertExameFuncionario(ExameFuncionarioVo exameFuncionarioVo) {
		StringBuilder query = new StringBuilder("insert into tb_exame_funcionario(id_funcionario,")
										.append("id_exame,dt_exame_funcionario) value (?,?,?)");
		try (
			Connection con = getConexao(); 
			PreparedStatement ps = con.prepareStatement(query.toString())) {

			int i = 1;
			ps.setString(i++, exameFuncionarioVo.getFuncionario().getRowid());
			ps.setString(i++, exameFuncionarioVo.getExame().getRowid());
			ps.setString(i++, exameFuncionarioVo.getData());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateExameFuncionario(ExameFuncionarioVo exameFuncionarioVo) {
		StringBuilder query = new StringBuilder("UPDATE tb_exame_funcionario SET id_funcionario = ?,")
										.append("id_exame = ?, dt_exame_funcionario = ? ")
										.append("WHERE id_exame_funcionario = ?");
		try(
			Connection con = getConexao(); 
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i =1;
			ps.setString(i++, exameFuncionarioVo.getFuncionario().getRowid());
			ps.setString(i++, exameFuncionarioVo.getExame().getRowid());
			ps.setString(i++, exameFuncionarioVo.getData());
			ps.setString(i++, exameFuncionarioVo.getRowid());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteExameFuncionario(String codigo) {
		StringBuilder query = new StringBuilder("DELETE FROM tb_exame_funcionario ")
				.append("WHERE id_exame_funcionario = (?)");

		try (
			Connection con = getConexao(); 
			PreparedStatement ps = con.prepareStatement(query.toString())) {

			int i = 1;
			ps.setString(i++, codigo);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteExameFuncionarioByFuncionario(String codigo) {
		StringBuilder query = new StringBuilder("DELETE FROM tb_exame_funcionario ")
										.append("WHERE id_funcionario = (?)");

		try (
			Connection con = getConexao(); 
			PreparedStatement ps = con.prepareStatement(query.toString())) {

			int i = 1;
			ps.setString(i++, codigo);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ExameFuncionarioVo> findAllExameFuncionarios() {
		StringBuilder query = new StringBuilder("SELECT id_exame_funcionario id, e.id_exame, e.nm_exame,")
										.append("f.id_funcionario, f.nm_funcionario, dt_exame_funcionario data ")
										.append("FROM  tb_exame_funcionario ef ")
										.append("JOIN tb_exame e ON ef.id_exame = e.id_exame ")
										.append("JOIN tb_funcionario f ON ef.id_funcionario = f.id_funcionario;");
		
		try (
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()) {

			ExameFuncionarioVo vo = null;
			FuncionarioVo funcionario = null;
			ExameVo exame = null;

			List<ExameFuncionarioVo> exameFuncionario = new ArrayList<>();
			while (rs.next()) {
				vo = new ExameFuncionarioVo();
				funcionario = new FuncionarioVo();
				exame = new ExameVo();
				
				funcionario.setRowid(rs.getString("id_funcionario"));
				funcionario.setNome(rs.getString("nm_funcionario"));
				
				exame.setRowid(rs.getString("id_exame"));
				exame.setNome(rs.getString("nm_exame"));
				
				vo.setRowid(rs.getString("id"));
				vo.setExame(exame);
				vo.setFuncionario(funcionario);
				vo.setData(rs.getDate("data"));

				exameFuncionario.add(vo);
			}
			return exameFuncionario;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public ExameFuncionarioVo findByCodigo(Integer codigo) {
		StringBuilder query = new StringBuilder("SELECT id_exame_funcionario id, e.id_exame, e.nm_exame,")
										.append("f.id_funcionario, f.nm_funcionario, dt_exame_funcionario data ")
										.append("FROM  tb_exame_funcionario ef ")
										.append("JOIN tb_exame e ON ef.id_exame = e.id_exame ")
										.append("JOIN tb_funcionario f ON ef.id_funcionario = f.id_funcionario ")
										.append("WHERE id_exame_funcionario = ?");

		try (Connection con = getConexao(); 
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			int i = 1;
			ps.setInt(i, codigo);

			try (ResultSet rs = ps.executeQuery()) {
				ExameFuncionarioVo vo = null;
				FuncionarioVo funcionario = null;
				ExameVo exame = null;
				
				while (rs.next()) {
					vo = new ExameFuncionarioVo();
					funcionario = new FuncionarioVo();
					exame = new ExameVo();
					
					funcionario.setRowid(rs.getString("id_funcionario"));
					funcionario.setNome(rs.getString("nm_funcionario"));
					
					exame.setRowid(rs.getString("id_exame"));
					exame.setNome(rs.getString("nm_exame"));
					
					vo.setRowid(rs.getString("id"));
					vo.setExame(exame);
					vo.setFuncionario(funcionario);
					vo.setData(rs.getDate("data"));
				}
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ExameFuncionarioVo> findAllByIDFuncionario(Integer idFuncionario) {
		StringBuilder query = new StringBuilder("SELECT id_exame_funcionario id, e.id_exame, e.nm_exame,")
										.append("f.id_funcionario, f.nm_funcionario, dt_exame_funcionario data ")
										.append("FROM  tb_exame_funcionario ef ")
										.append("JOIN tb_exame e ON ef.id_exame = e.id_exame ")
										.append("JOIN tb_funcionario f ON ef.id_funcionario = f.id_funcionario ")
										.append("WHERE ef.id_funcionario = ?");
		
		try (
			Connection con = getConexao(); 
			PreparedStatement ps = con.prepareStatement(query.toString())) {
				
			int i = 1;
			ps.setInt(i, idFuncionario);

			try (ResultSet rs = ps.executeQuery()) {
				ExameFuncionarioVo vo = null;
				FuncionarioVo funcionario = null;
				ExameVo exame = null;
				List<ExameFuncionarioVo> exameFuncionario = new ArrayList<>();
				
				while (rs.next()) {
					vo = new ExameFuncionarioVo();
					funcionario = new FuncionarioVo();
					exame = new ExameVo();
						
					funcionario.setRowid(rs.getString("id_funcionario"));
					funcionario.setNome(rs.getString("nm_funcionario"));
						
					exame.setRowid(rs.getString("id_exame"));
					exame.setNome(rs.getString("nm_exame"));
						
					vo.setRowid(rs.getString("id"));
					vo.setExame(exame);
					vo.setFuncionario(funcionario);
					vo.setData(rs.getDate("data"));
					
					exameFuncionario.add(vo);
				}
				return exameFuncionario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public List<ExameFuncionarioVo> findAllByIDExame(Integer idExame) {
		StringBuilder query = new StringBuilder("SELECT id_exame_funcionario id, e.id_exame, e.nm_exame,")
										.append("f.id_funcionario, f.nm_funcionario, dt_exame_funcionario data ")
										.append("FROM  tb_exame_funcionario ef ")
										.append("JOIN tb_exame e ON ef.id_exame = e.id_exame ")
										.append("JOIN tb_funcionario f ON ef.id_funcionario = f.id_funcionario ")
										.append("WHERE ef.id_exame = ?");
		try (
			Connection con = getConexao(); 
			PreparedStatement ps = con.prepareStatement(query.toString())) {
					
			int i = 1;
			ps.setInt(i, idExame);

			try (ResultSet rs = ps.executeQuery()) {
				ExameFuncionarioVo vo = null;
				FuncionarioVo funcionario = null;
				ExameVo exame = null;
				List<ExameFuncionarioVo> exameFuncionario = new ArrayList<>();
					
				while (rs.next()) {
					vo = new ExameFuncionarioVo();
					funcionario = new FuncionarioVo();
					exame = new ExameVo();
							
					funcionario.setRowid(rs.getString("id_funcionario"));
					funcionario.setNome(rs.getString("nm_funcionario"));
							
					exame.setRowid(rs.getString("id_exame"));
					exame.setNome(rs.getString("nm_exame"));
							
					vo.setRowid(rs.getString("id"));
					vo.setExame(exame);
					vo.setFuncionario(funcionario);
					vo.setData(rs.getDate("data"));
						
					exameFuncionario.add(vo);
				}
				return exameFuncionario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public List<ExameFuncionarioVo> findAllByFuncionario(String nome) {
		StringBuilder query = new StringBuilder("SELECT id_exame_funcionario id, e.id_exame, e.nm_exame,")
										.append("f.id_funcionario, f.nm_funcionario, dt_exame_funcionario data ")
										.append("FROM  tb_exame_funcionario ef ")
										.append("JOIN tb_exame e ON ef.id_exame = e.id_exame ")
										.append("JOIN tb_funcionario f ON ef.id_funcionario = f.id_funcionario ")
										.append("WHERE lower(f.nm_funcionario) like lower(?)");
		try (
			Connection con = getConexao(); 
			PreparedStatement ps = con.prepareStatement(query.toString())) {
					
			int i = 1;
			ps.setString(i, "%"+nome+"%");

			try (ResultSet rs = ps.executeQuery()) {
				ExameFuncionarioVo vo = null;
				FuncionarioVo funcionario = null;
				ExameVo exame = null;
				List<ExameFuncionarioVo> exameFuncionario = new ArrayList<>();
					
				while (rs.next()) {
					vo = new ExameFuncionarioVo();
					funcionario = new FuncionarioVo();
					exame = new ExameVo();
							
					funcionario.setRowid(rs.getString("id_funcionario"));
					funcionario.setNome(rs.getString("nm_funcionario"));
							
					exame.setRowid(rs.getString("id_exame"));
					exame.setNome(rs.getString("nm_exame"));
							
					vo.setRowid(rs.getString("id"));
					vo.setExame(exame);
					vo.setFuncionario(funcionario);
					vo.setData(rs.getDate("data"));
						
					exameFuncionario.add(vo);
				}
				return exameFuncionario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public List<ExameFuncionarioVo> findAllByExame(String nome) {
		StringBuilder query = new StringBuilder("SELECT id_exame_funcionario id, e.id_exame, e.nm_exame,")
										.append("f.id_funcionario, f.nm_funcionario, dt_exame_funcionario data ")
										.append("FROM  tb_exame_funcionario ef ")
										.append("JOIN tb_exame e ON ef.id_exame = e.id_exame ")
										.append("JOIN tb_funcionario f ON ef.id_funcionario = f.id_funcionario ")
										.append("WHERE lower(e.nm_exame) like lower(?)");
		try (
			Connection con = getConexao(); 
			PreparedStatement ps = con.prepareStatement(query.toString())) {
					
			int i = 1;
			ps.setString(i, "%"+nome+"%");

			try (ResultSet rs = ps.executeQuery()) {
				ExameFuncionarioVo vo = null;
				FuncionarioVo funcionario = null;
				ExameVo exame = null;
				List<ExameFuncionarioVo> exameFuncionario = new ArrayList<>();
					
				while (rs.next()) {
					vo = new ExameFuncionarioVo();
					funcionario = new FuncionarioVo();
					exame = new ExameVo();
							
					funcionario.setRowid(rs.getString("id_funcionario"));
					funcionario.setNome(rs.getString("nm_funcionario"));
							
					exame.setRowid(rs.getString("id_exame"));
					exame.setNome(rs.getString("nm_exame"));
							
					vo.setRowid(rs.getString("id"));
					vo.setExame(exame);
					vo.setFuncionario(funcionario);
					vo.setData(rs.getDate("data"));
						
					exameFuncionario.add(vo);
				}
				return exameFuncionario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public List<ExameFuncionarioVo> findAllByData(String data) {
		StringBuilder query = new StringBuilder("SELECT id_exame_funcionario id, e.id_exame, e.nm_exame,")
										.append("f.id_funcionario, f.nm_funcionario, dt_exame_funcionario data ")
										.append("FROM  tb_exame_funcionario ef ")
										.append("JOIN tb_exame e ON ef.id_exame = e.id_exame ")
										.append("JOIN tb_funcionario f ON ef.id_funcionario = f.id_funcionario ")
										.append("WHERE dt_exame_funcionario = ?");
		try (
			Connection con = getConexao(); 
			PreparedStatement ps = con.prepareStatement(query.toString())) {
					
			int i = 1;
			ps.setString(i, data);

			try (ResultSet rs = ps.executeQuery()) {
				ExameFuncionarioVo vo = null;
				FuncionarioVo funcionario = null;
				ExameVo exame = null;
				List<ExameFuncionarioVo> exameFuncionario = new ArrayList<>();
					
				while (rs.next()) {
					vo = new ExameFuncionarioVo();
					funcionario = new FuncionarioVo();
					exame = new ExameVo();
							
					funcionario.setRowid(rs.getString("id_funcionario"));
					funcionario.setNome(rs.getString("nm_funcionario"));
							
					exame.setRowid(rs.getString("id_exame"));
					exame.setNome(rs.getString("nm_exame"));
							
					vo.setRowid(rs.getString("id"));
					vo.setExame(exame);
					vo.setFuncionario(funcionario);
					vo.setData(rs.getDate("data"));
						
					exameFuncionario.add(vo);
				}
				return exameFuncionario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public boolean findExameFucionarioExist(ExameFuncionarioVo exameFuncionarioVo) {
		StringBuilder query = new StringBuilder("SELECT 1 FROM tb_exame_funcionario ")
										.append("WHERE id_funcionario = ? AND id_exame = ? ")
										.append("AND dt_exame_funcionario = ?");
		
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;
				
			ps.setString(i++, exameFuncionarioVo.getFuncionario().getRowid());
			ps.setString(i++, exameFuncionarioVo.getExame().getRowid());
			ps.setString(i++, exameFuncionarioVo.getData());
			
			try(ResultSet rs = ps.executeQuery()){
				while (rs.next()) {
					return rs.getBoolean(1);
				}
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return false;
	}


}
