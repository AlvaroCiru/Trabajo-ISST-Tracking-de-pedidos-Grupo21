import React, { useEffect, useState } from "react";
import "./../../style/Inicio.css"
import Texto from "../Texto";


export default function Inicio(props){
    return(
        <div>
            <h2 className="title">Inicia Sesión para ver tus pedidos</h2>
            <div className="textos">
                <div className="texto"><Texto title="¿Quiénes somos?" /></div>
                <div className="texto"><Texto title="Instrucciones" /></div>
                <div className="texto"><Texto title="Preguntas frecuentes" /></div>
            </div>
            
        </div>
    )
}