function ScrollBar(props){

    return(
        <div className={props.classe} style = {props.estilo}>
            {props.componente}
        </div>
    )
}

export default ScrollBar;