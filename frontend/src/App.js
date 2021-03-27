import {BrowserRouter, Route} from 'react-router-dom'
import NavBar from './components/NavBar';
import Footer from './components/Footer';
import Routers from './router/Router';
import LoginPage from './pages/Login';

function App() {
  return (
    <BrowserRouter>
      <div className="bg-light" style={{height: "100vh"}}>
        <br/><br/><br/><br/>

        <Routers/>

        <Footer/>
      </div>
    </BrowserRouter>
  );
}

export default App;
