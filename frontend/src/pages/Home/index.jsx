import TableView from '../../components/TableView';
import TituloPage from '../../components/TituloPage';
import agendadasIcon from '../../assets/icons/AgendadasIcon.svg';

function Home(){
    return(
		<div className="container">
			
		<TituloPage descricao = "Tarefas Agendadas" subDescricao = "Suas Tarefas" icon = {agendadasIcon} componente = {null}/>

		<TableView titulo1 = 'Nome' titulo2 = 'E-mail' titulo3 = 'Tel/Cel' titulo4 = 'Domínio' entidade = {[]}/>
			
		</div>
    );
}

export default Home;