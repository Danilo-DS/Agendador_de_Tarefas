import {useEffect, useState} from 'react'
import ContextFactory from '../../util/ContextFactory'
import TableView from '../../components/TableView';
import TituloPage from '../../components/TituloPage';
import {tabelaTarefa} from '../../util'
import agendadasIcon from '../../assets/icons/AgendadasIcon.svg';
import Button from '../../components/Button';
import TarefaService from '../../services/TarefaService';
import {BsCheckBox, BsFillXOctagonFill, BsFillTrashFill, BsPencil} from 'react-icons/bs'

var listTarefa = [];

const contextoTarefa = [{id : "", titulo : "", usuarios : [{}], prioridade : "", statusTarefa : ""}];

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
					<Button classe = "btn btn-primary" id = "btnFinalizarTarefa" acaoButton 
					valorButton = {null} tipo = "button" descricao = {<BsCheckBox/>}/>
				}
				btnCancelar = {
					<Button classe = "btn btn-warning" id = "btnCancelarTarefa" acaoButton 
					valorButton = {null} tipo = "button" descricao = {<BsFillXOctagonFill/>}/>
				}
				btnEditar = {
					<Button classe = "btn btn-info" id = "btnEditarTarefa" acaoButton 
					valorButton = {null} tipo = "button" descricao = {<BsPencil/>}/>
				}
				btnDeletar = {
					<Button classe = "btn btn-danger" id = "btnDeletarTarefa" acaoButton 
					valorButton = {null} tipo = "button" descricao = {<BsFillTrashFill/>}/>
				}
			/>
		</ContextFactory.Provider>
		</div>
    );
}

export default Home;