<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title><s:text name="label.titulo.pagina.cadastro"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">

		<div class="container-sm" style="width: 65rem">
			<s:form action="/salvarEdicaoExamefuncionarios.action">

				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
								<s:url action="todosExamefuncionarios" var="todos"/>
								<a href="${todos}" class="btn btn-success" >Exames dos Funcionários</a>
							</div>
							
							<div class="col-sm">
								<h5 class="card-title">Editar Exame do Funcionário</h5>
							</div>
						</div>
					</div>
					
					<div class="card-body" >
						<div class="row align-items-center">
							<label for="id" class="col-sm-2 col-form-label text-center">
								Código:
							</label>	

							<div class="col-sm-3">
								<s:textfield cssClass="form-control" id="id" name="exameFuncionarioVo.rowid" readonly="true"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="idFuncionario" class="col-sm-2 col-form-label text-center">
								Código Funcionário:
							</label>	

							<div class="col-sm-3">
								<s:textfield cssClass="form-control" id="idFuncionario" name="exameFuncionarioVo.funcionario.rowid"/>							
							</div>
							<s:fielderror fieldName="exameFuncionarioVo.funcionario.rowid" cssClass="text-danger" style="padding-left:20%"/>
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="idExame" class="col-sm-2 col-form-label text-center">
								Código Exame:
							</label>	

							<div class="col-sm-3">
								<s:textfield cssClass="form-control" id="idExame" name="exameFuncionarioVo.exame.rowid"/>							
							</div>
							<s:fielderror fieldName="exameFuncionarioVo.exame.rowid" cssClass="text-danger" style="padding-left:20%"/>
						</div>
						
												
						<div class="row align-items-center mt-3">
							<label for="data" class="col-sm-2 col-form-label text-center">
								Data:
							</label>	

							<div class="col-sm-3">
								<s:textfield cssClass="form-control" id="data" type="date" name="exameFuncionarioVo.data" />							
							</div>
							<s:fielderror fieldName="exameFuncionarioVo.data" cssClass="text-danger" style="padding-left:20%"/>
						</div>

					</div>

					<div class="card-footer">
						<div class="form-row">
							<button class="btn btn-primary col-sm-4 offset-sm-1">Salvar</button>
							<button type="reset" class="btn btn-secondary col-sm-4 offset-sm-2">Limpar Formulario</button>
						</div>
					</div>
				</div>
			</s:form>			
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	</body>
</html>