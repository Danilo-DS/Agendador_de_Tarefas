import {useEffect, useState} from 'react';
import {Link} from 'react-router-dom';
import {tabelaUsuario} from '../../../util';
import listaUserIcon from '../../../assets/icons/ListaUserIcon.svg';
import TituloPage from '../../../components/TituloPage';
import TableView from '../../../components/TableView';
import UsuarioService from '../../../services/UsuarioService';
import Button from '../../../components/Button';
import ContextFactory from '../../../util/ContextFactory'
import context from 'react-bootstrap/esm/AccordionContext';

var listUser = [];

const contextUsuario = [{nome : "", email : "", telefone : "", celular: "", tipoUsuario : ""}];

function ListaUsuario(){
    
    const [constext, setContext] = useState(contextUsuario);

    useEffect(() => {
        UsuarioService.getUsuarios().then(function(resp){
            if(listUser.length != resp.data.length){
              
                listUser = []
                resp.data.forEach(obj => {
                    listUser.push(obj) 
                })             
            }
            setContext(listUser)
        }).catch(function(resp){
            console.error("Erro ao carregar lista de usuarios:")
            console.error("Error " + resp.message)
        });
    })
       
    return(
        
        <div className="container">
			
            <TituloPage descricao = "Lista de Usuários" subDescricao = "Usuários Cadastrados" icon = {listaUserIcon} 
            componente = { <Link to="/cadastrar-usuario" className = "btn btn-outline-light float-right" id = "btnChamaCadastro">Cadastrar Usuario</Link> }/>

            <ContextFactory.Provider value = {[context, setContext]}>
                <TableView titulo1 = 'Nome' titulo2 = 'E-mail' titulo3 = 'Tel/Cel' titulo4 = 'Domínio' entidade = {listUser} tipoTabela = {tabelaUsuario}
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

export default ListaUsuario;