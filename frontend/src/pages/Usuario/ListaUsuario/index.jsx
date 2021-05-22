import {useEffect, useState} from 'react';
import FadeIn from 'react-fade-in';
import  {BsFillTrashFill, BsPencil} from 'react-icons/bs'
import {Link} from 'react-router-dom';
import {tabelaUsuario} from '../../../util';
import listaUserIcon from '../../../assets/icons/ListaUserIcon.svg';
import TituloPage from '../../../components/TituloPage';
import TableView from '../../../components/TableView';
import UsuarioService from '../../../services/UsuarioService';
import Button from '../../../components/Button';
import ContextFactory from '../../../util/ContextFactory'
import context from 'react-bootstrap/esm/AccordionContext';

var listaUsuario = [];

const contextUsuario = [{nome : "", email : "", telefone : "", celular: "", tipoUsuario : ""}];

function ListaUsuario(){
    
    const [constext, setContext] = useState(contextUsuario);

    useEffect(() => {
        UsuarioService.getUsuarios().then(function(resp){
            if(listaUsuario.length != resp.data.length){
                resp.data.forEach(obj => {
                    listaUsuario.push(obj) 
                })             
            }
            setContext(listaUsuario);
            console.info("Carregamento Bem Sucedido");
			console.info("Codigo Http: ", resp.status);
			console.info("Msg: " , resp.statusText);
        }).catch(function(resp){
            console.error("Erro ao carregar lista de usuarios:");
            console.error("Codigo Http: " , resp.status);
            console.error("Msg: " , resp.statusText);
        });
    })
       
    return(
        
        <div className="container">			
            <FadeIn>
                <TituloPage descricao = "Lista de Usuários" subDescricao = "Usuários Cadastrados" icon = {listaUserIcon} 
                componente = { <Link to="/cadastrar-usuario" className = "btn btn-outline-light float-right" id = "btnChamaCadastro">Cadastrar Usuario</Link> }/>
            
                <ContextFactory.Provider value = {[context, setContext]}>
                    <TableView titulo1 = 'Nome' titulo2 = 'E-mail' titulo3 = 'Tel/Cel' titulo4 = 'Domínio' entidade = {listaUsuario} tipoTabela = {tabelaUsuario}
                        btnEditar = {
                            <Button classe = "btn btn-info" id = "btnEditarUsuario" acaoButton 
                            valorButton = {null} tipo = "button" descricao = {<BsPencil/>}/>
                        }
                        btnDeletar = {
                            <Button classe = "btn btn-danger" id = "btnDeletarUsuario" acaoButton 
                            valorButton = {null} tipo = "button" descricao =  {<BsFillTrashFill/>}/>
                        }
                    />
                </ContextFactory.Provider>
			</FadeIn>
		</div>
    );
    
}

export default ListaUsuario;