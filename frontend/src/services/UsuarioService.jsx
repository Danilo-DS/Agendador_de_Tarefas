import axios from 'axios';
import Utils from '../util/Util'

const urlBase = "http://localhost:8080/api/v1";

class UsuarioService{

    getUsuarios(){
       // console.log(urlBase + "/usuarios",)
        return axios.get(urlBase + "/usuarios",{Headers:{ "Authorization": 'Basic c3lzdGVtc2NoZWR1bGVAZ21haWwuY29tLmJyOjEyMw=='}});
    }

    getUsuarios2(){
        // console.log(urlBase + "/usuarios",)
         return axios.get(urlBase + "/usuarios/t/test");
     }
 

}

export default new UsuarioService();