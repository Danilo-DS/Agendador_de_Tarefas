function Label(props){
    return(
    <label htmlFor={props.name}>{props.descricao}</label>
    );
}

export default Label;