import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./../../style/Login.css"


export default function Login(props){
    
    const [nombreUsuario, setNombreUsuario] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const loginasync = async () => {
        const user = {
            nombre: nombreUsuario, 
            contrasena: password
        }
        console.log(user)
        const peticion = await fetch("http://localhost:8083/tracking/api/usuarios/login", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(user)
        })
        const peticionJSON = await peticion.json()
        console.log(peticionJSON)
        return peticionJSON
    }

    const loginsync = () => {
        const user = {
            nombre: nombreUsuario, 
            contrasena: password
        }
        console.log(user)
        fetch("http://localhost:8083/tracking/api/usuarios/login", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(user)
        })
        .then((response) => response.json())
        .then((data) => {return data})
    }
    
    const direccionaLogin = async () =>{
        const user = await loginasync()
        console.log(user)
        // const user = users.find(u=> u.nombre === nombreUsuario && u.contrasena === password);

        if(user) {
            if(user.es_gestor) {
                navigate(`/gestor/${user.id}/pedidos`);
            } else {
                navigate(`/comprador/${user.id}/pedidos`)
            }
        } else {
            alert("Las credenciales no son válidas")
        }

    }
        
    useEffect(() => {
        // console.log(compradores);
        // console.log(gestores);
    })
    return(
        <div className="container">
            <div className="pestana">
                <h3 className="loginTitle">Login</h3>
                <div className="login">
                    <div className="formulario">
                        <input 
                            type="text" 
                            className="input" 
                            placeholder="Username" 
                            aria-label="Username" 
                            onChange={(e)=> setNombreUsuario(e.target.value)}/>
                        
                        <input 
                            type="password" 
                            className="input" 
                            placeholder="Password" 
                            aria-label="Password" 
                            onChange={(e)=> setPassword(e.target.value)}/>

                        <button type="button" className="enter" onClick={direccionaLogin}>ENTRAR</button>
                    </div>
                </div>
                <Link to="/" className="volver">VOLVER</Link>
            </div>
        </div>
    )
}