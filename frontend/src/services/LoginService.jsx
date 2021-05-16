import axios from 'axios';
import {urlBase} from '../util'
import {auth} from '../util'
import {deletarCredenciais} from '../util';

class LoginService{
    getAuthLogin(email, senha){
        return axios.post(urlBase + auth, {"email" : email, "senha" : senha });
    }

    logout(){
        deletarCredenciais();
    }
}

export default new LoginService();