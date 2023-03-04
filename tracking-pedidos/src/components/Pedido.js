import React from "react";
import "./../style/Pedido.css"
import {Link} from "react-router-dom"


export default function Pedido (props){


    return(
        <div className="unpedido">
            <div className="elementopedido"><img src={props.img} alt="fotopedido" width="150px" height="auto"></img></div>
            <div className="elementopedido"><h2>{props.title}</h2></div>
            <div className="elementopedido"><Link to ={ "/pedido/" + props.id.toString()}><button id="volver"type="button" className="btn btn-outline-secondary">INFO</button></Link></div>
        </div>
    )
}