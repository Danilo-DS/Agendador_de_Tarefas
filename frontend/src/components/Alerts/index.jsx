import {Alert} from 'react-bootstrap'
import { useState } from 'react'

function Alerte(props){
    // <div className="alert alert-success" style="margin: 2% 10% 0 10%; display: none;" id ="alertSucesso" role="alert">
    //     <h2 id="sucesso" style="text-align: center; font-size: 1.4rem"></h2>
    // </div>
    //const [show, setShow] = useState(true);

    function autoCloseAlert(exibir){
        // if(exibir == true){
        //     setTimeout(function() {
        //         setShow(false)
        //       }, 2000);
        // }
    }

    return(
        <Alert variant = "success" onClose = {() => autoCloseAlert(props.exibir)} dismissible> 
            {/*props.mensagem*/}
            teste
        </Alert>
    ) 
}

export default Alerte;