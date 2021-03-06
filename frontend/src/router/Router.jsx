import {Switch, Route} from 'react-router-dom';
import Home from '../pages/Home';
import Agendar from '../pages/Agendar';
import ListarUsuarios from '../pages/Usuario/ListaUsuario';
import CadastrarUsuarios from '../pages/Usuario/CadastroUsuario';


function Routes(){
    return(
        <Switch>
            <Route path = '/' exact component={Home}/>
            <Route path = '/agendar' component={Agendar}/>
            <Route path = '/cadastrar-usuario' component={CadastrarUsuarios}/>
            <Route path = '/listar-usuarios' component={ListarUsuarios}/>
        </Switch>
    );

}

export default Routes;