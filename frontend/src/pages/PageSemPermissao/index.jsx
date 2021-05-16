import { Link } from "react-router-dom";
import { homePath } from "../../util";

function PageSemPermissao(){
    return(
        <div className="container">
            <div className="row justify-content-center">
                <div className="col-md-12 text-center">
                    <span className="display-1 d-block">401</span>
                    <div className="mb-4 lead">Desculpe, Você Não tem Permissão!</div>
                    <Link to = {homePath} className="btn btn-link">Clique aqui para voltar para home</Link>
                </div>
            </div>
        </div>
    )
}

export default PageSemPermissao;