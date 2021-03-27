import {NavDropdown}  from 'react-bootstrap';
import {Link} from 'react-router-dom';
function NavBar(props){
    return(
		<div>
			<nav className="navbar navbar-expand-md fixed-top navbar-dark bg-dark">
				<Link  to="/home" className="navbar-brand">
				<h1 className="h1 mb-3 font-weight-normal">
					Home
				</h1>
				</Link>
		
				<div className="navbar-collapse offcanvas-collapse"	id="navbarsExampleDefault">
					<ul className="navbar-nav mr-auto">
						<li className="nav-item ">
							<Link to="/listar-usuarios" className="nav-link" >Usuários</Link></li>
						<li className="nav-item">
							<Link to="/agendar" className="nav-link"> Agendar Tarefa</Link>
						</li>
					</ul>
					<ul className="navbar-nav" style={{marginRight: "10%"}}>
					<NavDropdown title="Opções" id="collasible-nav-dropdown">
						<NavDropdown.Item href="#action/3.1">Perfil</NavDropdown.Item>
						<NavDropdown.Divider />
						<NavDropdown.Item href="/">Sair</NavDropdown.Item>
					</NavDropdown>
					</ul>
				</div>
			</nav>

			{props.children}
		</div>
    );
}

export default NavBar;