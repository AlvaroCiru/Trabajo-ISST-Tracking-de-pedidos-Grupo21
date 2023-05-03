import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./../../style/Login.css"


export default function Login(props){
    
    const [users, setUsers] = useState(props.usuarios)
    const [nombreUsuario, setNombreUsuario] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const gestores = users.reduce(
        (previousValue, currentValue)=>{
            if(previousValue.es_gestor){
                previousValue.push(currentValue);
            }
            return previousValue;
        },[]);
    
    const compradores = users.reduce(
        (previousValue, currentValue)=>{
                if(!previousValue.es_gestor){
                    previousValue.push(currentValue);
                }
                return previousValue;
        },[]);
    
    const direccionaLogin = () =>{
        
        const user = users.find(u=> u.nombre === nombreUsuario && u.contrasena === password);

        if(user) {
            navigate('/comprador');
            alert("Se ha iniciado sesión correctamente!");
        } else {
            alert("algo ha fallado...")
        }

    }
        
    useEffect(() => {
        console.log(compradores);
        console.log(gestores);
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