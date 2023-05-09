import React, { useEffect, useState } from "react";
import "./../../style/Comprador.css"
import Pedido from "../Pedido";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { useParams } from "react-router-dom";

export default function Comprador(props){
    
    const [pedidos, setPedidos] = useState(null);
    const [empresas, setEmpresas] = useState(null);
    const [codigoPedido, setCodigoPedido] = useState("");
    const {idUsuario} = useParams()

    // const empresas = props.pedidos.reduce(
    //     (previousValue, currentValue)=>{
    //         if(!previousValue.includes(currentValue.empresa)){
    //             previousValue.push(currentValue.empresa);
    //         }
    //         return previousValue;
    // },[]);

    const addPedido = async () => {
        console.log(codigoPedido)
        const peticion = await fetch(`http://localhost:8083/tracking/api/pedidos/agregarPedido/${idUsuario}`, {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: codigoPedido
        })
        const peticionJSON = await peticion.json()
        console.log(peticionJSON)
        await getPedidos()
    }

    const getPedidos = async () => {
        const peticion = await fetch(`http://localhost:8083/tracking/api/pedidos/compradores/${idUsuario}`)
        const peticionJSON = await peticion.json()
        setPedidos(peticionJSON)
        console.log(pedidos)
    }

    const getEmpresas = async () => {
        const peticion = await fetch(`http://localhost:8083/tracking/api/empresas`)
        const peticionJSON = await peticion.json()
        setEmpresas(peticionJSON)
        console.log(empresas)
    }

    const filtraempresa = (emp) => {

        // const resultado = props.pedidos;

        // return resultado.filter((el)=>
        // el.empresa.toString().toLowerCase().includes(emp.toString().toLowerCase()));
    }

    useEffect(() => {
        getPedidos()
        getEmpresas()
    }, [])

    return(
        <div className="comprador">
            <h2 className="titulo">Página del Comprador</h2>
            <h3 className="titulo">Mis pedidos</h3>
            <div className="contenidocomprador">
                <div className="listaempresas">
                    <ul className="nav flex-column">
                        { empresas ? 
                            empresas.map((empresa, index) => 
                                <li className="nav.item" key={empresa.id}>
                                    <span>{empresa.nombre}</span>
                                    <button type="button" className="btn btn-primary btn-sm"
                                    onClick={() => filtraempresa(empresa.nombre)}>Filtrar</button>
                                </li>
                            ) : 
                            <span>No hay empresas registradas</span>
                        }
                    </ul>
                </div>
                <div className="container-pedidoscomprador">
                    <ul className="elementopedidoscomprador">
                        { pedidos ? pedidos.map((pedido, index)=>
                            <li className="tarjetapedido" key= {pedido.codigo}>
                                <Pedido
                                    id= {pedido.codigo}
                                    title= {pedido.titulo}
                                    description= {pedido.descripcion}
                                    pedido={pedido}
                                    ruta= {`/comprador/${idUsuario}/pedidos/${pedido.codigo}`}
                                />
                            </li>
                        ) : 
                        <div>
                            <h3>Aún no se han añadido pedidos</h3>
                            <p>Añada un pedido para ver su estado</p>
                        </div> }
                    </ul>
                    
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
                            onChange={(e) => setCodigoPedido(e.target.value.toString())}
                            />
                            <Button variant="outline-secondary" id="button-addon2" onClick={()=>addPedido()}>
                            Button
                            </Button>
                        </InputGroup>
                    </div>
                </div>
            </div>
        </div>
    )
}