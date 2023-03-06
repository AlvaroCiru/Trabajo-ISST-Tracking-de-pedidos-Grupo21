import React from "react";
import {Link} from "react-router-dom"
import "./../../style/Registro.css"

export default function Registro(props){
    return(
        <div className="container">
            <div className="registro">
            <h3 className="registroTitle">Registrarse</h3>
            <form className="formRegistro" novalidate>
                <div className="datosPersonales">
                    <input type="text" className="dato" id="nombre" required placeholder="Nombre"/>
                    <input type="text" className="dato" id="apellido"  required placeholder="Primer apellido"/>
                    <input type="text" className="dato" id="username" required placeholder="Nombre de usuario"/>
                    <input type="password" className="dato" id="contraseña" required placeholder="Contraseña"/>
                    <input type="password" className="dato" id="confirmaContraseña" required placeholder="Confirmar contraseña"/>
                </div>
                <div className="localizacion">
                <select className="dato" id="provincia" required>
                    <option selected disabled value="">Provincia</option>
                    <option>Madrid</option>
                    <option>Sevilla</option>
                    <option>Barcelona</option>
                    <option>Asturias</option>
                    <option>Valencia</option>
                    <option>Burgos</option>
                    <option>Cádiz</option>
                    <option>Cuenca</option>
                    <option>Toledo</option>
                    <option>León</option>
                </select>
                <input type="text" className="dato" id="ciudad" required placeholder="Ciudad"/>
                <input type="text" className="dato" id="codigoPostal" required placeholder="Código Postal"/>
                <input type="text" className="dato" id="direccion" required placeholder="Dirección"/>
                </div>
            </form>
            <div id="terminosCondiciones">
                <input className="termsCheck" type="checkbox" value="" id="termsCheck" required/>
                <label className="termsTitle" id="termsTitle" for="invalidCheck">Acepto los términos y condiciones</label>
            </div>
            <button type="button" className="enter" id="enviar">ENVIAR</button>
            <Link to="/"><button id="volverRegistro" type="button" className="volver">VOLVER</button></Link>
            </div>
        </div>
    )
}