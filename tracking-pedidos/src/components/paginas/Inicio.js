import React from "react";
import "./../../style/Inicio.css"
import Logo from "./../navegacion/svg-1.svg"
import Pedido from "../Pedido";

export default function Inicio(props){
    return(
        <div>
            <h1 className="titulo">Página principal</h1>
            <p></p>
            <div className="container-pedidos">
                <div className="elementopedidos">
                    <ul className="nav flex-column">
                        <li className="nav-item">
                            <h3>Mis pedidos</h3>
                        </li>
                        <p></p>
                        <li className="nav-item">
                            <img src={Logo}  width="100" height="80" alt="logo"></img>
                            <button type="button" className="btn btn-primary btn-sm">Active</button>
                        </li>
                        <li className="nav-item">
                            <img src={Logo}  width="100" height="80" alt="logo"></img>
                            <button type="button" className="btn btn-primary btn-sm">Active</button>                </li>
                        <li className="nav-item">
                            <img src={Logo}  width="100" height="80" alt="logo"></img>
                            <button type="button" className="btn btn-primary btn-sm">Active</button>                </li>
                        <li className="nav-item">
                            <img src={Logo}  width="100" height="80" alt="logo"></img>
                            <button type="button" className="btn btn-primary btn-sm">Active</button>                </li>
                    </ul>
                </div>
                <div className="elementopedidos">
                    <Pedido/>


                </div>
            </div>      
          
        </div>
    )
}