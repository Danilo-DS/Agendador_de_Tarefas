var idTarefa; // var global que recebe o id da tarefa para realizar operações

/*Carrega todas as tarefas agendadas e manda para a
function montaTabela para exibir */
function carregarTarefas(){
	$(document).ready(function(){
		$.get("/api/v1/agendar-tarefa", function(resp){
			if(typeof(resp) != undefined && !resp == ""){
				montaTabela(resp);
			}
		}).fail(function(resp){
			console.log("Error => code = " + resp.code + "\nstatus = " + resp.statusCode, "\nmsg = " + resp.responseText);
			notificacao("Erro Inesperado, Reload Page!", "error", "alertError");						
		});
	});
};

/* Monta a tabela em html
 * @param obj passado pela function carregaTarefas */	
const montaTabela = (obj) =>{
	
	obj.forEach(e => {
		$('#table-view').append(
			`<tr class = "linha">
				<td id = "col-Titulo">`+e.titulo+`</td>
				<td id = "col-NomeUser">`+e.usuario.nome+`</td>
				<td id = "col-Prioridade">`+e.prioridade+`</td>
				<td id = "col-Status">`+e.statusTarefa+`</td>
				
				`+operacoesLista(e.statusTarefa, e.id)+`
			</tr>`		
		);
	});
	
};

/*Desabilita os buttons de acordo com os status da tarefas
 * @param statusTarefa situação em que a tarefa se encontra
 * @param id da tarefa*/
function operacoesLista(statusTarefa, id){	
	if(statusTarefa == "FINALIZADA"){
		return `<td>
					<button class="btn btn-primary" type="button" id="btnEditar" data-toggle="modal" data-placement="top" 
					title="Finalizar Tarefa" data-target="#modalFinalizar" value="" disabled="disabled">
						Finalizar
					</button>
				</td>
				<td>
					<button class="btn btn-warning" type="button" id="btnEditar" data-toggle="modal" data-placement="top" 
					title="Cancelar Tarefa" data-target="#modaCancelar" value="" disabled="disabled">
						Cancelar
					</button>
				</td>
				<td>
					<button class="btn btn-info" type="button" id="btnEditar" data-toggle="modal" data-placement="top" 
					title="Editar Tarefa" data-target="#modalEditar" value="" disabled="disabled">
						<i class="fa fa-edit"></i>
					</button>
				</td>
				<td>
					<button class="btn btn-danger" type="button" id="btnExcluir" data-toggle="modal" data-placement="top" 
					title="Excluir Usuário" data-target="#modalExcluir" value="" disabled="disabled">
					<i class="fa fa-trash"></i>
					</button>
				</td>`
	}
	else if(statusTarefa == "CANCELADA"){
		return `<td>
					<button class="btn btn-primary" type="button" id="btnEditar" data-toggle="modal" data-placement="top" 
					title="Finalizar Tarefa" data-target="#modalFinalizar" value="" disabled="disabled">
						Finalizar
					</button>
				</td>
				<td>
					<button class="btn btn-warning" type="button" id="btnEditar" data-toggle="modal" data-placement="top" 
					title="Cancelar Tarefa" data-target="#modaCancelar" value="" disabled="disabled">
						Cancelar
					</button>
				</td>
				<td>
					<button class="btn btn-info" type="button" id="btnEditar" data-toggle="modal" data-placement="top" 
					title="Editar Tarefa" data-target="#modalEditar" value="" disabled="disabled">
						<i class="fa fa-edit"></i>
					</button>
				</td>
				<td>
					<button class="btn btn-danger" type="button" id="btnExcluir" data-toggle="modal" data-placement="top" 
					title="Excluir Usuário" data-target="#modalExcluir" value=`+id+` onClick = "pegarIdTarefa(this.value)">
					<i class="fa fa-trash"></i>
					</button>
				</td>`
	}
	
	else{
		return `<td>
					<button class="btn btn-primary" type="button" id="btnEditar" data-toggle="modal" data-placement="top" 
					title="Finalizar Tarefa" data-target="#modalFinalizar" value=`+id+` onClick = "pegarIdTarefa(this.value)">
						Finalizar
					</button>
				</td>
				<td>
					<button class="btn btn-warning" type="button" id="btnEditar" data-toggle="modal" data-placement="top" 
					title="Cancelar Tarefa" data-target="#modaCancelar" value=`+id+` onClick = "pegarIdTarefa(this.value)">
						Cancelar
					</button>
				</td>
				<td>
					<button class="btn btn-info" type="button" id="btnEditar" data-toggle="modal" data-placement="top" 
					title="Editar Tarefa" data-target="#modalEditar" value=`+id+` onClick = "visualizarTarefa(this.value)">
						<i class="fa fa-edit"></i>
					</button>
				</td>
				<td>
					<button class="btn btn-danger" type="button" id="btnExcluir" data-toggle="modal" data-placement="top" 
					title="Excluir Usuário" data-target="#modalExcluir" value=`+id+` onClick = "pegarIdTarefa(this.value)">
					<i class="fa fa-trash"></i>
					</button>
				</td>` 
				
	}
	
}


