import React from "react";
import {Link, useParams} from "react-router-dom";


export default function PaginaPedido (props){
    let{pedidoId} = useParams();
    return(
        <div>
            <h2 className="titulopedido">{props.pedidos.find(({id})=> {return id===Number(pedidoId)}).title}</h2>
            <div className="infopedido">
                <div className="elementoinfo"><img src="ubicacion.png" alt="ubi"></img></div>
            </div>
        </div>
    )
}