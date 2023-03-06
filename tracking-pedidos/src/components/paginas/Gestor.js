import React, { useEffect, useState } from "react";
import "./../../style/Comprador.css"
import Pedido from "../Pedido";
import { Link } from "react-router-dom";


export default function Gestor(props){
    
    const ruta='/gestor/'
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
        <div>
            <h2 className="titulo">Página del Gestor</h2>
            <div className="contenidocomprador">
                <div className="container-pedidos">
                    <h3 className="mispedidos">Pedidos Operativos</h3>
                    <div className="elementopedidos">
                        {pedidos.map((pedido, index)=>
                            <div className="tarjetapedido">
                                <Pedido
                                    key= {index}
                                    id= {pedido.id}
                                    title= {pedido.title}
                                    description= {pedido.description}
                                    ruta={ruta}
                                />
                            </div>
                        )}
                    </div>
                </div>
                <div className="historico">Ver Histórico de Pedidos</div>
            </div>
        </div>
    )
}