import React from "react";
import { Link } from "react-router-dom";
import Logo from "./svg-1.svg"
import "./../../style/Navbar.css"

export default function Navbar(props){
    return(
        <div>
            <nav className="navbar bg-body-tertiary">
                <div className="container-fluid">
                    <img src={Logo} alt="Logo" width="50" height="50" className="d-inline-block align-text-top"></img>
                    <h1>Tracking de pedidos</h1>
                    <form >
                        <Link className="btn btn-outline-success me-2" to='/login'>Inicia Sesión</Link>
                        <Link className="btn btn-sm btn-outline-secondary" to='/registro'>Regístrate</Link>
                    </form>
                </div>

            </nav>        
        </div>
    )
}