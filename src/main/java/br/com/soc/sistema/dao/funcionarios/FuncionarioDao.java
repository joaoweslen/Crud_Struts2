package br.com.soc.sistema.dao.funcionarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioDao extends Dao{
	public void insertFuncionario(FuncionarioVo funcionarioVo){
		StringBuilder query = new StringBuilder("INSERT INTO tb_funcionario (nm_funcionario) values (?)");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i=1;
			ps.setString(i++, funcionarioVo.getNome());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateFuncionario(FuncionarioVo funcionarioVo) {
		StringBuilder query = new StringBuilder("UPDATE tb_funcionario SET nm_funcionario = (?) ")
									.append("WHERE id_funcionario = (?)");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i=1;
			ps.setString(i++, funcionarioVo.getNome());
			ps.setString(i++, funcionarioVo.getRowid());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFuncionario(String codigo){
		StringBuilder query = new StringBuilder("DELETE FROM tb_funcionario WHERE id_funcionario = (?)");
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
	
	
	public List<FuncionarioVo> findAllFuncionario(){
		StringBuilder query = new StringBuilder("SELECT id_funcionario id, nm_funcionario nome FROM tb_funcionario");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()){
			
			FuncionarioVo vo =  null;
			List<FuncionarioVo> funcionarios = new ArrayList<>();
			while (rs.next()) {
				vo = new FuncionarioVo();
				vo.setRowid(rs.getString("id"));
				vo.setNome(rs.getString("nome"));	
				
				funcionarios.add(vo);
			}
			return funcionarios;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.emptyList();
	}
	
	public List<FuncionarioVo> findAllByNome(String nome){
		StringBuilder query = new StringBuilder("SELECT id_funcionario id, nm_funcionario nome FROM tb_funcionario ")
									.append("WHERE lower(nm_funcionario) like lower(?)");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setString(i, "%"+nome+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				FuncionarioVo vo =  null;
				List<FuncionarioVo> funcionarios = new ArrayList<>();
				
				while (rs.next()) {
					vo = new FuncionarioVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));	
					
					funcionarios.add(vo);
				}
				return funcionarios;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return Collections.emptyList();
	}
	
	public FuncionarioVo findByCodigo(Integer codigo){
		StringBuilder query = new StringBuilder("SELECT id_funcionario id, nm_funcionario nome FROM tb_funcionario ")
									.append("WHERE id_funcionario = ?");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setInt(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				FuncionarioVo vo =  null;
				
				while (rs.next()) {
					vo = new FuncionarioVo();
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
}
