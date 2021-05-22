import './style.css'

function Faixa(props){
    return(
        <div className="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded box-shadow row">
			
			<div className="col-md-1">
				<img src = {props.icon} alt= "Icone de Lista" id="IconListaTarefa" width="35" height="30" viewBox="0 0 16 16"/>
			</div>

			<div className="col-md-6 lh-100">
				<h6 className="mb-0 text-white lh-100">{props.descricao}</h6>
				<small>{props.subDescricao}</small>
			</div>

			<div className="col-md-5" id="divComponent">
				{props.componente}
			</div> 

		</div>
    )
}

export default Faixa; 