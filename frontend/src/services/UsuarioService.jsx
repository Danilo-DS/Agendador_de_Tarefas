import axios from 'axios';
import {urlBase,listarUsuarios} from '../util'
import MontaHerader from '../util'

class UsuarioService{

    getUsuarios(){
        return axios.get(urlBase + listarUsuarios, MontaHerader());
    }
}

export default new UsuarioService();