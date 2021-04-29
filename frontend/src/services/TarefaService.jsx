import axios from 'axios';
import {urlBase,listarTarefas,buscarTarefa,listarTarefaPor,usuarios,salvarTarefa,atualizarTarefa,deletarTarefa} from '../util';
import MontarHeader from '../util';

class TarefaService{
    getTarefas(){
        return axios.get(urlBase + listarTarefas, MontarHeader());
    }
}

export default new TarefaService();

