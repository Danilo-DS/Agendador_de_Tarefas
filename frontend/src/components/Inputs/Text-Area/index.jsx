import Label from '../../Label';

function TextArea(props){
    return(
        <div className={props.classe}>
            <Label name ={props.nmLabel} descricao = {props.descLabel}/>
                <textarea className="form-control" id={props.id}
                    placeholder={props.legenda} value = {props.valorText}
                    maxLength={props.tamanho}></textarea>
        </div>
        );
}

export default TextArea;