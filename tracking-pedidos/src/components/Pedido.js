import React from "react";
import "./../style/Pedido.css"

export default function Pedido (props){
    return(
        <div className="unpedido">
            <div className="elementopedido"><h2>{props.title}</h2></div>
            <div className="elementopedido"><p>{props.description}</p></div>
        </div>
    )
}