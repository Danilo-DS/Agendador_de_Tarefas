import Label from '../../Label';

function Input(props){
    return(
        <div className={props.classe}>
            <Label name = {props.nmLabel} descricao = {props.descLabel}/>
            <div className="input-group">
                {props.componente}
                    <input type={props.tipo}
                        className="form-control" id = {props.id}
                        placeholder= {props.legenda} value= {props.valorInput} maxLength={props.tamanho} onInput= {props.restricao} required/>
            </div>
        </div>
    );
}

export default Input;