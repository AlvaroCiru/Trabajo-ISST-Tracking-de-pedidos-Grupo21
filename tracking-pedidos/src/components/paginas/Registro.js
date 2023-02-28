import React from "react";
import {Link} from "react-router-dom"
import "./../../style/Registro.css"

export default function Registro(props){
    return(
        <div className="container">
            <p></p>
            <h2>Regístrate para empezar a usar nuestra aplicación!</h2>
            <p></p>
            <form className="row g-3 needs-validation" novalidate>
                <div className="col-md-4">
                    <label for="validationCustom01" className="form-label">Nombre</label>
                    <input type="text" className="form-control" id="validationCustom01" required/>
                    <div className="valid-feedback">
                    Looks good!
                    </div>
                </div>
                <div className="col-md-4">
                    <label for="validationCustom02" className="form-label">Primer apellido</label>
                    <input type="text" className="form-control" id="validationCustom02"  required/>
                    <div className="valid-feedback">
                    Looks good!
                    </div>
                </div>
                <div className="col-md-4">
                    <label for="validationCustomUsername" className="form-label">Nombre de usuario</label>
                    <div className="input-group has-validation">
                    <span className="input-group-text" id="inputGroupPrepend">@</span>
                    <input type="text" className="form-control" id="validationCustomUsername" aria-describedby="inputGroupPrepend" required/>
                    <div className="invalid-feedback">
                        Please choose a username.
                    </div>
                    </div>
                </div>
                <div className="col-md-6">
                    <label for="validationCustom03" className="form-label">Ciudad</label>
                    <input type="text" className="form-control" id="validationCustom03" required/>
                    <div className="invalid-feedback">
                    Please provide a valid city.
                    </div>
                </div>
                <div className="col-md-3">
                    <label for="validationCustom04" className="form-label">Provincia</label>
                    <select className="form-select" id="validationCustom04" required>
                    <option selected disabled value="">Choose...</option>
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
                    <div className="invalid-feedback">
                    Please select a valid state.
                    </div>
                </div>
                <div className="col-md-3">
                    <label for="validationCustom05" className="form-label">Código Postal</label>
                    <input type="text" className="form-control" id="validationCustom05" required/>
                    <div className="invalid-feedback">
                    Please provide a valid zip.
                    </div>
                </div>
                <div className="col-md-6">
                    <label for="validationCustom03" className="form-label">Dirección</label>
                    <input type="text" className="form-control" id="validationCustom03" required/>
                    <div className="invalid-feedback">
                    Please provide a valid city.
                    </div>
                </div>
                <div className="col-md-4">
                    <label for="validationCustom01" className="form-label">Contraseña</label>
                    <input type="password" className="form-control" id="validationCustom01" required/>
                    <div className="valid-feedback">
                    Looks good!
                    </div>
                </div>
                <div className="col-md-4">
                    <label for="validationCustom01" className="form-label">Confirmar contraseña</label>
                    <input type="password" className="form-control" id="validationCustom01" required/>
                    <div className="valid-feedback">
                    Looks good!
                    </div>
                </div>
                <div className="col-12">
                    <div className="form-check">
                    <input className="form-check-input" type="checkbox" value="" id="invalidCheck" required/>
                    <label className="form-check-label" for="invalidCheck">
                        Acepto los términos y condiciones
                    </label>
                    <div className="invalid-feedback">
                        You must agree before submitting.
                    </div>
                    </div>
                </div>
                <div className="col-12">
                    <button className="btn btn-primary" type="submit">ENVIAR</button>
                </div>
            </form>
            <p></p>
            <Link to="/"><button id="volver"type="button" className="btn btn-outline-secondary">VOLVER</button></Link>
        </div>
    )
}