import {useEffect, useContext} from 'react'
import ContextFactory from '../../util/ContextFactory'
import TableView from '../../components/TableView';
import TituloPage from '../../components/TituloPage';
import {tabelaTarefa} from '../../util'
import agendadasIcon from '../../assets/icons/AgendadasIcon.svg';
import { useState } from 'react';
import Button from '../../components/Button';
import TarefaService from '../../services/TarefaService';

var listTarefa = [];

const contextoTarefa = [{id : "", titulo : "", usuario : {nome : ""}, prioridade : "", statusTarefa : ""}];

function Home(){
	
	const [context, setContext] = useState(contextoTarefa);

	useEffect(()=> {
		TarefaService.getTarefas().then(function(resp){
			if(listTarefa.length != resp.data.length){
				resp.data.forEach(obj => {
					listTarefa.push(obj)
				});
			}
			setContext(listTarefa)
		}).catch(function(resp){
            console.error("Erro ao carregar lista de tarefas:")
            console.error("Error " + resp.message)
        });
	})


    return(
		<div className="container">

		<TituloPage descricao = "Tarefas Agendadas" subDescricao = "Suas Tarefas" icon = {agendadasIcon} componente = {null}/>
		
		<ContextFactory.Provider value = {[context, setContext]}>
			<TableView titulo1 = 'Título' titulo2 = 'Usuario Responsáviel' titulo3 = 'Nível Prioridade' titulo4 = 'Situação Tarefa' entidade = {listTarefa} tipoTabela = {tabelaTarefa}
				btnFinalizar = {
					<Button classe = "btn btn-primary" id = "btnEditarUsuario" acaoButton 
					valorButton = {null} tipo = "button" descricao = {<i className="fa fa-edit"></i>}/>
				}
				btnCancelar = {
					<Button classe = "btn btn-warning" id = "btnEditarUsuario" acaoButton 
					valorButton = {null} tipo = "button" descricao = {<i className="fa fa-edit"></i>}/>
				}
				btnEditar = {
					<Button classe = "btn btn-info" id = "btnEditarUsuario" acaoButton 
					valorButton = {null} tipo = "button" descricao = {<i className="fa fa-edit"></i>}/>
				}
				btnDeletar = {
					<Button classe = "btn btn-danger" id = "btnDeletarUsuario" acaoButton 
					valorButton = {null} tipo = "button" descricao = {<i className="fa fa-trash"></i>}/>
				}
			/>
		</ContextFactory.Provider>
		</div>
    );
}

export default Home;