// Url Base
export const urlBase = "http://localhost:8080/api/v1";

// Autenticação
export const auth = "/auth/token";

// Usuario
export const listarUsuarios = "/usuarios";

export const SalvarUsuario = "/usuarios";

export const buscarUsuario = "/usuarios/";

export const atualizarUsuario = "/usuarios/";

export const deletarUsuarios = "/usuarios";

//Tarefas
export const listarTarefas = "/agendar-tarefa";

export const listarTarefaPor = "/agendar-tarefa/";
export const usuarios = "/usuario";

export const buscarTarefa = "/agendar-tarefa/";

export const salvarTarefa = "/agendar-tarefa";

export const atualizarTarefa = "/agendar-tarefa";

export const deletarTarefa = "/agendar-tarefa/";

//Mount Header
export default function montaHerader(){
    let type = localStorage.getItem('type');
    let token = localStorage.getItem('token');
    let header = {headers:{"Authorization" : (type + token).replace(/"/g, "")}}
    return header;
}

//Save Credenciais
export function salvarCredenciais(credenciais){
    localStorage.setItem("id",JSON.stringify(credenciais.id));
    localStorage.setItem("email",JSON.stringify(credenciais.email));
    localStorage.setItem("permissao",JSON.stringify(credenciais.permissao)); 
    localStorage.setItem("token",JSON.stringify(credenciais.token));
    localStorage.setItem("type",JSON.stringify(credenciais.type));
}

//Delete Credenciais
export function deletarCredenciais(){
    localStorage.clear();
}

// Path's Front
export const loginPath = "/";
export const homePath = "/home";
export const listaUsuarioPath = "/listar-usuarios";
export const cadastarUsuarioPath = "/cadastrar-usuario";
export const cadastarTarefaPath = "/agendar";


//Type Table
export const tabelaUsuario = "user";
export const tabelaTarefa = "task";