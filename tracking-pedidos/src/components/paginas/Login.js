import React from "react";
import { Link } from "react-router-dom";
import "./../../style/Login.css"

export default function Login(props){
    return(
        <div className="container">
            <div className="form">
                <input type="text" className="form-control" placeholder="Username" aria-label="Username"/>
                <input type="password" className="form-control" placeholder="Password" aria-label="Password"/>
                <p></p>
                <button type="button" className="btn btn-outline-success">ENTRAR</button>
            </div>
            <Link to="/"><button id="volver"type="button" className="btn btn-outline-secondary">VOLVER</button></Link>
        </div>
    )
}