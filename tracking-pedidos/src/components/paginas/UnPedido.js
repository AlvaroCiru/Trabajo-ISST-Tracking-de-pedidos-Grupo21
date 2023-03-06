import React, { useEffect, useState } from "react";
import "./../../style/UnPedido.css"
import { Link, useParams } from "react-router-dom";


export default function UnPedido(props){
    
    return(
        <div className="pedido">
            <h2>{pedido}</h2>
            <p>El pedido está en camino</p>
            <Link className="btn btn-outline-success me-2" to={props.ruta}>Volver</Link>
        </div>
    )
}