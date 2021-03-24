import Label from '../../Label';

function SelectOption(props){

    return(
            <div className={props.classe}>
                <Label name={props.nmLabel} descricao = {props.descLabel}/> 
                <select className="custom-select d-block w-100" id={props.id}>
                    <option value={props.valorOption}>{props.descOption}</option>
                    {
                        props.arrayOption.map((item) => 
                            <option>{item.descricao}</option>)
                    }
                </select>
            </div>
        );
}

export default SelectOption;