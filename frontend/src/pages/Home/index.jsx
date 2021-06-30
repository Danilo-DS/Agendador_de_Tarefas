import {useEffect, useState} from 'react'
import FadeIn from 'react-fade-in';
import ContextFactory from '../../util/ContextFactory'
import TableView from '../../components/TableView';
import TituloPage from '../../components/TituloPage';
import agendadasIcon from '../../assets/icons/AgendadasIcon.svg';
import Button from '../../components/Button';
import Tooltip from '../../components/Tooltip';
import TarefaService from '../../services/TarefaService';
import {tabelaTarefa} from '../../util'
import {BsCheckBox, BsFillXOctagonFill, BsFillTrashFill, BsPencil,BsListCheck} from 'react-icons/bs'

var listTarefa = [];

const contextoTarefa = [{id : "", titulo : "", usuario : [{}], prioridade : "", statusTarefa : ""}];

function Home(){
	
	const [context, setContext] = useState(contextoTarefa);
	
	useEffect(()=> {
		TarefaService.getTarefas().then(function(resp){			
			if(listTarefa.length != resp.data.length){
				listTarefa = []
				resp.data.forEach(obj => {
					listTarefa.push(obj)
				});
			}
			setContext(listTarefa);
			console.info("Carregamento Bem Sucedido");
			console.info("Codigo Http: ", resp.status);
			console.info("Msg: " , resp.statusText);
		}).catch(function(resp){
            console.error("Erro ao carregar lista de tarefas!");
			console.error("Codigo Http " , resp.status);
			console.error("Msg: ", resp.statusText);
        });
	})


    return(
		<div className="container">
		
		<FadeIn>
			<TituloPage descricao = "Tarefas Agendadas" subDescricao = "Suas Tarefas" icon = {agendadasIcon} componente = {null}/>
		
			<ContextFactory.Provider value = {[context, setContext]}>
				<TableView titulo1 = 'Título' titulo2 = 'Responsávieis' titulo3 = 'Tipo' titulo4 = 'Nível Prioridade' titulo5 = 'Situação' entidade = {listTarefa} tipoTabela = {tabelaTarefa}
					btnFinalizar = {				
						<Tooltip posicao = "bottom" descricao = "Finalizar Agendamento" 
							componente = {
								<Button classe = "btn btn-primary" id = "btnFinalizarTarefa" acaoButton 
								valorButton = {null} tipo = "button" descricao = {<BsCheckBox/>}/>
							}
						/>
					}
					btnCancelar = {
						<Tooltip posicao = "bottom" descricao = "Cancelar Agendamento" 
							componente = {
								<Button classe = "btn btn-warning" id = "btnCancelarTarefa" acaoButton 
								valorButton = {null} tipo = "button" descricao = {<BsFillXOctagonFill/>}/>
							}
						/>
						
					}
					btnEditar = {
						<Tooltip posicao = "bottom" descricao = "Editar Agendamento" 
							componente = {
								<Button classe = "btn btn-info" id = "btnEditarTarefa" acaoButton 
								valorButton = {null} tipo = "button" descricao = {<BsPencil/>}/>
							}
						/>	
					}
					btnDeletar = {
						<Tooltip posicao = "bottom" descricao = "Deletar Agendamento" 
							componente = {
								<Button classe = "btn btn-danger" id = "btnDeletarTarefa" acaoButton 
								valorButton = {null} tipo = "button" descricao = {<BsFillTrashFill/>}/>
							}
						/>
					}
				/>
			</ContextFactory.Provider>
		</FadeIn>
		</div>
    );
}

export default Home;