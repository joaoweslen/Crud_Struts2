package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.soc.sistema.exception.TechnicalException;

public abstract class Dao implements AutoCloseable{

	private final String USER = "root";
	private final String PASSWORD = "";
	private Connection con = null;
	
	public Dao() {
		conectar();
	}
	
	private void conectar() {
		StringBuilder urlBuilder = new StringBuilder("jdbc:mysql://localhost/db_exames")
											 .append("?useTimezone=true");
		
		 try {	 
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection(urlBuilder.toString(), USER, PASSWORD);
        } catch (SQLException ex) {
        	ex.printStackTrace();
            throw new TechnicalException("Ocorreu um problema na tentativa de conexao", ex);
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}	
	
	private void fechar() throws SQLException {
		if(con == null) {
			System.out.println("Conexao nao foi criada");
			throw new TechnicalException("Conexao nao foi criada");}
		
		if(con.isClosed()) {
			System.out.println("Conexao ja foi encerrada");
			throw new TechnicalException("Conexao ja foi encerrada");}
		
		System.out.println("Fechei XD");
		con.close();
	}

	@Override
	public void close() throws Exception {
		try{
			fechar();		
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * retorna uma conexao
	 * @return
	 * @throws SQLException 
	 */
	protected Connection getConexao() throws SQLException {
		if(con == null || con.isClosed())
			conectar();
		return con;
	}	
}
