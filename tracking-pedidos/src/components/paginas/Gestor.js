import React, { useEffect, useState } from "react";
import "./../../style/Gestor.css"
import Pedido from "../Pedido";


export default function Gestor(props){
    
    const [pedidos, setPedidos] = useState(props.pedidos);

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

    useEffect(()=> {
        setPedidos(filtraempresa("ebay"))
    })

    return(
        <div>
            <h2 className="titulo">Página del Gestor</h2>
            <div className="contenidogestor">
                <div className="container-pedidosgestor">

                    <div className="elementopedidosgestor">
                        <h3 className="mispedidos">Pedidos Operativos de la empresa : {empresas[0]}</h3>
                        {pedidos.map((pedido, index)=>
                            <div className="tarjetapedido">
                                <Pedido
                                    key= {index}
                                    id= {pedido.id}
                                    title= {pedido.title}
                                    description= {pedido.description}
                                    ruta="/gestor/"
                                />
                            </div>
                        )}
                    </div>
                    <div className="elementopedidosgestor">
                        <div className="historico">Ver Histórico de Pedidos</div>
                    </div>
                </div>
            </div>
        </div>
    )
}