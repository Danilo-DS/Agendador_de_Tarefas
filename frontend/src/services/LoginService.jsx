import axios from 'axios';
import {urlBase} from '../util'
import {auth} from '../util'
import {deletarCredenciais} from '../util';

//const urlBase = "http://localhost:8080/api/v1/auth/login"

class LoginService{
    getAuthLogin(email, senha){
        return axios.post(urlBase + auth, {"email" : email, "senha" : senha });
    }

    logout(){
        deletarCredenciais();
    }
}

export default new LoginService();