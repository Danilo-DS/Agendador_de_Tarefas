
/*carrega todos os usuairo e manda para a function
 * que monta o select*/
function carregarUsers(){
	$(document).ready(function(){
		$.get("/api/v1/usuarios", function(resp){
			if(typeof(resp) != undefined && !resp == ""){
				montaSelectUsuario(resp);
			}
		}).fail(function(resp){
			console.log("Error => code = " + resp.code + "\nstatus = " + resp.statusCode, "\nmsg = " + resp.responseText);
			notificacao("Erro Inesperado, Reload Page!", "error", "alertError");						
		});
	});
};

/* Monta select option com os usuario em html */	
const montaSelectUsuario = (obj) =>{
	obj.forEach(e => {
		let fone = e.telefone == null ? e.celular : e.telefone + " / " + e.celular;
		let dominio = e.tipoUsuario == 'G' ? 'Gestor' : 'Usuário Comum';
		$('#userResponsavel').append(
			`<option value="`+e.id+`">`+e.nome+`</option>>`		
		);
	});
};

//Exibe o alert por 1,5s e depois some.
function notificacao(msg, idText, idAlert){
	$("#" + idText).text(msg);			
	$( "#" + idAlert ).fadeIn(1100,0).delay( 1500 ).slideUp(1100)
};

/*Monta o Obj Json para realizar o cadastro*/
function cadastrarAgendamento(){
		let agendamento = validaUsuarioPrioridade();
	if (agendamento.user != '' && agendamento.prior != '' 
				&& agendamento.titulo != '' && agendamento.descricao != ''){
		let objTarefa = {
			"titulo" : agendamento.titulo,
			"descricao" : agendamento.descricao,
			"usuario" : agendamento.user,
			"prioridade" : agendamento.prior
		}
		
		$.post("/api/v1/agendar-tarefa", objTarefa).done(function(resp) {
			notificacao("Tarefa Agendada com sucesso", "sucesso", "alertSucesso")
			$('.needs-validation').each (function(){
				  this.reset();
				});
		}).fail(function(resp) {
			console.log("Error => code = " + resp.code + "\nstatus = " + resp.statusCode, "\nmsg = " + resp.responseText);
			notificacao(resp.responseText, "error", "alertError");						
		});
	}
}

/*Valida se os todos os campos está preenchidos
 * para realizar o cadastro da tarefa*/
function validaUsuarioPrioridade(){
	let usuario = document.getElementById("userResponsavel").value;
	let prioridade = document.getElementById("prioridade").value;
	let titulo = document.getElementById("tituloTarefa").value;
	let descricao = document.getElementById("descricaoTarefa").value;
	let resp = {}
	
	if(usuario != 0 && prioridade == 0){
		notificacao("Selecione a Prioridade!", "error", "alertError")
	}	
	else if(prioridade != 0 && usuario == 0){
		notificacao("Selecione um Responsável!", "error", "alertError")
	}
	else if (prioridade == 0 && usuario == 0 || titulo == '' || titulo == null || descricao == '' || descricao == null){
		notificacao("Preencha os campos corretamente", "error", "alertError")
	}
	else{
		return resp = {
				"titulo" : titulo,
				"descricao" : descricao,
				"user" : usuario,
				"prior" : prioridade
				};
	}
}

carregarUsers();