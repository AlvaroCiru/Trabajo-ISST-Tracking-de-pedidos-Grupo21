import React, { useEffect, useState } from "react";
import "./../../style/Inicio.css"
import Texto from "../Texto";
import { Link } from "react-router-dom";
import Logo from "./../navegacion/svg-1.svg"
import LogoEbay from "./../../images/450px-EBay_logo.svg.png"
import LogoAmazon from "./../../images/Amazon_logo.svg.png"
import LogoAliExpress from "./../../images/Aliexpress_logo.svg.png"
import Pedido from "../Pedido";


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