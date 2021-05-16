import { useEffect } from 'react';
import agendarIcon from '../../assets/icons/AgendarIcon.svg';
import TituloPage from '../../components/TituloPage';
import Input from '../../components/Inputs/Normal Input';
import TextArea from '../../components/Inputs/Text-Area';
import Select from '../../components/Inputs/Select-Option';
import Button from '../../components/Button';
import ScrollBar from '../../components/ScrollBar';
import CheckBox from '../../components/Inputs/CheckBox';
import Label from '../../components/Label';
import TarefaService from '../../services/TarefaService';
import { useState } from 'react';
import ContextFactory from '../../util/ContextFactory';
import './style.css';

const prioridadesOptions = [{descricao:'ALTA'},{descricao:'MEDIA'},{descricao:'BAIXA'}];
var listaResponsaveis = [];

function CadastroAgendamento(){

    const [context, setContext] = useState(ContextFactory);

    useEffect(()=> {
		TarefaService.getUsuarioResponsaveis().then(function(resp){
			if(listaResponsaveis.length != resp.data.length){
				resp.data.forEach(obj => {
					listaResponsaveis.push({descricao : obj.nome, valor : obj.id});
                });
                setContext(listaResponsaveis);
			}
		}).catch(function(resp){
            console.error("Erro ao carregar lista de tarefas:");
            console.error("Error " + resp.message);
        });
	})

    return(
        <div className="container">
			
		    <TituloPage descricao = "Agendar Tarefa" subDescricao = "Preencha os campos" icon = {agendarIcon} componente = {null}/>

            <form className = "needs-validation">
                <Input classe = "col-md-12 mb-3" nmLabel = "tituloTarefa" descLabel = "Titulo da Tarefa" tipo = "text"
                 id = "tituloTarefa" legenda = "Título da Tarefa" valorInput = {undefined} tamanho = "64" restricao = {null} componente = {null}/>

                <TextArea classe = "col-md-12 mb-3" nmLabel = "descricaoTarefa" descLabel = "Descrição da Tarefa"
                 id = "descricaoTarefa" legenda = "Descreva da Tarefa" valorText = {undefined} tamanho = "254"/>

                <div className="row">
                    <Label classe="col-md-8 mb-3" name= "selecaoUsuario" descricao = "Selecione os Usuarios Responsáveis"/>
                    <ScrollBar classe = "overflow-auto col-md-8 mb-3" componente = {<CheckBox isLista = {true} objCheck = {listaResponsaveis} />} estilo = {{height : "100px"}}/>

                    <Select classe = "col-md-4 mb-3" nmLabel = "nivelDePrioridade" descLabel = "Nivel de Prioridade"
                    id= "prioridade" valorOption = "0" descOption = "Selecione a Prioridade" arrayOption  = {prioridadesOptions}/>
                </div>

                <hr className="mb-4"/>

                <Button classe = "btn btn-primary btn-lg btn-block" id = "btnCadastrarTarefa" acaoButton = "" 
                 valorButton = {null} tipo = "button" descricao = "Realizar Agendamento" />

            </form>	
		</div>
    );
}

export default CadastroAgendamento;