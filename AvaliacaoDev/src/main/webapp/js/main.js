function setURL(url){
	var btn_confirmaExclusao = document.querySelector("#excluir");
	btn_confirmaExclusao.setAttribute("href",url);
}

function changeType(value){
	var input_pesquisa = document.querySelector("#pesquisa");
	if(value != 6){
		input_pesquisa.setAttribute("type","text");
	}else{
		input_pesquisa.setAttribute("type","date");
	}
}