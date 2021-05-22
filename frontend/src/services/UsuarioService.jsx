import axios from 'axios';
import {urlBase,listarUsuarios, isMaster, listarUsuariosPorE,empresaU} from '../util'
import MontarHeader from '../util'

class UsuarioService{

    getUsuarios(){
        if(isMaster()){
            return axios.get(urlBase + listarUsuarios, MontarHeader());
        }
        let buscarIdEmpresa = localStorage.getItem("empresa");
        return axios.get(urlBase + listarUsuariosPorE + buscarIdEmpresa + empresaU, MontarHeader());
    }

}

export default new UsuarioService();