import axios from 'axios';
import {listarUsuarios} from '../util'
import montaHerader from '../util'

const urlBase = "http://localhost:8080/api/v1";

class UsuarioService{

    getUsuarios(){
        return axios.get(urlBase + listarUsuarios, montaHerader());
    }
}

export default new UsuarioService();