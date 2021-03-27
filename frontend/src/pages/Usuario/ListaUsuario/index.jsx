import {useEffect} from 'react';
import {Link} from 'react-router-dom';
import listaUserIcon from '../../../assets/icons/ListaUserIcon.svg';
import TituloPage from '../../../components/TituloPage';
import TableView from '../../../components/TableView';
import UsuarioService from '../../../services/UsuarioService';
import Button from '../../../components/Button';

var listUser = [];
function ListaUsuario(){
    useEffect(() => {
            UsuarioService.getUsuarios2().then(function(resp){
                if(listUser.length != resp.data.length){
                    listUser = []
                    resp.data.forEach(obj => {
                    listUser.push(obj) 
                    })
                }
            }).catch(function(resp){
                console.error("Erro ao carregar lista de usuarios:")
                console.error("Error " + resp.data)
            });
    })
    console.log('Lista de usuario ' , listUser);
    return(
        <div className="container">
			
            <TituloPage descricao = "Lista de Usuários" subDescricao = "Usuários Cadastrados" icon = {listaUserIcon} 
            componente = { <Link to="/cadastrar-usuario" className = "btn btn-outline-light float-right" id = "btnChamaCadastro">Cadastrar Usuario</Link> }/>

            <TableView titulo1 = 'Nome' titulo2 = 'E-mail' titulo3 = 'Tel/Cel' titulo4 = 'Domínio' entidade = {listUser} 
                btnEditar = {<Button classe = "btn btn-info" id = "btnEditarUsuario" acaoButton = "" 
                valorButton = {null} tipo = "button" descricao = {<i className="fa fa-edit"></i>}/>}
                btnDeletar = {<Button classe = "btn btn-danger" id = "btnDeletarUsuario" acaoButton = "" 
                valorButton = {null} tipo = "button" descricao = {<i className="fa fa-trash"></i>}/>}
                />
			
		</div>
    );
}

export default ListaUsuario;