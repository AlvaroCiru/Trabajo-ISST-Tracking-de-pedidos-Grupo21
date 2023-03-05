import React, { useEffect, useState } from "react";
import "./../../style/Inicio.css"
import LogoEbay from "./../../images/450px-EBay_logo.svg.png"
import LogoAmazon from "./../../images/Amazon_logo.svg.png"
import LogoNike from "./../../images/Logo_NIKE.svg.png"
import LogoAliExpress from "./../../images/Aliexpress_logo.svg.png"
import Pedido from "../Pedido";
import { Link } from "react-router-dom";


export default function Gestor(props){
    
    const ruta='/gestor/'
    const [pedidos, setPedidos] = useState(props.pedidos);

    const empresas = props.pedidos.reduce(
        (previousValue, currentValue)=>{
            if(!previousValue.includes(currentValue.empresa)){
                previousValue.push(currentValue.empresa);
            }
            return previousValue;
    },[]);

    console.log(empresas)

    const filtraempresa = (emp) => {

        const resultado = props.pedidos;

        return resultado.filter((el)=>
        el.empresa.toString().toLowerCase().includes(emp.toString().toLowerCase()));
    }

    console.log(filtraempresa("amazon"))


    return(
        <div>
            <h1 className="titulo">Página del Gestor</h1>
            <p></p>
            <div className="container-pedidos">
                <div className="elementopedidos">
                    {pedidos.map((pedido, index)=>
                        <Link className="btn btn-outline-success me-2" to={ruta+String(index)}>
                            <Pedido
                                key= {index}
                                id= {pedido.id}
                                title= {pedido.title}
                                description= {pedido.description}
                            />
                        </Link>
                        
                    )}
                </div>
            </div>      
          
        </div>
    )
}