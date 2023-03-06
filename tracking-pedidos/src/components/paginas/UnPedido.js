import React, { useEffect, useState } from "react";
import "./../../style/UnPedido.css"
import { Link, useParams } from "react-router-dom";
import mapa from "../../images/mapa.png"

export default function UnPedido(props){

    const {idPedido} = useParams();
    const [pedidos, setPedidos] = useState(props.pedidos)
    console.log("El idPedido es "+idPedido)
    console.log(pedidos)
    let pedido = props.pedidos[idPedido]

    let rutaActual = window.location.href
    console.log("Ruta actual: "+rutaActual)
    console.log(typeof rutaActual)

    let rutaVuelta = ""

    if (rutaActual.includes("/comprador/")) {
        rutaVuelta = "/comprador/"
    } else if (rutaActual.includes("/gestor/")) {
        rutaVuelta = "/gestor/"
    }
    
    return(
        <div className="pedido">
            <div className="infopedido">
                <h2>{pedido.title}</h2>
                <div className="descripciones">
                    <p>{pedido.description}</p>
                    <p>El pedido está en camino</p>
                </div>
                <Link className="volver" to={rutaVuelta}>Volver</Link>
            </div>
            <img src={mapa} alt="Mapa"/>
        </div>
    )
}