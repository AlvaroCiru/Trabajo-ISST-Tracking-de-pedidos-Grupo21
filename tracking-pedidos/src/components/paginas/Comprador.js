import React, { useEffect, useState } from "react";
import "./../../style/Comprador.css"
import Pedido from "../Pedido";
import { Link } from "react-router-dom";


export default function Comprador(props){
    
    const ruta='/comprador/'
    const [pedidos, setPedidos] = useState(props.pedidos);

    const empresas = props.pedidos.reduce(
        (previousValue, currentValue)=>{
            if(!previousValue.includes(currentValue.empresa)){
                previousValue.push(currentValue.empresa);
            }
            return previousValue;
    },[]);

    console.log(empresas)

    const filtraempresa = (emp) => {

        const resultado = props.pedidos;

        return resultado.filter((el)=>
        el.empresa.toString().toLowerCase().includes(emp.toString().toLowerCase()));
    }

    console.log(filtraempresa("amazon"))


    return(
        <div className="comprador">
            <h2 className="titulo">Página del Comprador</h2>
            <div className="contenidocomprador">
                <div className="container-pedidos">
                    <h3 className="mispedidos">Mis pedidos</h3>
                    <div className="elementopedidos">
                        {pedidos.map((pedido, index)=>
                            <div className="tarjetapedido">
                                <Pedido
                                    key= {index}
                                    id= {pedido.id}
                                    title= {pedido.title}
                                    description= {pedido.description}
                                    ruta="/comprador/"
                                />
                            </div>
                        )}
                    </div>
                </div>
                <div className="registrarpedido">
                    <h2 className="registraTitle">Registrar pedido</h2>
                    <textarea id="story" name="story" rows="20" cols="100" className="textarea"
                        placeholder="Introduzca un código de pedido para registrarlo">
                    </textarea>
                </div>
            </div>
        </div>
    )
}