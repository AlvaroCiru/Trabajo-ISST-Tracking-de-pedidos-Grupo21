import React, { useEffect, useState } from "react";
import "./../../style/UnPedido.css"
import { Link, useParams } from "react-router-dom";
import mapa from "../../images/mapa.png"

export default function UnPedido(props){

    const {idUsuario, idPedido} = useParams();
    const [pedido, setPedido] = useState(null)
    
    let rutaActual = window.location.href
    console.log("Ruta actual: "+rutaActual)
    console.log(typeof rutaActual)
    console.log(idUsuario)

    let rutaVuelta = ""

    if (rutaActual.includes("/comprador")) {
        rutaVuelta = `/comprador/${idUsuario}/pedidos`
    } else if (rutaActual.includes("/gestor")) {
        rutaVuelta = `/gestor/${idUsuario}/pedidos`
    }

    let mensajeEstado = ""

    if(pedido){
        if(pedido.estado === "EN_PREPARACION")
            mensajeEstado = "El pedido está aún preparándose para salir."
        else if (pedido.estado === "EN_REPARTO")
        mensajeEstado = "El pedido está en reparto."
        else if (pedido.estado === "FINALIZADO")
        mensajeEstado = "El pedido ha finalizado."
        else mensajeEstado = "Lo sentimos, no tenemos información sobre su pedido. Póngase en contacto con la empresa correspondiente."
    } 

    useEffect(() => {
        const getPedido = async () => {
            const peticion = await fetch(`http://localhost:8083/tracking/api/pedidos/${idPedido}`)
            const peticionJSON = await peticion.json()
            setPedido(peticionJSON)
            console.log(peticionJSON)
        }
        getPedido()
    }, [idPedido])
    
    return(
        <>
            {pedido ? 
                <div className="pedido">
                    <div className="infopedido">
                        <h2>{pedido.titulo}</h2>
                        <div className="descripciones">
                            <p>{pedido.descripcion}</p>
                            <p>{mensajeEstado}</p>
                        </div>
                        <Link className="volver" to={rutaVuelta}>Volver</Link>
                    </div>
                    <img src={mapa} alt="Mapa"/>
                </div>
                : <span>Spinner</span>
            }
            
        </>
       
    )
}