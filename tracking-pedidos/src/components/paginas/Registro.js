import React from "react";
import {Link} from "react-router-dom"
import { useState, useEffect } from 'react';

import "./../../style/Registro.css"

export default function Registro(props){

    const [nombre, setNombre] = useState("");
    const [contrasena, setContrasena] = useState("");
    const [telefono, setTelefono] = useState("");
    const [email, setEmail] = useState("");
    const [registroCorrecto, setRegistroCorrecto] = useState(false);
    // const bcrypt = require("bcrypt");
    // const BCRYPT_SALT_ROUNDS = 12;

    const handleSubmit = (e) => {
        e.preventDefault();
        // const cryptedKey = bcrypt.hash(contrasena, BCRYPT_SALT_ROUNDS);
    
        // Enviar datos al backend
        fetch("http://localhost:8083/tracking/api/usuarios/compradores", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            nombre,
            email,
            contrasena,
            telefono,
          }),
        })
          .then((response) => response.json())
          .then((data) => {
            setRegistroCorrecto(true);
            alert("EL registro se ha realizado correctamente!")
          })
          .catch((error) => {
            console.error("Error:", error);
          });
      };
    
    
    return(
        <div className="container">
            <div className="registro">
            <h3 className="registroTitle">Registrarse</h3>
            <form className="formRegistro" onSubmit={handleSubmit}>
                <div className="datosPersonales">
                    <input 
                        type="text" 
                        className="dato" 
                        id="nombre" 
                        required placeholder="Nombre" 
                        value={nombre}
                        onChange={(e)=> setNombre(e.target.value)}
                    />
                    <input 
                        type="number" 
                        className="dato" 
                        id="telefono" 
                        required placeholder="Teléfono"
                        value={telefono}
                        onChange={(e)=> setTelefono(e.target.value)}/>
                    <input 
                        type="email" 
                        className="dato" 
                        id="email" 
                        required placeholder="E-mail" 
                        value={email}
                        onChange={(e)=> setEmail(e.target.value)}
                    />
                    <input type="password" className="dato" id="contraseña" required placeholder="Contraseña"/>
                    <input 
                        type="password" 
                        className="dato" 
                        id="confirmaContraseña" 
                        required placeholder="Confirmar contraseña"
                        value={contrasena}
                        onChange={(e)=> setContrasena(e.target.value)}/>

                </div>
                <div id="terminosCondiciones">
                    <input className="termsCheck" type="checkbox" value="" id="termsCheck" required/>
                    <label className="termsTitle" id="termsTitle" for="invalidCheck">Acepto los términos y condiciones</label>
                </div>   
                <button type="submit" className="enter" id="enviar">ENVIAR</button>            
            </form>
            <Link to="/"><button id="volverRegistro" type="button" className="volver">VOLVER</button></Link>
            </div>
        </div>
    )
}