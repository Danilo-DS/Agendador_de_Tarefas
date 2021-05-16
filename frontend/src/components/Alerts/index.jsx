import {Alert} from 'react-bootstrap'
import { useState } from 'react'
import { Redirect } from 'react-router';
import { homePath } from '../../util';

function Alerte(props){
    // <div className="alert alert-success" style="margin: 2% 10% 0 10%; display: none;" id ="alertSucesso" role="alert">
    //     <h2 id="sucesso" style="text-align: center; font-size: 1.4rem"></h2>
    // </div>
    const [show, setShow] = useState(props.estado);

    function autoCloseAlert(exibir){
        if(exibir == true){
            setTimeout(function() {
                setShow(false)
              }, 2000);
        }
    }

    function retornarHome(){
        setTimeout(function() {
            setShow(false);
            <Redirect to = {homePath}/>
          }, 2000);
    }
    

    if(show){
        return(
            <Alert id= "alert-action" variant = {props.tipo} onClose = {() => retornarHome()} dismissible> 
                {props.mensagem}
            </Alert>
        )
    } 
}

export default Alerte;