import TableView from '../../components/TableView';
import TituloPage from '../../components/TituloPage';
import agendadasIcon from '../../assets/icons/AgendadasIcon.svg';

function Home(){
    return(
		<div className="container">
			
		<TituloPage descricao = "Tarefas Agendadas" subDescricao = "Suas Tarefas" icon = {agendadasIcon} componente = {null}/>

		<TableView/>
			
		</div>
    );
}

export default Home;