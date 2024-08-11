package br.com.soc.sistema.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RelatorioVo {
	private Date dataInicial;
	private Date dataFinal;
	
	private static final SimpleDateFormat sdfDateISO= new SimpleDateFormat("yyyy-MM-dd");
	
	public RelatorioVo() {}
	
	public RelatorioVo(Date dataInicial, Date dataFinal) {
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}

	public String getDataInicial() {
		return sdfDateISO.format(dataInicial);
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return sdfDateISO.format(dataFinal);
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	@Override
	public String toString() {
		return "RelatorioVo [dataInicial=" + getDataInicial() + ", dataFinal=" + getDataFinal() + "]";
	}
}
