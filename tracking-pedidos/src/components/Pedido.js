import React from "react";
import "./../style/Pedido.css"
import {Link} from "react-router-dom"
import "../images/fundaiphone13.jpg"


export default function Pedido (props){


    return(
        <div className="unpedido">
            <div className="tituloPedido"><h3>{props.title}</h3></div>
            <div className="descripcionPedido"><p>{props.description}</p></div>
            <Link to ={ props.ruta + props.id.toString()} className="info">INFO</Link>
        </div>
    )
}