import React, { useEffect, useState } from "react";
import "./../../style/Comprador.css"
import Pedido from "../Pedido";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';


export default function Comprador(props){
    
    const [pedidos, setPedidos] = useState(props.pedidos);
    const [codigoPedido, setCodigoPedido] = useState("");

    const empresas = props.pedidos.reduce(
        (previousValue, currentValue)=>{
            if(!previousValue.includes(currentValue.empresa)){
                previousValue.push(currentValue.empresa);
            }
            return previousValue;
    },[]);

    const filtraempresa = (emp) => {

        const resultado = props.pedidos;

        return resultado.filter((el)=>
        el.empresa.toString().toLowerCase().includes(emp.toString().toLowerCase()));
    }



    return(
        <div className="comprador">
            <h2 className="titulo">Página del Comprador</h2>
            <h3 className="titulo">Mis pedidos</h3>
            <div className="contenidocomprador">
                <div className="listaempresas">
                    <ul className="nav flex-column">
                            <p></p>
                            <li className="nav-item">
                                <img src="450px-EBay_logo.svg.png"  className="logoempresa" alt="logo"></img>
                                <button type="button" className="btn btn-primary btn-sm"
                                onClick={()=>setPedidos(filtraempresa("ebay"))}>Ver pedidos</button>
                            </li>
                            <li className="nav-item">
                                <img src="Amazon_logo.svg.png"  className="logoempresa" alt="logo"></img>
                                <button type="button" className="btn btn-primary btn-sm"
                                onClick={()=>setPedidos(filtraempresa("amazon"))}>Ver pedidos</button></li>
                            <li className="nav-item">
                                <img src="Logo_NIKE.svg.png"  className="logoempresa" alt="logo"></img>
                                <button type="button" className="btn btn-primary btn-sm"
                                onClick={()=>setPedidos(filtraempresa("nike"))}>Ver pedidos</button></li>
                            <li className="nav-item">
                                <img src="Aliexpress_logo.svg.png" className="logoempresa" alt="logo"></img>
                                <button type="button" className="btn btn-primary btn-sm"
                                onClick={()=>setPedidos(filtraempresa("aliexpress"))}>Ver pedidos</button></li>
                    </ul>
                </div>
                <div className="container-pedidoscomprador">
                    <div className="elementopedidoscomprador">
                        { pedidos? pedidos.map((pedido, index)=>
                            <div className="tarjetapedido">
                                <Pedido
                                    key= {index}
                                    id= {pedido.id}
                                    title= {pedido.title}
                                    description= {pedido.description}
                                    ruta="/comprador/"
                                />
                            </div>
                        ) : <h3>Haz click en la empresa que quieras consultar</h3>}
                    </div>
                    
                    <div className="registrarpedido">
                        {/* <h2 className="registraTitle">Registrar nuevo pedido</h2>
                        <p></p>
                        <textarea id="story" name="story" rows="20" cols="100" className="textarea"
                            placeholder="Introduzca un código de pedido para registrarlo">
                        </textarea> */}
                        <InputGroup className="mb-3">
                            <Form.Control
                            placeholder="Escriba el código de su pedido"
                            aria-label="Escriba el código de su pedido"
                            aria-describedby="basic-addon2"
                            />
                            <Button variant="outline-secondary" id="button-addon2">
                            Button
                            </Button>
                        </InputGroup>
                    </div>
                </div>
            </div>
        </div>
    )
}