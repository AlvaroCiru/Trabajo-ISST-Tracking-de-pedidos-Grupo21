import React, { useEffect, useState } from "react";
import "./../../style/Inicio.css"
import Texto from "../Texto";
import { useNavigate } from "react-router-dom";

export default function Inicio(props){

    const navigate = useNavigate()
    const user = props.sesion ? JSON.parse(props.sesion) : null

    const handleClick = () => {
        if(user) {
            if(user.es_gestor) {
                navigate(`/gestor/${user.id}/pedidos`);
            } else {
                navigate(`/comprador/${user.id}/pedidos`)
            }
        } else {
            navigate("/login")
        }

    }

    return(
        <div className="inicio">
            <h2 className="title">Inicia Sesión para ver tus pedidos</h2>
            <button className="misPedidosButton" onClick={handleClick}>Ver Mis Pedidos</button>
            <div className="textos">
                <div className="texto"><Texto title="¿Quiénes somos?" /></div>
                <div className="texto"><Texto title="Instrucciones" /></div>
                <div className="texto"><Texto title="Preguntas frecuentes" /></div>
            </div>
            
        </div>
    )
}