//Exibe o alert por 1,5s e depois some.
function notificacao(msg, idText, idAlert){
	$("#" + idText).text(msg);			
	$( "#" + idAlert ).fadeIn(1100,0).delay( 1500 ).slideUp(1100)
};

/*Atualiza a tabela que exibe as tarefas*/
function refreshTabela(){
	$(".linha").remove();			
	carregarTarefas();
}

/*Carrega os usuario e manda para a function montaSelectUsuario*/
function carregarUsers(){
	$(document).ready(function(){
		$.get("/api/v1/usuarios", function(resp){
			if(typeof(resp) != undefined && !resp == ""){
				montaSelectUsuario(resp);
			}
		});
	});
};

/* Monta select option com os usuario em html */	
const montaSelectUsuario = (obj) =>{
	obj.forEach(e => {
		$('#user-modal').append(
			`<option value="`+e.id+`">`+e.nome+`</option>>`		
		);
	});
};

/*Monta a request do tipo informado, essa function é para os tipos PUT e DELETE
 * @param url da request
 * 		  obj que vai ser enviado
 * 		  type da request que sera enviada*/
function montaRequestDeletarAtualizar(url, obj, type){
	return $.ajax({
	    url: url,
	    type: type,
	    data: obj,
	    contentType: "application/json"
	  });
};

/*Pega o id da tarefa que está sendo editada, deletada, finalizada, ou cancelada
 * @param pega o id da tarefa que está sendo manipulada*/
function pegarIdTarefa(id){
	console.log(id);
	document.getElementById('btnConfirmar').value = id;
}

/*Solicita a finalização da tarefa*/
function finalizarTarefa(){
	idTarefa = document.getElementById('btnConfirmar').value;
	let objTarefaFinalizar = {
			"statusTarefa" : "FINALIZADA"
	}
	
	montaRequestDeletarAtualizar("/api/v1/agendar-tarefa/" + idTarefa, JSON.stringify(objTarefaFinalizar), 'PUT').done(function(resp){
			notificacao("Tarefa finalizada com sucesso", "sucesso", "alertSucesso");
			refreshTabela();
	}).fail(function(resp){
		if(resp.code == 404){
			console.log("Error => code = " + resp.code + "\nstatus => " + resp.statusCode + "\nmsg =>" + resp.responseText);
			notificacao(resp.responseText, "error", "alertError");
		}
		else{
			console.log("Error => code = " + resp.code + "\nstatus => " + resp.statusCode + "\nmsg =>" + resp.responseText);
			notificacao("Erro Inesperado", "error", "alertError");
		}
	});
};

/*Solicita a cancelamento da tarefa*/
function cancelarTarefa(){
	idTarefa = document.getElementById('btnConfirmar').value;
	let objTarefaCancelar = {
			"statusTarefa" : "CANCELADA"
	}
	
	montaRequestDeletarAtualizar("/api/v1/agendar-tarefa/" + idTarefa, JSON.stringify(objTarefaCancelar), 'PUT').done(function(resp){
			notificacao("Tarefa cancelada com sucesso", "sucesso", "alertSucesso");
			refreshTabela();
	}).fail(function(resp){
		if(resp.code == 404){
			console.log("Error => code = " + resp.code + "\nstatus => " + resp.statusCode + "\nmsg =>" + resp.responseText);
			notificacao(resp.responseText, "error", "alertError");
		}
		else{
			console.log("Error => code = " + resp.code + "\nstatus => " + resp.statusCode + "\nmsg =>" + resp.responseText);
			notificacao("Erro Inesperado", "error", "alertError");
		}
	});
};

