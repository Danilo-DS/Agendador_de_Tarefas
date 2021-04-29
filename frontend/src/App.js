import {BrowserRouter, Route} from 'react-router-dom'
import Footer from './components/Footer';
import Routers from './router/Router';
import PageNotFound from './pages/PageNotFound';

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
