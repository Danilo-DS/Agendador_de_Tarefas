import { useEffect, useState } from 'react';
import FadeIn from 'react-fade-in';
import agendarIcon from '../../assets/icons/AgendarIcon.svg';
import TituloPage from '../../components/TituloPage';
import Input from '../../components/Inputs/Normal Input';
import TextArea from '../../components/Inputs/Text-Area';
import Select from '../../components/Inputs/Select-Option';
import Button from '../../components/Button';
import ScrollBar from '../../components/ScrollBar';
import CheckBox from '../../components/Inputs/CheckBox';
import Label from '../../components/Label';
import RadioButton from '../../components/Inputs/Radio';
import TarefaService from '../../services/TarefaService';
import ContextFactory from '../../util/ContextFactory';
import { isEmpresaOuGestor } from '../../util';
import './style.css';


const prioridadesOptions = [{descricao:'ALTA'},{descricao:'MEDIA'},{descricao:'BAIXA'}];
const contextUserResponsaveis = [{descricao : "", valor : ""}];
var listaResponsaveis = [];


function CadastroAgendamento(){
    const [context, setContext] = useState(contextUserResponsaveis);
    
    useEffect(()=> {
        tipoAgendamento(1);
        
		TarefaService.getUsuarioResponsaveis().then(function(resp){
			if(listaResponsaveis.length != resp.data.length){
				resp.data.forEach(obj => {
					listaResponsaveis.push({descricao : obj.nome + " - " + (obj.tipoUsuario === "GST" ? "GESTOR" : "FUNCIONARIO"), valor : obj.id});
                });
                setContext(listaResponsaveis);
			}
		}).catch(function(resp){
            console.error("Erro ao carregar responsaveis!");
            console.error("Codigo Http: ", resp.status);
            console.error("Msg: ", resp.statusText);
        });
	})

    return(
        <div className="container">
			
            <FadeIn>
		        <TituloPage descricao = "Agendar Tarefa" subDescricao = "Preencha os campos" icon = {agendarIcon} componente = {null}/>
            
                <form className = "needs-validation">
                    <Input classe = "col-md-12 mb-3" nmLabel = "tituloTarefa" descLabel = "Titulo da Tarefa" tipo = "text"
                    id = "tituloTarefa" legenda = "Título da Tarefa" valorInput = {undefined} tamanho = "64" restricao = {null} componente = {null}/>

                    <TextArea classe = "col-md-12 mb-3" nmLabel = "descricaoTarefa" descLabel = "Descrição da Tarefa"
                    id = "descricaoTarefa" legenda = "Descreva da Tarefa" valorText = {undefined} tamanho = "254"/>

                    <div className="row">

                        <div className = "col-md-8 mb-3">
                            <Label name= "selecaoUsuario" descricao = "Selecione os Usuarios Responsáveis"/>
                            <ScrollBar id = "scrollTAgendar" classe = "overflow-auto card" 
                                componente = {
                                    <ContextFactory.Provider value = {[context, setContext]}>
                                        <CheckBox isLista = {true} objCheck = {listaResponsaveis} acaoCheck = {()=>setarResponsavel()}/>
                                    </ContextFactory.Provider>
                                }
                                estilo = {{height : "110px"}}/>
                        </div>

                        <div className = "col-md-4 mb-3">
                            <Label name= "tipoTarefa" descricao = "Tipo Agendamento"/>
                            <div className="row">  
                                <RadioButton classeDiv = "form-check col-md-4 mb-3" classeRadio = "form-check-input" nome = "radioTarefa"
                                    id = "rdTarefa" descricao = "Atividade" isChecked = {true}
                                    acao = {() => tipoAgendamento(1)} />
                                
                                <RadioButton classeDiv = "form-check col-md-4 mb-3" classeRadio = "form-check-input" nome = "radioReuniao"
                                    id = "rdReuniao" descricao = "Reunião"
                                    acao = {() => tipoAgendamento(2)} />
                            </div>

                            <Select nmLabel = "nivelDePrioridade" descLabel = "Nivel de Prioridade"
                            id= "prioridade" valorOption = "0" descOption = "Selecione a Prioridade" arrayOption  = {prioridadesOptions}/>
                        </div>
                    </div>

                    {/* <Label classe="col-md-12 mb-3" name= "sessaoConvReunicao" descricao = "Sessão Reunião"/> */}

                    <div className="row">
                        <Input classe = "col-md-3 mb-3" nmLabel = "dataReuniao" descLabel = "Data da Reunião" tipo = "date"
                            id = "dtReuniao" legenda = "Informe a Data da Reuniao" valorInput = {undefined} tamanho = {null} restricao = {null} componente = {null}/>

                        <Input classe = "col-md-9 mb-3" nmLabel = "convidadosEmail" descLabel = "Informe os E-mail's dos Convidados" tipo = "text"
                        id = "convidadosEmail" legenda = "Informe os e-mail's separados por vírgula" valorInput = {undefined} tamanho = "5000" restricao = {null} componente = {null}/>
                    </div>

                    <Input classe = "col-md-12 mb-3" nmLabel = "convidadosTel" descLabel = "Informe os Telefones dos Convidados" tipo = "text"
                    id = "convidadosTelefone" legenda = "Informe os telefones separado por vírgula" valorInput = {undefined} tamanho = "5000" restricao = {null} componente = {null}/>

                    <hr className="mb-4"/>

                    <Button classe = "btn btn-primary btn-lg btn-block" id = "btnCadastrarTarefa" acaoButton = {()=> iniciaCadastro()} 
                    valorButton = {null} tipo = "button" descricao = "Realizar Agendamento" />
                </form>
            </FadeIn>	
		</div>
        
    );
}

