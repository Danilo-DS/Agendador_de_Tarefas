import axios from 'axios';
import {auth} from '../util'


//const urlBase = "http://localhost:8080/api/v1/auth/login"

class LoginService{
    getAuth(email, senha){
        return axios.post(auth, {"email" : email, "senha" : senha });
    }
}

export default new LoginService();