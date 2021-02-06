//Carrega os usuario cadatrados e passa para o método "montaTabela".
function carregarUsers(){
	$(document).ready(function(){
		$.get("/api/v1/usuarios", function(resp){
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
 * @param obj json recebido do metodo carregaUsers*/	
const montaTabela = (obj) =>{
	
	obj.forEach(e => {
		let fone = e.telefone == '' ? e.celular : e.telefone + " / " + e.celular;
		let dominio = e.tipoUsuario == 'G' ? 'Gestor' : 'Usuário Comum';
		$('#table-view').append(
			`<tr class = "linha">
				<td id = "col-Nome">`+e.nome+`</td>
				<td id = "col-Email">`+e.email+`</td>
				<td id = "col-Telefone">`+fone+`</td>
				<td id = "col-dominio">`+dominio+`</td>
				<td>
					<button class="btn btn-info" type="button" id="btnEditar" data-toggle="modal" data-placement="top" 
					title="Editar Usuário" data-target="#modalEditar" value=`+e.id+` disable onClick = "visualizarUsuario(this.value)">
						<i class="fa fa-edit"></i>
					</button>
				</td>
				<td>
					<button class="btn btn-danger" type="button" id="btnExcluir" data-toggle="modal" data-placement="top" 
					title="Excluir Usuário" data-target="#modalExcluir" value=`+e.id+` onClick = "pegarIdUser(this.value)">
					<i class="fa fa-trash"></i>
					</button>
				</td>
			</tr>`		
		);
	});
	
};

//Exibe o alert por 1,5s e depois some.
function notificacao(msg, idText, idAlert){
	$("#" + idText).text(msg);			
	$( "#" + idAlert ).fadeIn(1100,0).delay( 1500 ).slideUp(1100)
};

/*Atualiza a tabela, reprocessando-a*/
function refreshTabela(){
	$(".linha").remove();			
	carregarUsers();
}

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
function pegarIdUser(id){
	document.getElementById('btnConfirmar').value = id;
}

/*Solicita a exclusão da tarefa*/
function deletarUsuario(){
	let idUsuario = document.getElementById('btnConfirmar').value;
	montaRequestDeletarAtualizar("/api/v1/usuarios/" + idUsuario, null, 'DELETE').done(function(resp){
			notificacao("Usuario deletado com sucesso", "sucesso", "alertSucesso");
			refreshTabela();
	}).fail(function(resp){
		console.log("Error => code = " + resp.code + "status = " + resp.status);
		if (resp != 404){
			notificacao("Usuario está associado a uma ou mais Tarefas! Impossível Deletar!", "error", "alertError");
		}
		else{
			notificacao(resp.responseText, "error", "alertError");
		}
	});
};

/*Carrega dados da tarefa para exibir no modalEditar
 * @param id da tarefa que foi selecionada para visualizar*/
function visualizarUsuario(idUsuario){
	$.get('/api/v1/usuarios/' + idUsuario).done(function(resp){
		document.getElementById('btnSalvar').value = idUsuario;
		document.getElementById('nome-modal').value = resp.nome;
		document.getElementById('telefone-modal').value = resp.telefone;
		document.getElementById('celular-modal').value = resp.celular;
		document.getElementById('email-modal').value = resp.email;
		document.getElementById('senha-modal').value = resp.senha;
		document.getElementById('dtNascimento-modal').value = resp.dtNascimento;
		document.getElementById('tipo-modal').value = resp.tipoUsuario == 'U' ? 'Usuário Comum' : 'Gestor';
		document.getElementById('endereco-modal').value = resp.endereco;
		document.getElementsByName('fotoPerfil-modal').text = "Sem foto de Perfil";
	})
}

/*Monta obj Json e faz a request para atualizar dados do usuario.*/
function atualizarUsuario(){
	let idUsuario = document.getElementById('btnSalvar').value
	let dadosUser = validaDadosAtulizacaoUsuario();
	let objUser = {
			"id" : idUsuario,
			"nome" : dadosUser.nome,
			"telefone" : document.getElementById('telefone-modal').value,
			"celular" : dadosUser.celular,
			"email" : dadosUser.email,
			"senha" : dadosUser.senha,
			"dtNascimento" : dadosUser.dtNascimento,
			"tipoUsuario" : document.getElementById('tipo-modal').value == 'Gestor' ? 'G' : 'U',
			"endereco" : dadosUser.endereco
		}

	if (dadosUser != null){
		montaRequestDeletarAtualizar('/api/v1/usuarios/' + idUsuario, JSON.stringify(objUser), 'PUT').done(function(resp){
				$('#modalEditar').modal("hide");
				notificacao("Usuário " + objUser.nome + " editado com sucesso", "sucesso", "alertSucesso");
				refreshTabela();
		}).fail(function(resp){
			console.log("Error => code = " + resp.code + "\nstatus = " + resp.status, "\nmsg = " + resp.responseText);
			notificacao(resp.responseText + " " + objUser.nome, "error", "alertError");
		});
	}
}

/*Valida se os campos obrigarios está todos preenchidos*/
function validaDadosAtulizacaoUsuario(){
	
	let nome = document.getElementById('nome-modal').value;
	let cel = document.getElementById('celular-modal').value;
	let email = document.getElementById('email-modal').value;
	let senha = document.getElementById('senha-modal').value;
	let dtNasc = document.getElementById('dtNascimento-modal').value;
	let endereco = document.getElementById('endereco-modal').value;

	let resp = {}
	
	if(nome == '' || cel == '' || email == '' || senha == '' ||  dtNasc == '' || endereco ==''){
		resp = null
	}	
	else{
		resp = {
				"nome" : nome,
				"celular" : cel,
				"email" : email,
				"senha" : senha,
				"dtNascimento" : dtNasc,
				"endereco" : endereco
				};
	}
	
	return resp;
}

carregarUsers();