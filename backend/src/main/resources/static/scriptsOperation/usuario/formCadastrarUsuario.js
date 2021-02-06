
//Exibe o alert por 1,5s e depois some.
function notificacao(msg){
	$("#error").text(msg);			
	$( "#alertError" ).fadeIn(1100,0).delay( 1500 ).slideUp(1100)
};

/*Monta o obj Json que vai enviado na request para realizar cadastro do usuario*/
function cadastrarusuario(){	
	
	let dadosCadastro = validaCadastro();
	
	
		let objUser = {
			"nome" : dadosCadastro.nome,
			"telefone" : document.getElementById("telefone").value,
			"celular" : dadosCadastro.celular,
			"email" : dadosCadastro.email,
			"senha" : dadosCadastro.senha,
			"dtNascimento" : dadosCadastro.dtNasc,
			"tipoUsuario" : dadosCadastro.tipoUser == 'Gestor' ? 'G' : 'U',
			"endereco" : dadosCadastro.endereco
		}

	if (dadosCadastro.nome != '' && dadosCadastro.celular != '' && dadosCadastro.email != '' &&
		dadosCadastro.senha != '' && dadosCadastro.dtNasc != '' && dadosCadastro.tipoUser != '' && dadosCadastro.endereco != ''){

		$.post("/api/v1/usuarios", objUser).done(function(resp){
			window.location.assign("/api/v1/home/usuarios/listar-usuario");
		}).fail(function(resp) {
				if(resp.statusCode == 500){
					console.log("Error => code status = " + resp.statusCode, "\nmsg = " + resp.responseText);
					notificacao("Erro Inesperado, Try Again!", "error", "alertError");
				}
				else{
					console.log("Error => code status = " + resp.statusCode, "\nmsg = " + resp.responseText);
					notificacao("Email já está em uso!", "error", "alertError");
				}
		});
	}
}

/*Valida se todos os campos então preenchidos*/
function validaCadastro(){
	let nome = document.getElementById("nomeCompleto").value;
	let cel = document.getElementById("celular").value;
	let email = document.getElementById("email").value;
	let senha = document.getElementById("senha").value;
	let dtNasc =  document.getElementById("dt_Nascimento").value;
	let tipoUsuario = document.getElementById("tipo").value;
	let endereco = document.getElementById("endereco").value;
	
	let resp = {}
	
	if(nome == '' || cel == '' || email == '' || senha == '' ||  dtNasc == '' || endereco =='' || tipoUsuario == 0){
		notificacao("Preencha todos os campos obrigatórios para realizar o cadastro")
	}	
	else{
		return resp ={
				"nome" : nome,
				"celular" : cel,
				"email" : email,
				"senha" : senha,
				"dtNasc" : dtNasc,
				"tipoUser" : tipoUsuario,
				"endereco" : endereco
				};
	}
	
}
