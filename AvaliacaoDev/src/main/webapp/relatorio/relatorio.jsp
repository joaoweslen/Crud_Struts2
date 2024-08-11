<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title><s:text name="label.titulo.pagina.consulta"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">
				<a class="navbar-brand">SOC</a>
		    
				<div class="collapse navbar-collapse" id="navbar">
					<ul class="navbar-nav">
						<li class="nav-item">
							<a href="/avaliacao/todosExames.action" class="nav-link">Exames</a>
						</li>
						<li class="nav-item">
							<a href="/avaliacao/todosFuncionarios.action" class="nav-link">Funcionários</a>
						</li>
						<li class="nav-item">
							<a href="/avaliacao/todosExamefuncionarios.action" class="nav-link">Exames dos Funcionários</a>
						</li>
						<li class="nav-item">
							<a href="/avaliacao/browserRelatorios.action" class="nav-link">Relatório</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>
		
		<div class="container">
			<s:form action="buscarRelatorios">
				<div class="row justify-content-md-center mt-4">
					<label for="dataInicial" class="col col-form-label col-lg-1 text-light text-end">
						Data Inicial:
					</label>	
					<div class="col-md-auto">
						<s:textfield cssClass="form-control" id="dataInicial" type="date" name="relatorioVo.dataInicial"/>
						<s:fielderror fieldName="relatorioVo.dataInicial" cssClass="text-danger"/>							
					</div>
					
					<label for="dataFinal" class="col col-form-label col-lg-1 text-light text-end">
						Data final:
					</label>	
					<div class="col-md-auto">	
						<s:textfield cssClass="form-control" id="dataFinal" type="date" name="relatorioVo.dataFinal" />	
						<s:fielderror fieldName="relatorioVo.dataFinal" cssClass="text-danger"/>						
					</div>
					
					<div class="col-md-auto">
						<button class="btn btn-primary">Buscar</button>
					</div>
				</div>
			</s:form>
			
			<div class="row mt-5 mb-2">
				<table class="table table-light table-striped align-middle">
					<thead>
						<tr>
							<th><s:text name="label.id.funcionario"/></th>
							<th><s:text name="label.funcionario"/></th>
							<th><s:text name="label.id.exame"/></th>
							<th><s:text name="label.exame"/></th>
							<th><s:text name="label.data"/></th>
						</tr>
					</thead>
					
					<s:if test="!relatorios.isEmpty()">
						<tbody>
							<s:iterator value="relatorios" >
								<tr>
									<td>${funcionario.rowid}</td>
									<td>${funcionario.nome}</td>
									<td>${exame.rowid}</td>
									<td>${exame.nome}</td>
									<td>${dataLoc}</td>
								</tr>
							</s:iterator>
						</tbody>
						<s:form action="/criarRelatorios.action">
							<s:textfield type="hidden" name="relatorioVo.dataInicial"/>	
							<s:textfield type="hidden" name="relatorioVo.dataFinal" />	
							<button class="btn btn-success">Gerar Relatório XLS</button>
						</s:form>
					</s:if>
					<s:else>
				        <tr>
				            <td colspan="5">Nenhum relatório encontrado.</td>
				        </tr>
   					</s:else>		
				</table>
			</div>
		</div>
	</body>
</html>