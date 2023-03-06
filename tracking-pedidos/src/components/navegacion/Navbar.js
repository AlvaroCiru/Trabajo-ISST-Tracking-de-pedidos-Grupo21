import React from "react";
import { Link } from "react-router-dom";
import Logo from "./logoTarget.png"
import "./../../style/Navbar.css"

export default function Navbar(props){
    return(
        <div>
            <nav className="navegador">
                <div className="header">
                    <Link to='/'><img src={Logo} alt="Logo" width="65" height="65" className="logo"></img></Link>
                    <h1 className="mainTitle">Tracking de pedidos</h1>
                    <div className="botones">
                        <Link className="iniciaSesion" to='/login'>Iniciar Sesión</Link>
                        <Link className="registrarse" to='/registro'>Registrarse</Link>
                    </div>
                </div>
            </nav>        
        </div>
    )
}