/*Solicita a exclusão da tarefa*/
function deletarTarefa(){
	idTarefa = document.getElementById('btnConfirmar').value;
	console.log(idTarefa);
	montaRequestDeletarAtualizar("/api/v1/agendar-tarefa/" + idTarefa, null, 'DELETE').done(function(resp){
			notificacao("Tarefa deletado com sucesso", "sucesso", "alertSucesso");
			refreshTabela();
	}).fail(function(resp){
		if(resp.code == 404){
			console.log("Error => code = " + resp.code + "\nstatus => " + resp.statusCode + "\nmsg =>" + resp.responseText);
			notificacao(resp.responseText, "error", "alertError");
		}
		else{
			console.log("Error => code = " + resp.code + "\nstatus => " + resp.statusCode + "\nmsg =>" + resp.responseText);
			notificacao("Erro Inesperado", "error", "alertError");
		}
	});
};

/*Carrega dados da tarefa para exibir no modalEditar
 * @param id da tarefa que foi selecionada para visualizar*/ 
function visualizarTarefa(idTarefa){
	carregarUsers();
	$.get('/api/v1/agendar-tarefa/' + idTarefa).done(function(resp){
		document.getElementById('btnSalvar').value = idTarefa;
		document.getElementById('titulo-modal').value = resp.titulo;
		document.getElementById('descricao-modal').value = resp.descricao;
		document.getElementById('user-modal').value = resp.usuario.nome;
		document.getElementById('prioridade-modal').value = resp.prioridade;
		document.getElementById('situacao-modal').value = resp.statusTarefa;
		document.getElementById('dtCriacao-modal').value = resp.dtCriacaoTarefa;
		document.getElementById('dtFinalizacao-modal').value = resp.dtFinalizacaoTarefa;
		document.getElementById('dtCancelamento-modal').value = resp.dtCancelamentoTarefa;
	})
}

/*Solicita atualização de dados da tarefa escolhida*/
function atualizarTarefa(){
	idTarefa = document.getElementById('btnSalvar').value
	let dadosTarefa = validaDadosAtulizacaoTarefa();
	let objTarefa = {
			"id" : idTarefa,
			"titulo" : dadosTarefa.title,
			"descricao" : dadosTarefa.desc,
			"usuario" : {"id" : dadosTarefa.user},
			"prioridade" : dadosTarefa.prior,
			"statusTarefa" : dadosTarefa.situacao,
			"dtCriacaoTarefa" : document.getElementById('dtCriacao-modal').value
		}
	console.log("antes :" + JSON.stringify(objTarefa))
	if (dadosTarefa != null){
		montaRequestDeletarAtualizar('/api/v1/agendar-tarefa/' + idTarefa, JSON.stringify(objTarefa), 'PUT').done(function(resp){
				$('#modalEditar').modal("hide");
				notificacao(" Tarefa " + objTarefa.titulo + " editado com sucesso", "sucesso", "alertSucesso");
				refreshTabela();
		}).fail(function(resp){
			if(resp.code == 404){
				console.log("Error => code = " + resp.code + "\nstatus = " + resp.statusCode, "\nmsg = " + resp.responseText);
				notificacao(resp.responseText + " " + objTarefa.titulo, "error", "alertError");
			}
			else{
				console.log("Error => code = " + resp.code + "\nstatus => " + resp.statusCode + "\nmsg =>" + resp.responseText);
				notificacao("Erro Inesperado", "error", "alertError");
			}
		});
		$('#modalEditar').modal("hide");
		console.log(objTarefa)
	}
}

/*Valida se os campos do modalEditar estam preenchidos*/
function validaDadosAtulizacaoTarefa(){
	
	let titulo = document.getElementById('titulo-modal').value;
	let descricao = document.getElementById('descricao-modal').value;
	let user = document.getElementById('user-modal').value;
	let prioridade = document.getElementById('prioridade-modal').value;
	let situacao = document.getElementById('situacao-modal').value;

	let resp = {}
	
	if(titulo == '' || descricao == '' || prioridade == '' || situacao == ''){
		resp = null
	}	
	else{
		resp = {
				"title" : titulo,
				"desc" : descricao,
				"user" : user,
				"prior" : prioridade,
				"situacao" : situacao
				};
	}
	
	return resp;
}

carregarTarefas()