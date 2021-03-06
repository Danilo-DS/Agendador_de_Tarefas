import axios from 'axios';
import {urlBase,listarTarefas,buscarTarefa,listarTarefaPorU,usuarioT,salvarTarefa, salvarReuniao,atualizarTarefa,deletarTarefa,
       listarTarefasPorE, empresaT, listarUsuariosR, responsavel} from '../util';
import {isMaster, isEmpresaOuGestor} from '../util';
import MontarHeader from '../util';

class TarefaService{
    
    getTarefas(){
        if(isMaster()){
            return axios.get(urlBase + listarTarefas, MontarHeader());            
        }
        
        return this.getTarefasEmpresaUsuario();
    }
    
    getTarefasEmpresaUsuario(){
        let buscarIdEmpresa = localStorage.getItem("empresa");
        let buscarIdUsuario = localStorage.getItem("id");
        if(isEmpresaOuGestor()){
            return axios.get(urlBase + listarTarefasPorE + buscarIdEmpresa + empresaT, MontarHeader());
        }
        return axios.get(urlBase + listarTarefaPorU + buscarIdUsuario + usuarioT, MontarHeader());
    }

    getUsuarioResponsaveis(){ 
        let buscarIdEmpresa = localStorage.getItem("empresa").replace(/"/g,);

        return axios.get(urlBase + listarUsuariosR + buscarIdEmpresa + responsavel, MontarHeader());
    }

    saveReuniao(reuniao){
        return axios.post(urlBase + salvarReuniao, reuniao, MontarHeader());            
    }

    saveAtividade(atividade){
        return axios.post(urlBase + salvarTarefa, atividade, MontarHeader());            
    }

}

export default new TarefaService();

