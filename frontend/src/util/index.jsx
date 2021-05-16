// Url Base
export const urlBase = "http://localhost:8080/api/v1";

// Autenticação
export const auth = "/auth/token";

// Usuario
export const listarUsuarios = "/usuario";

export const listarUsuariosR = "/usuario/";
export const responsavel = "/empresa"

export const SalvarUsuario = "/usuario";

export const buscarUsuario = "/usuario/";

export const atualizarUsuario = "/usuario/";

export const deletarUsuarios = "/usuario";

//Tarefas
export const listarTarefas = "/agendar-tarefa";

export const listarTarefasPorE = "/agendar-tarefa/";
export const empresaT = "/empresa";

export const listarTarefaPorU = "/agendar-tarefa/";
export const usuarioT = "/usuario";

export const buscarTarefa = "/agendar-tarefa/";

export const salvarTarefa = "/agendar-tarefa";

export const atualizarTarefa = "/agendar-tarefa";

export const deletarTarefa = "/agendar-tarefa/";

//Montar Header
export default function montaHerader(){
    let type = localStorage.getItem('type');
    let token = localStorage.getItem('token');
    let header = {headers:{"Authorization" : (type + token).replace(/"/g, "")}}
    return header;
}

export function isMaster(){
    let permissaoUsuario = localStorage.getItem('permissao');
    return permissaoUsuario.includes("ROLE_MST");
}

//Salvar Credenciais
export function salvarCredenciais(credenciais){
    localStorage.setItem("id",JSON.stringify(credenciais.id));
    localStorage.setItem("email",JSON.stringify(credenciais.email));
    localStorage.setItem("empresa",JSON.stringify(typeof credenciais.empresa === undefined ? 0 : credenciais.empresa));
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
export const cadastrarEmpresaPath = "/cadastrar-empresa";
export const semPermissaoPath = "/unothorized";

//Tipo Tabela
export const tabelaUsuario = "usuario";
export const tabelaTarefa = "tarefa";