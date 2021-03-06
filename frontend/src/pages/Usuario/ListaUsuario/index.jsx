import {useEffect} from 'react';
import {Link} from 'react-router-dom';
import listaUserIcon from '../../../assets/icons/ListaUserIcon.svg';
import TituloPage from '../../../components/TituloPage';
import TableView from '../../../components/TableView';
import UsuarioService from '../../../services/UsuarioService';

function ListaUsuario(){
    var listUser;
    useEffect(() => {
        UsuarioService.getUsuarios().then(function(resp){
            console.log(resp.data);
        }).catch(function(resp){
            console.error("Erro ao carregar lista de usuarios:")
            console.error("Error " + resp.data)
        });
    });

    return(
        <div className="container">
			
        <TituloPage descricao = "Lista de Usuários" subDescricao = "Usuários Cadastrados" icon = {listaUserIcon} 
        componente = { <Link to="/cadastrar-usuario" className = "btn btn-outline-light float-right" id = "btnChamaCadastro">Cadastrar Usuario</Link> }/>

		<TableView/>
			
		</div>
    );
}

export default ListaUsuario;