function CheckBox(props){

    function tipoCheckBox(isLista){
        if(isLista){
            return listaCheckBox();
        }
        return checkBox();
    }

    function checkBox(){
        return (
            <label className="list-group-item espaco-label">
                <input className="form-check-input " type="checkbox" value={props.valor}/>
                {props.descricao}
            </label>
        )
    }

    function listaCheckBox(){
        return props.objCheck.map((item) => 
            <label className="list-group-item espaco-label">
                <input className="form-check-input" type="checkbox" value={item.valor} onClick = {props.acaoCheck}/>
                {item.descricao}
            </label>
        )
    }

    return tipoCheckBox(props.isLista);
    
}

export default CheckBox;