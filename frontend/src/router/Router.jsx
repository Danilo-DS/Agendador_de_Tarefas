import {Switch, Route} from 'react-router-dom';
import Home from '../pages/Home';
import Agendar from '../pages/Agendar';
import ListarUsuarios from '../pages/Usuario/ListaUsuario';
import CadastrarUsuarios from '../pages/Usuario/CadastroUsuario';
import Login from '../pages/Login';
import NavBar from '../components/NavBar';
import SemPermissao from '../pages/PageSemPermissao';
import CadastrarEmpresa from '../pages/Empresa/CadastrarEmpresa';
import {PrivateRoutes} from './AuthRoutes';
import {loginPath, semPermissaoPath, homePath, cadastrarEmpresaPath,
       listaUsuarioPath, cadastarUsuarioPath, cadastarTarefaPath} from '../util';
import PageNotFound from '../pages/PageNotFound';


function Routes(){
    return(
        <Switch>
            <Route path = {loginPath} exact component={Login}/>
            <Route path = {cadastrarEmpresaPath} exact component={CadastrarEmpresa}/>
            <Route path = "/agendarEdit"  component = {Agendar}/>
            <PrivateRoutes path = "/user" component = {CadastrarUsuarios}/>

            <NavBar>
                <PrivateRoutes path = {homePath} component = {Home}/>       
                <PrivateRoutes path = {cadastarTarefaPath} component = {Agendar}/>
                <PrivateRoutes path = {cadastarUsuarioPath} component = {CadastrarUsuarios}/>
                <PrivateRoutes path = {listaUsuarioPath} component = {ListarUsuarios}/>
                <Route path = {semPermissaoPath} component = {SemPermissao}/>
                {/* <Route path = "*"  component = {PageNotFound}/> */}
                {/* <Route path = "/alert"  component = {Alerte}/>    */}
            </NavBar>            
        </Switch>
    );

}

export default Routes;