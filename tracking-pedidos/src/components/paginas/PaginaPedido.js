import React from "react";
import {Link, useParams} from "react-router-dom";


export default function PaginaPedido (props){
    let{idComprador, codigoPedido} = useParams();


    return(
        <div>
            <h2 className="titulopedido">{props.pedidos.find(({id})=> {return id===Number(codigoPedido)}).title}</h2>
            <div className="infopedido">
                <div className="elementoinfo"><img src="ubicacion.png" alt="ubi"></img></div>
            </div>
        </div>
    )
}