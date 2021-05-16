import { Modal, ListGroup } from "react-bootstrap";
import { useState } from "react";
import {BsPersonSquare} from 'react-icons/bs'
import Button from "../Button";



function ModalList(props) {
  const [exibir, setExibir] = useState(false);

  const fecharModal = () => setExibir(false);
  const abrirModal = () => setExibir(true);
  
  return (
    <>
      <Button classe = "btn btn-secondary" id = "btnAbrirMList" acaoButton = {abrirModal} 
          valorButton = {null} tipo = "button" descricao = {<BsPersonSquare/>}/>

      <Modal show={exibir} onHide={abrirModal}>
        <Modal.Header>
          <Modal.Title>{props.titulo}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <ListGroup>
              {props.lista.map((item) =>         
                  <ListGroup.Item>{item.nome}</ListGroup.Item>
              )}
          </ListGroup>
        </Modal.Body>
        <Modal.Footer>
          <Button classe = "btn btn-danger btn-lg btn-block" id = "btnFecharMList" acaoButton = {fecharModal} 
                valorButton = {null} tipo = "button" descricao = "Fechar"/>
        </Modal.Footer>
      </Modal>
    </>
  );
  }
  
  //render(<Example />);

  export default ModalList;