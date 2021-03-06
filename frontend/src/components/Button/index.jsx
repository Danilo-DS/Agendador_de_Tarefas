function Button(props){
    return(
        <button className={props.classe}
            id={props.id} onClick={props.acaoButton} value = {props.valorButton} type = {props.tipo}>
                {props.descricao}
        </button>
    );
}

export default Button;