import React, { useEffect, useState } from "react";
import "./../../style/Inicio.css"
import { Link } from "react-router-dom";
import Logo from "./../navegacion/svg-1.svg"
import LogoEbay from "./../../images/450px-EBay_logo.svg.png"
import LogoAmazon from "./../../images/Amazon_logo.svg.png"
import LogoNike from "./../../images/Logo_NIKE.svg.png"
import LogoAliExpress from "./../../images/Aliexpress_logo.svg.png"
import Pedido from "../Pedido";


export default function Inicio(props){
    
    return(
        <div>
            <h2>BIENVENIDO A TARGET</h2>
            <h4>¿Qué rol vas a tomar?</h4>
            <Link className="btn btn-outline-success me-2" to='/comprador'>Comprador</Link>
            <Link className="btn btn-outline-success me-2" to='/gestor'>Gestor</Link>
        </div>
    )
}