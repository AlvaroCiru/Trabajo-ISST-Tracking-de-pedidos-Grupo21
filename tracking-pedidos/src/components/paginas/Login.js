import React from "react";
import { Link } from "react-router-dom";
import "./../../style/Login.css"

export default function Login(props){
    return(
        <div className="container">
            <div className="pestana">
                <h3 className="loginTitle">Login</h3>
                <div className="login">
                    <div className="formulario">
                        <input type="text" className="input" placeholder="Username" aria-label="Username"/>
                        <input type="password" className="input" placeholder="Password" aria-label="Password"/>
                        <button type="button" className="enter">ENTRAR</button>
                    </div>
                    <div className="botonesUsuario">
                        <Link to="/comprador" className="botoncomprador">COMPRADOR</Link>
                        <Link to="/gestor" className="botongestor">GESTOR</Link>
                    </div>
                </div>
                <Link to="/" className="volver">VOLVER</Link>
            </div>
        </div>
    )
}