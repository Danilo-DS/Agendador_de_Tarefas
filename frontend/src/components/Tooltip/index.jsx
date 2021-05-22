import {OverlayTrigger, Tooltip} from 'react-bootstrap';

function Tooltips(props){
    return(
        <OverlayTrigger 
            placement={props.posicao}
            delay = {{ show: 250, hide: 400 }}
            overlay={
                <Tooltip id="button-tooltip">
                    {props.descricao}
                </Tooltip>
            }>
               {props.componente}
        </OverlayTrigger>
    );
}
export default Tooltips;
