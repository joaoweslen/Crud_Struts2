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
							<s:url action="todosfuncionarios" var="urlExame"/>
							<a href="/avaliacao/todosFuncionarios.action" class="nav-link">Funcion�rios</a>
						</li>
						<li class="nav-item">
							<a href="/avaliacao/todosExamefuncionarios.action" class="nav-link">Exames dos Funcion�rios</a>
						</li>
						<li class="nav-item">
							<a href="/avaliacao/browserRelatorios.action" class="nav-link">Relat�rio</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>
		<div class="container">
			<div class="row mt-5 mb-2">
				<div class="col-sm p-0">
					<s:form action="/filtrarFuncionarios.action">
						<div class="input-group">
							<span class="input-group-text">
								<strong><s:text name="label.buscar.por"/></strong>
							</span>	
								<s:select  
									cssClass="form-select" 
									name="filtrar.opcoesCombo" 
									list="listaOpcoesCombo"  
									headerKey=""  
									headerValue="Escolha..." 
									listKey="%{codigo}" 
									listValueKey="%{descricao}"
									value="filtrar.opcoesCombo.codigo"									
								/>
								
								<s:textfield cssClass="form-control" id="pesquisa" name="filtrar.valorBusca"/>
								<button class="btn btn-primary" type="submit"><s:text name="label.pesquisar"/></button>
						</div>
					</s:form>			
				</div>				
			</div>

			<div class="row">
				<table class="table table-light table-striped align-middle">
					<thead>
						<tr>
							<th><s:text name="label.id"/></th>
							<th><s:text name="label.funcionario"/></th>
							<th class="text-end mt-5"><s:text name="label.acao"/></th>
						</tr>
					</thead>
					<s:if test="!funcionarios.isEmpty() && !funcionarios.contains(null)">
						<tbody>
							<s:iterator value="funcionarios" >
								<tr>
									<td>${rowid}</td>
									<td>${nome}</td>
									<td class="text-end">
										<s:url action="editarFuncionarios" var="editar">
											<s:param name="funcionarioVo.rowid" value="rowid"></s:param>
										</s:url>
										<a href="${editar}" class="btn btn-warning text-white">
											<s:text name="label.editar"/>
										</a>
										
										<s:url action="deletarFuncionarios" var="deletar">
												<s:param name="funcionarioVo.rowid" value="rowid"></s:param>
										</s:url>
										<a href="#" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#confirmarExclusao" onclick="setURL('${deletar}')">
											<s:text name="label.excluir"/>
										</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</s:if>
					<s:else>
				        <tr>
				            <td colspan="5">Nenhum funcion�rio encontrado.</td>
				        </tr>
   					</s:else>
					<tfoot class="table-secondary">
						<tr>
							<td colspan="3">
								<s:url action="novoFuncionarios" var="novo"/>
								
								<a href="${novo}" class="btn btn-success">
									<s:text name="label.novo"/>
								</a>
							</td>
						</tr>
					</tfoot>				
				</table>
			</div>

			<div class="row">
			
			</div>
		</div>
		
		<div  class="modal fade" id="confirmarExclusao" 
			data-bs-backdrop="static" data-bs-keyboard="false"
			tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title"><s:text name="label.modal.titulo"/></h5>
		        
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      
		      <div class="modal-body">
		      	<span><s:text name="label.modal.corpo"/></span>
		      </div>
		      
		      <div class="modal-footer">
	        	<a class="btn btn-secondary" data-bs-dismiss="modal" aria-label="Close">
					<s:text name="label.nao"/>
				</a>

				<a href="#" id="excluir" class="btn btn-primary" style="width: 75px;">
					<s:text name="label.sim"/>
				</a>						
		      </div>
		    </div>		    
		  </div>
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
		<script src="js/main.js"></script>
	</body>
</html>