// Url Base
export const urlBase = "http://localhost:8080/api/v1";

// Autenticação
export const auth = "/auth/token";

// Usuario
export const listarUsuarios = "/usuario";


export const listarUsuariosR = "/usuario/";
export const responsavel = "/empresa"

export const listarUsuariosPorE = "/usuario/";
export const empresaU = "/empresa"

export const SalvarUsuario = "/usuario";

export const buscarUsuario = "/usuario/";

export const atualizarUsuario = "/usuario/";

export const deletarUsuarios = "/usuario";

//Tarefas
export const listarTarefas = "/agendar";

export const listarTarefasPorE = "/agendar/";
export const empresaT = "/empresa";

export const listarTarefaPorU = "/agendar/";
export const usuarioT = "/usuario";

export const buscarTarefa = "/agendar/";

export const salvarTarefa = "/agendar/tarefa";

export const salvarReuniao = "/agendar/reuniao";

export const atualizarTarefa = "/agendar";

export const deletarTarefa = "/agendar/";

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

export function isEmpresaOuGestor(){
    let permissaoUsuario = localStorage.getItem('permissao');
    return permissaoUsuario.includes("ROLE_EMP") || permissaoUsuario.includes("ROLE_GST");
}

//Salvar Credenciais
export function salvarCredenciais(credenciais){
    localStorage.setItem("id",JSON.stringify(credenciais.id));
    localStorage.setItem("email",JSON.stringify(credenciais.email));
    localStorage.setItem("empresa",JSON.stringify(credenciais.empresa === undefined ? 0 : credenciais.empresa));
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