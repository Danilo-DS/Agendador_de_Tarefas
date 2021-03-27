import LoginService from '../../services/LoginService'

function LoginPage(){

    function login(event){
        event.preventDefault()
        let email = document.getElementById('inputEmail').value;
        let senha = document.getElementById('inputSenha').value;

        LoginService.getAuth(email, senha).then(function(resp){
            
                localStorage.setItem("token",JSON.stringify(resp.data.token));
                localStorage.setItem("type",JSON.stringify(resp.data.type));
                localStorage.setItem("id",JSON.stringify(resp.data.id));
                localStorage.setItem("email",JSON.stringify(resp.data.email));
                localStorage.setItem("permissao",JSON.stringify(resp.data.permissao)); 
                window.location.assign("/home");               
        }).catch(function(resp){
            console.error("Error ao Autenticar");
            console.error("Error: " + resp.data);
        });

    }

    return (
        <div className = "container">

	    <form className="card form-signin" id="formLogin">
	      <h1 className="h3 mb-3 font-weight-normal">Seja Bem Vindo <br/> ao Pastel System</h1>
	      
	      <label htmlFor="inputEmail" className="sr-only">Email</label>
	      <input type="email" id="inputEmail" className="form-control" name="email" placeholder="Email" required/>
	      
	      <label htmlFor="inputPassword" className="sr-only">Senha</label>
	      <input type="password" id="inputSenha" className="form-control" name="senha" placeholder="Senha" required />
	      
	      <button className="btn btn-lg btn-primary btn-block" id="btnEntrar" onClick = {login}>Entrar</button>
				
        </form>
	    <br/>
	   
	  </div>
    )
}

export default LoginPage;