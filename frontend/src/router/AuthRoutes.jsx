import { Route, Redirect } from "react-router-dom";
import {homePath} from '../util';

const permissao = (path) => {
    let pathsUser = [homePath]
    let permissaoUsuario = localStorage.getItem('permissao').replace(/"/g, "");
    let autorizado = false;

    if( permissaoUsuario == "ROLE_U"){
        pathsUser.forEach(e => {
            if(e == path){
                autorizado = true;
            }
        })
        return autorizado;
    }else{
        autorizado = true
        return autorizado;
    }
}

export const PrivateRoutes = ({component : Component, ...rest}) => {
    return <Route {...rest} 
        render = {props => 
            permissao(rest.path) ? 
            (<Component {...props} />)
            :
            (<Redirect to = {{pathname : "/", state : {from : props.location} }}/>) 
            
        }
    />
}