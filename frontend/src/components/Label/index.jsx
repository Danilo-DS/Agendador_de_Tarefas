function Label(props){
    return(
        <label className = {props.classe} htmlFor={props.name}>{props.descricao}</label>
    );
}

export default Label;