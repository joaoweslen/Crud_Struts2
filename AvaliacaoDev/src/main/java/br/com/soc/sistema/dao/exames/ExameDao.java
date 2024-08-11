package br.com.soc.sistema.dao.exames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.ExameVo;

public class ExameDao extends Dao {
	
	public void insertExame(ExameVo exameVo){
		StringBuilder query = new StringBuilder("INSERT INTO tb_exame (nm_exame) values (?)");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i=1;
			ps.setString(i++, exameVo.getNome());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateExame(ExameVo exameVo) {
		StringBuilder query = new StringBuilder("UPDATE tb_exame SET nm_exame = (?) WHERE id_exame = (?)");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i=1;
			ps.setString(i++, exameVo.getNome());
			ps.setString(i++, exameVo.getRowid());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteExame(String codigo){
		StringBuilder query = new StringBuilder("DELETE FROM tb_exame WHERE id_exame = ?");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i=1;
			ps.setString(i++, codigo);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<ExameVo> findAllExames(){
		StringBuilder query = new StringBuilder("SELECT id_exame id, nm_exame nome FROM tb_exame");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()){
			
			ExameVo vo =  null;
			List<ExameVo> exames = new ArrayList<>();
			while (rs.next()) {
				vo = new ExameVo();
				vo.setRowid(rs.getString("id"));
				vo.setNome(rs.getString("nome"));	
				
				exames.add(vo);
			}
			return exames;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.emptyList();
	}
	
	public List<ExameVo> findAllByNome(String nome){
		StringBuilder query = new StringBuilder("SELECT id_exame id, nm_exame nome FROM tb_exame ")
										.append("WHERE lower(nm_exame) like lower(?)");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setString(i, "%"+nome+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				ExameVo vo =  null;
				List<ExameVo> exames = new ArrayList<>();
				
				while (rs.next()) {
					vo = new ExameVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));	
					
					exames.add(vo);
				}
				return exames;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return Collections.emptyList();
	}
	
	public ExameVo findByCodigo(Integer codigo){
		StringBuilder query = new StringBuilder("SELECT id_exame id, nm_exame nome FROM tb_exame ")
										.append("WHERE id_exame = ?");
		
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setInt(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				ExameVo vo =  null;
				
				while (rs.next()) {
					vo = new ExameVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));	
				}
				return vo;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	public boolean findExistInExameFucionario(String codigo) {
		StringBuilder query = new StringBuilder("SELECT 1 FROM tb_exame_funcionario ")
										.append("WHERE id_exame = ? LIMIT 1");
		
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;
			
			ps.setString(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				while (rs.next()) {
					return rs.getBoolean(i);
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