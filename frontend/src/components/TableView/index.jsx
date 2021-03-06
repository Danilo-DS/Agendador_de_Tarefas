
function TableView(props){
    return (
        <table className="table table-striped ">
					<thead>
						<tr>
							<th scope="col">Título</th>
							<th scope="col">Usuario Responsáviel</th>
							<th scope="col">Nível Prioridade</th>
							<th scope="col">Situação Tarefa</th>
						</tr>
					</thead>
					<tbody id = "table-view">
						
					</tbody>
				</table>
    );
}

export default TableView;
