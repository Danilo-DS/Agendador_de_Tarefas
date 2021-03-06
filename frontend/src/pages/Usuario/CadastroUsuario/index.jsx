import cadastroUserIcon from '../../../assets/icons/CadastroUserIcon.svg';
import TituloPage from '../../../components/TituloPage';
import Input from '../../../components/Inputs/Normal Input';
import Select from '../../../components/Inputs/Select-Option';
import Button from '../../../components/Button';
import './style.css';
function CadastroUsuario(){
    return(
        <div className="container">
			
		    <TituloPage descricao = "Cadastrar Usuario" subDescricao = "Preencha os Campos" icon = {cadastroUserIcon} componente = {null}/>

            <form className = "needs-validation">
                <div className="row">
                    <Input classe = "col-md-6 mb-3" nmLabel = "nome" descLabel = "Nome" tipo = "text"
                    id = "nomeCompleto" legenda = "Informe o Nome Completo" valorInput = {undefined} tamanho = "54" restricao = {null} componente = {null}/>

                    <Input classe = "col-md-3 mb-3" nmLabel = "telefone" descLabel = "Telefone Fixo" tipo = "number"
                    id = "telefone" legenda = "Número de Telefone" valorInput = {undefined} tamanho = "10" 
                    restricao = "javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);" componente = {null}/>

                    <Input classe = "col-md-3 mb-3" nmLabel = "celular" descLabel = "Celular" tipo = "number"
                    id = "celular" legenda = "Número de Celular" valorInput = {undefined} tamanho = "10" 
                    restricao = "javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);" componente = {null}/>
                </div>

                <div className="row">
                    <Input classe = "col-md-6 mb-3" nmLabel = "email" descLabel = "E-mail" tipo = "email"
                        id = "email" legenda = "Informe o E-mail" valorInput = {undefined} tamanho = "59" restricao = ""
                        componente = { <span class="input-group-text">@</span> }
                        />

                    <Input classe = "col-md-6 mb-3" nmLabel = "senha" descLabel = "Senha" tipo = "password"
                    id = "telefone" legenda = "Informe a Senha" valorInput = {undefined} tamanho = "23" restricao = {null} componente = {null}/>
                </div>

                <div className="row">
                    <Input classe = "col-md-6 mb-3" nmLabel = "dtNascimento" descLabel = "Data de Nascimento" tipo = "date"
                    id = "dt_Nascimento" legenda = "Informe a Data de Nascimento" valorInput = {undefined} tamanho = "59" restricao = {null} componente = {null}/>

                    <Select classe = "col-md-6 mb-3" nmLabel = "tipo" descLabel = "Tipo Usuário"
                    id= "tipo" valorOption = "0" descOption = "Selecione o Tipo de Usuário" arrayOption = {[]}/>
                </div>

                <Input classe = "col-md-12 mb-3" nmLabel = "endereco" descLabel = "Endereço" tipo = "text"
                    id = "endereco" legenda = "Informe seu Endereço" valorInput = {undefined} tamanho = "254" restricao = {null} componente = {null}/>
                
                <hr class="mb-4"/>

                <Button classe = "btn btn-primary btn-lg btn-block" id = "btnCadastrarUsuario" acaoButton = "" 
                 valorButton = {null} tipo = "button" descricao = "Realizar Cadastro" />

            </form>	
		</div>
    );
}

export default CadastroUsuario;