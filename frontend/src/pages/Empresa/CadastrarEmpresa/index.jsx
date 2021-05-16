import {loginPath} from '../../../util';
import arqEstado from '../../../assets/json/estados.json'
import { Link } from 'react-router-dom';
import empresaIcon from '../../../assets/icons/EmpresaIcon.svg';
import Input from '../../../components/Inputs/Normal Input';
import Select from '../../../components/Inputs/Select-Option';
import Button from '../../../components/Button';
import TituloPage from '../../../components/TituloPage';


function CadastrarEmpresa(){
    return(
        <div className = "container" style = {{marginTop:"-4.4rem"}}>

            <TituloPage descricao = "Cadastro de Empresa" subDescricao = "Realize o cadastro de sua empresa" icon = {empresaIcon} componente = {null}/>

            <form className = "needs-validation">

                <div className="row">
                    <Input classe = "col-md-6 mb-3" nmLabel = "nomeFantasia" descLabel = "Nome Fantasia" tipo = "text"
                    id = "nmFantasia" legenda = "Informe o Nome Fantasia" valorInput = {undefined} tamanho = "80" restricao = {null} componente = {null}/>

                    <Input classe = "col-md-6 mb-3" nmLabel = "razaoSocial" descLabel = "Razão Social" tipo = "text"
                    id = "rzSocial" legenda = "Informe a Razão Social" valorInput = {undefined} tamanho = "80" 
                    restricao = {null} componente = {null}/>
                </div>

                <div className="row">                    
                    <Input classe = "col-md-4 mb-3" nmLabel = "nuCnpj" descLabel = "CNPJ" tipo = "number"
                        id = "cnpjEmpresa" legenda = "Cnpj da Empresa" valorInput = {undefined} tamanho = "14" 
                        restricao = {null} componente = {null}/>

                    <Input classe = "col-md-4 mb-3" nmLabel = "inscEstadual" descLabel = "Inscrição Estadual" tipo = "number"
                        id = "insEstadual" legenda = "Insc. Estadual da Empresa" valorInput = {undefined} tamanho = "12" 
                        restricao = {null}
                        componente = {null}/>

                    <Input classe = "col-md-4 mb-3" nmLabel = "cel-tel" descLabel = "Celular/Telefone" tipo = "number"
                        id = "cel/tel" legenda = "Informe o Cel/Tel" valorInput = {undefined} tamanho = "10" restricao = ""
                        componente = {null}/>
                </div>

                <Input classe = "col-md-12 mb-3" nmLabel = "logradouro" descLabel = "Logradouro" tipo = "text"
                        id = "descLogradouro" legenda = "Logradouro" valorInput = {undefined} tamanho = "60" restricao = {null} componente = {null}/>

                <div className="row">
                    <Input classe = "col-md-5 mb-3" nmLabel = "bairro" descLabel = "Bairro" tipo = "text"
                        id = "descBairro" legenda = "Informe o Bairro" valorInput = {undefined} tamanho = "25" restricao = {null} componente = {null}/>

                    <Input classe = "col-md-4 mb-3" nmLabel = "numero" descLabel = "Numero" tipo = "number"
                        id = "descBairro" legenda = "Informe o Numero" valorInput = {undefined} tamanho = "5" 
                        restricao = {null} componente = {null}/>

                    <Input classe = "col-md-3 mb-3" nmLabel = "cep" descLabel = "CEP" tipo = "text"
                        id = "nuCep" legenda = "Informe o Cep" valorInput = {undefined} tamanho = "10" 
                        restricao = {null} componente = {null}/>
                </div>

                <div className="row">

                    <Select classe = "col-md-3 mb-3" nmLabel = "estado" descLabel = "Estado"
                        id= "siglaEstado" valorOption = "0" descOption = "Selecione o Estado" arrayOption = {arqEstado}/>

                    <Input classe = "col-md-9 mb-3" nmLabel = "cidade" descLabel = "Cidade" tipo = "text"
                        id = "descCidade" legenda = "Informe a Cidade" valorInput = {undefined} tamanho = "30" 
                        restricao = {null} componente = {null}/>

                </div>
                <Input classe = "col-md-12 mb-3" nmLabel = "complemento" descLabel = "Complemento" tipo = "text"
                    id = "descComplemento" legenda = "Informe o Complemento" valorInput = {undefined} tamanho = "254" restricao = {null} componente = {null}/>
                
                <div className="row">
                    <Input classe = "col-md-6 mb-3" nmLabel = "email" descLabel = "E-mail" tipo = "email"
                        id = "email" legenda = "Informe o E-mail" valorInput = {undefined} tamanho = "59" restricao = ""
                        componente = { <span class="input-group-text">@</span> }
                        />

                    <Input classe = "col-md-6 mb-3" nmLabel = "senha" descLabel = "Senha" tipo = "password"
                    id = "pass" legenda = "Informe a Senha" valorInput = {undefined} tamanho = "23" restricao = {null} componente = {null}/>
                </div>

                <hr className="mb-4"/>

                <Button classe = "btn btn-primary btn-lg btn-block" id = "btnCadastrarEmpresa" acaoButton = "" 
                 valorButton = {null} tipo = "button" descricao = "Realizar Cadastro" />
            </form>	 

            <div className = "text-center" align = "center" style = {{marginTop : "0.8rem"}}>
                <Link to = {loginPath} >Entrar com Usuario</Link>
            </div>
	  </div>

    );
}

export default CadastrarEmpresa;