import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "./../../style/Navbar.css"

export default function Navbar(props){

    return(
        <div>
            <nav className="navegador">
                <div className="header">
                    <Link to='/'><img src={process.env.PUBLIC_URL + "Logo-EasyTrack.jpg"} alt="Logo" width="200" height="50" className="logo"></img></Link>
                    <h1 className="mainTitle">EasyTrack</h1>
                    {props.sesion ? <Link className="logout" to='/' onClick={()=>props.logout(null)}>Logout</Link> 
                        :
                    <div className="botones">
                        <Link className="iniciaSesion" to='/login'>Iniciar Sesión</Link>
                        <Link className="registrarse" to='/registro'>Registrarse</Link>
                    </div>
                    }
                    
                </div>
            </nav>        
        </div>
    )
}