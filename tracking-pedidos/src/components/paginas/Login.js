import React from "react";
import { Link } from "react-router-dom";
import "./../../style/Login.css"

export default function Login(props){
    return(
        <div className="container">
            <div className="form">
                <input type="text" className="form-control" placeholder="Username" aria-label="Username"/>
                <input type="password" className="form-control" placeholder="Password" aria-label="Password"/>
            </div>
            <div className="botonesEntrar">
                <Link to="/comprador"><button id="entraComprador" type="button" className="btn btn-outline-secondary">Entrar como Comprador</button></Link>
                <Link to="/gestor"><button id="entraGestor"type="button" className="btn btn-outline-secondary">Entrar como Gestor</button></Link>
            </div>
            <Link to="/"><button id="volver"type="button" className="btn btn-outline-secondary">VOLVER</button></Link>
        </div>
    )
}