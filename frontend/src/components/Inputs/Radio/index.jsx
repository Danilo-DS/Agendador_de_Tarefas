function Radio(props){
    return(
        <div className={props.classeDiv}>
            <input className={props.classeRadio} type="radio" name = {props.nome} id={props.id} checked = {props.isChecked} onClick = {props.acao} />
                {props.descricao}
        </div>
    );
}

export default Radio;