import { Route, Redirect } from "react-router-dom";
import {homePath, semPermissaoPath} from '../util';

const permissao = (path) => {
    let pathsUser = [homePath];
    let permissaoUsuario = [];
    let autorizado = false;
    let permissaoTela;

    permissaoUsuario.push(localStorage.getItem('permissao'));
    permissaoUsuario.forEach(p => {
        let permissao = p.replace(/"/g,"").replace("[","").replace("]","");
        if(permissao === "ROLE_FUNC"){
            permissaoTela = permissao; 
        }
    })

    if(permissaoTela === "ROLE_FUNC" && permissaoUsuario.length === 1){
        pathsUser.forEach(e => {
            if(e === path){
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
            (<Redirect to = {{pathname : semPermissaoPath, state : {from : props.location}}} />) 
        }
    />
}