function ScrollBar(props){

    return(
        <div className={props.classe} id = {props.id} style = {props.estilo}>
            {props.componente}
        </div>
    )
}

export default ScrollBar;