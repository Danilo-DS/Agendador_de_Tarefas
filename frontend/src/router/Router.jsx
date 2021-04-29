import {Switch, Route} from 'react-router-dom';
import Home from '../pages/Home';
import Agendar from '../pages/Agendar';
import ListarUsuarios from '../pages/Usuario/ListaUsuario';
import CadastrarUsuarios from '../pages/Usuario/CadastroUsuario';
import Login from '../pages/Login';
import NavBar from '../components/NavBar';
import {PrivateRoutes} from './AuthRoutes';
import {loginPath} from '../util';
import {homePath} from '../util';
import {listaUsuarioPath} from '../util';
import {cadastarUsuarioPath} from '../util';
import {cadastarTarefaPath} from '../util';
import Alerte from '../components/Alerts'
import PageNotFound from '../pages/PageNotFound';


function Routes(){
    return(
        <Switch>
            <Route path = {loginPath} exact component={Login}/>
            
            <NavBar>
                <PrivateRoutes path = {homePath} component = {Home}/>       
                <PrivateRoutes path = {cadastarTarefaPath} component = {Agendar}/>
                <PrivateRoutes path = {cadastarUsuarioPath} component = {CadastrarUsuarios}/>
                <PrivateRoutes path = {listaUsuarioPath} component = {ListarUsuarios}/>
                {/* <Route path = "*"  component = {PageNotFound}/> */}
                {/* <Route path = "/alert"  component = {Alerte}/>    */}
            </NavBar>            
        </Switch>
    );

}

export default Routes;