package br.com.soc.sistema.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExameFuncionarioVo {
	private String rowid;
	private ExameVo exame;
	private FuncionarioVo funcionario;
	private Date data;
	
	private static final SimpleDateFormat sdfDateISO= new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat sdfDateLOC= new SimpleDateFormat("dd/MM/yyyy");
	
	public ExameFuncionarioVo() {};
	
	public ExameFuncionarioVo(String rowid, ExameVo exame, FuncionarioVo funcionario, Date data) {
		this.rowid = rowid;
		this.exame = exame;
		this.funcionario = funcionario;
		this.data = data;
	}

	public String getRowid() {
		return rowid;
	}
	
	public void setRowid(String rowid) {
		this.rowid = rowid;
	}
	
	public ExameVo getExame() {
		return exame;
	}
	
	public void setExame(ExameVo exame) {
		this.exame = exame;
	}
	
	public FuncionarioVo getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(FuncionarioVo funcionario) {
		this.funcionario = funcionario;
	}
	
	public String getData() {
		return sdfDateISO.format(data);
	}
	
	public String getDataLoc() {
		return sdfDateLOC.format(data);
	}
	
	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ExameFuncionarioVo [rowid=" + rowid + ", exame=" + exame + ", funcionario=" + funcionario 
				+ ", data=" + data + "]";
	}
}
