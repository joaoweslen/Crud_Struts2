package br.com.soc.sistema.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.soc.sistema.vo.ExameFuncionarioVo;

public class POIXLS {
	private static final String fileName = "relatorio/relatorio.xls";
	
	public void criarRelatorioXLS(List<ExameFuncionarioVo> examesRelatorio) {
		HSSFWorkbook workbook = new	HSSFWorkbook();
		HSSFSheet sheetRelatorio = workbook.createSheet("Relatorio");
		
		File directory = new File("relatorio");
        if (!directory.exists()) {
            directory.mkdir();
        }
		
		int rownum = 0;
	    Row headerRow = sheetRelatorio.createRow(rownum++);
	    headerRow.createCell(0).setCellValue("ID Funcionário");
	    headerRow.createCell(1).setCellValue("Funcionário");
	    headerRow.createCell(2).setCellValue("ID Exame");
	    headerRow.createCell(3).setCellValue("Exame");
	    headerRow.createCell(4).setCellValue("Data");

		
		for (ExameFuncionarioVo exames : examesRelatorio) {
			int cellnum = 0;
			Row row = sheetRelatorio.createRow(rownum++);
			
			Cell cellIdFuncionario = row.createCell(cellnum++);
			Cell cellFuncionario = row.createCell(cellnum++);
			Cell cellIdExame = row.createCell(cellnum++);
			Cell cellExame = row.createCell(cellnum++);
			Cell cellData = row.createCell(cellnum++);
			
			cellIdFuncionario.setCellValue(exames.getFuncionario().getRowid());
			cellFuncionario.setCellValue(exames.getFuncionario().getNome());
			cellIdExame.setCellValue(exames.getExame().getRowid());
			cellExame.setCellValue(exames.getExame().getNome());
			cellData.setCellValue(exames.getDataLoc());
		}
		
		try (FileOutputStream out = new FileOutputStream(new File(POIXLS.fileName))){
            workbook.write(out);
            out.close();
            workbook.close();
             
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
