
function TableView(props){	
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
						{
							props.entidade.map((item) => 
								<tr>
									<td>{item.nome}</td>
									<td>{item.email}</td>
									<td>{item.telefone + ' / ' + item.celular}</td>
									<td>{item.tipoUsuario}</td>
									<td>{props.btnEditar}</td>
									<td>{props.btnDeletar}</td>
									<td>{props.btnFinalizar}</td>
									<td>{props.btnCancelar}</td>
								</tr>
							)
						}	
					</tbody>
		</table>
    );
}

export default TableView;
