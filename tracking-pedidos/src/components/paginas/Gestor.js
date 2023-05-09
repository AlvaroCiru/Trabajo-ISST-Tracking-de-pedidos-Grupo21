import React, { useEffect, useState } from "react";
import "./../../style/Gestor.css"
import Pedido from "../Pedido";
import { useParams } from "react-router-dom";


export default function Gestor(props){
    
    const [pedidos, setPedidos] = useState(null);
    const {idUsuario} = useParams()

    // const empresas = props.pedidos.reduce(
    //     (previousValue, currentValue)=>{
    //         if(!previousValue.includes(currentValue.empresa)){
    //             previousValue.push(currentValue.empresa);
    //         }
    //         return previousValue;
    // },[]);

    const getPedidos = async () => {
        const peticion = await fetch(`http://localhost:8083/tracking/api/pedidos/gestores/${idUsuario}`)
        const peticionJSON = await peticion.json()
        setPedidos(peticionJSON)
        console.log(pedidos)
    }

    useEffect(()=> {
        getPedidos()
    }, [])

    return(
        <div>
            <h2 className="titulo">Página del Gestor</h2>
            <div className="contenidogestor">
                <div className="container-pedidosgestor">

                    <div className="elementopedidosgestor">
                        <h3 className="mispedidos">Pedidos Operativos de la empresa : </h3>
                        {pedidos ? pedidos.map((pedido, index)=>
                            <div className="tarjetapedido" key={pedido.codigo}>
                                <Pedido
                                    id= {pedido.codigo}
                                    title= {pedido.titulo}
                                    description= {pedido.descripcion}
                                    ruta= {`/gestor/${idUsuario}/pedidos/${pedido.codigo}`}
                                />
                            </div>
                        ) : <span>Spinner</span>}
                    </div>
                    <div className="elementopedidosgestor">
                        <div className="historico">Ver Histórico de Pedidos</div>
                    </div>
                </div>
            </div>
        </div>
    )
}