import React from "react";
import { Link } from "react-router-dom";
import "./../../style/Login.css"

export default function Login(props){
    return(
        <div>
            <p></p>
            <div className="container">
                <p>Introduzca su nombre de usuario</p>
                <input type="text" className="form-control" placeholder="Username" aria-label="Username"/>
                <p></p>
                <p>Introduzca su contraseña</p>
                <input type="password" className="form-control" placeholder="Password" aria-label="Password"/>
                <p></p>
                <button type="button" className="btn btn-outline-success">ENTRAR</button>
            </div>
            <p></p>
            <Link to="/"><button id="volver"type="button" className="btn btn-outline-secondary">VOLVER</button></Link>
        </div>
    )
}