export default CadastroAgendamento;

function tipoAgendamento(tipo){
    if(tipo === 1){
        document.getElementById("rdReuniao").checked = false;
        document.getElementById("dtReuniao").disabled = true;
        document.getElementById("convidadosEmail").disabled = true;
        document.getElementById("convidadosTelefone").disabled = true;
        document.getElementById("convidadosEmail").value = "";
        document.getElementById("dtReuniao").value = "";
        document.getElementById("convidadosTelefone").value = "";
        document.getElementById("prioridade").disabled = false;

        let checks = document.getElementById("scrollTAgendar").getElementsByTagName("label");
        
        for(var node of checks){
            node.getElementsByTagName("input")[0].disabled = false;
        }
    }
    else{
        document.getElementById("rdTarefa").checked = false;
        document.getElementById("prioridade").value = 0;
        document.getElementById("prioridade").disabled = true;
        document.getElementById("dtReuniao").disabled = false;
        document.getElementById("convidadosEmail").disabled = false;
        document.getElementById("convidadosTelefone").disabled = false;
        
        let checks = document.getElementById("scrollTAgendar").getElementsByTagName("label");
        
        for(var node of checks){
            node.getElementsByTagName("input")[0].checked = false;
            node.getElementsByTagName("input")[0].disabled = true;
        }
    }
}

function iniciaCadastro(){
    let isReuniao = document.getElementById("rdReuniao").checked ? true : false
    if(isReuniao){
        cadastraReuniao();
    }
    else{
        cadastrarAtividade();
    }
}

function cadastraReuniao(){
    let reuniao = {}
    if(isEmpresaOuGestor()){
        reuniao = {
            titulo : document.getElementById("tituloTarefa").value,
            descricao : document.getElementById("descricaoTarefa").value,
            dtReuniao : document.getElementById("dtReuniao").value,
            empresa : {
                id : parseInt(localStorage.getItem("empresa"))
            },
            convidadosEmail : formatarEmailConvidados()[0],
            convidadosTelefone :formatarTelefoneConvidados()[0]
        }
    }
    else{
        reuniao = {
            titulo : document.getElementById("tituloTarefa").value,
            descricao : document.getElementById("descricaoTarefa").value,
            dtReuniao : document.getElementById("dtReuniao").value,
            usuario: {id : parseInt(localStorage.getItem('id'))},
            convidadosEmail : formatarEmailConvidados()[0],
            convidadosTelefone :formatarTelefoneConvidados()[0]
        } 
    }
    console.log(reuniao);
    TarefaService.saveReuniao(reuniao).then(function(resp){
        console.info("Reuniao Registrada com Sucesso!");
        console.info("Codigo Http: ", resp.status);
        console.info("Msg: ", resp.statusText);
    }).catch(function(resp){
        console.error("Ocorreu um erro ao salvar reuniao!");
        console.error("Codigo Http: ", resp.status);
        console.error("Msg: ", resp.statusText);
    });

}

function cadastrarAtividade(){
    let atividade = {};
    if(isEmpresaOuGestor()){
        atividade = {
            titulo : document.getElementById("tituloTarefa").value,
            descricao : document.getElementById("descricaoTarefa").value ,
            usuario:setarResponsavel(),
            empresa : {
                id : parseInt(localStorage.getItem("empresa"))
            },
            prioridade : document.getElementById("prioridade").value
        }
    }
    else{
        atividade = {
            titulo : document.getElementById("tituloTarefa").value,
            descricao : document.getElementById("descricaoTarefa").value ,
            usuario:[{id: parseInt(localStorage.getItem("id"))}],
            prioridade : document.getElementById("prioridade").value
        }
    }
console.info(atividade);
    TarefaService.saveAtividade(atividade).then(function(resp){
        console.info("Atividade Registrada com Sucesso!");
        console.info("Codigo Http: ", resp.status);
        console.info("Msg: ", resp.statusText);
    }).catch(function(resp){
        console.error("Ocorreu um erro ao salvar a atividade!");
        console.error("Codigo Http: ", resp.status);
        console.error("Msg: ", resp.statusText);
    });
}

function setarResponsavel(){
    let responsaveis = [];
    let checks = document.getElementById("scrollTAgendar").getElementsByTagName("label");
        
    for(var node of checks){
        let marcado = node.getElementsByTagName("input")[0].checked ? true : false;
        if(marcado){
            responsaveis.push({id : parseInt(node.getElementsByTagName("input")[0].value)});
        }
    }
    return responsaveis;
}

function formatarEmailConvidados(){
    let convidados = document.getElementById("convidadosEmail").value

    return[convidados.replace(/ /g,"").split(",")]
}

function formatarTelefoneConvidados(){
    let convidados = document.getElementById("convidadosTelefone").value

    return[convidados.replace(/ /g,"").split(",")]
}