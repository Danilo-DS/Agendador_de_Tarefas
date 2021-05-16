import {tabelaUsuario, tabelaTarefa} from '../../util'
import ModelList from '../ModalList';
function TableView(props){
	
	function TipoTabela(tipo){
		if(tipo === tabelaUsuario){
			return MontaBodyUsuario();
		}

		if(tipo === tabelaTarefa){
			return MontaBodyTarefa();
		}
	}
	
	function MontaBodyUsuario(){
		return props.entidade.map((item) => 
			<tr>
				<td>{item.nome}</td>
				<td>{item.email}</td>
				<td>{item.telefone + ' / ' + item.celular}</td>
				<td>{item.tipoUsuario}</td>
				<td>{props.btnEditar}</td>
				<td>{props.btnDeletar}</td>
			</tr>
		)
	}

	function MontaBodyTarefa(){
		 return	props.entidade.map((item) => 
			<tr>
				<td>{item.titulo}</td>
				<td align = "center"><ModelList titulo = "Usuarios Tarefa" lista = {item.usuario}/></td>
				<td>{item.prioridade}</td>
				<td>{item.statusTarefa}</td>
				<td>{props.btnFinalizar}</td>
				<td>{props.btnCancelar}</td> 
				<td>{props.btnEditar}</td>
				<td>{props.btnDeletar}</td>
			</tr>
		)
		 						

	}

	return (
        <table className="table table-striped ">
					<thead>
						<tr>
							<th scope="col">{props.titulo1}</th>
							<th scope="col">{props.titulo2}</th>
							<th scope="col">{props.titulo3}</th>
							<th scope="col">{props.titulo4}</th>
						</tr>
					</thead>
					<tbody id = "table-view">
						{TipoTabela(props.tipoTabela)}	
					</tbody>
		</table>
    );
}

export default TableView;
