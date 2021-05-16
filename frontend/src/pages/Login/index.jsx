import LoginService from '../../services/LoginService';
import {salvarCredenciais, cadastrarEmpresaPath} from '../../util';
import {Link} from 'react-router-dom';

function LoginPage(){

    function login(event){
        event.preventDefault();
        let email = document.getElementById('inputEmail').value;
        let senha = document.getElementById('inputSenha').value;

        LoginService.getAuthLogin(email, senha).then(function(resp){
            
           salvarCredenciais(resp.data, event, resp);            
           window.location.assign("/home");               
        }).catch(function(resp){
            console.error("Error ao Autenticar");
            console.error("Error: " + resp.data);
        });

    }

    return (
        <div className = "container" align = "center">

	    <form className="form-signin" id="formLogin">
	      <h1 className="h3 text-center mb-3 font-weight-normal">Seja Bem Vindo <br/> ao Schedule System</h1>
	      
	      <label htmlFor="inputEmail" className="sr-only">Email</label>
	      <input type="email" id="inputEmail" className="col-4 mb-2 form-control" name="email" placeholder="E-mail" required/>
	      
	      <label htmlFor="inputPassword" className="sr-only">Senha</label>
	      <input type="password" id="inputSenha" className="col-4 mb-4 form-control" name="senha" placeholder="Senha" required />
	      
	      <button className="col-4 mb-3 btn btn-lg btn-primary btn-block" id="btnEntrar" onClick = {login}>Entrar</button>
				
        </form>
        <Link to= {cadastrarEmpresaPath}>Cadastra-se como Empresa</Link>
        <br/>
        <Link to= "/cadastrar-empresa">Cadastra-se como Usuario</Link>
	  </div>
    )
}

export default LoginPage;