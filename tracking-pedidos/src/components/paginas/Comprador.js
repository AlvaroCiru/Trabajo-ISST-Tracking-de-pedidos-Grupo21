import React, { useEffect, useState } from "react";
import "./../../style/Inicio.css"
import LogoEbay from "./../../images/450px-EBay_logo.svg.png"
import LogoAmazon from "./../../images/Amazon_logo.svg.png"
import LogoNike from "./../../images/Logo_NIKE.svg.png"
import LogoAliExpress from "./../../images/Aliexpress_logo.svg.png"
import Pedido from "../Pedido";


export default function Comprador(props){
    
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
            <h1 className="titulo">Página del Comprador</h1>
            <p></p>
            <div className="container-pedidos">
                <div className="elementopedidos">
                    <ul className="nav flex-column">
                        <li className="nav-item">
                            <h3>Mis pedidos</h3>
                        </li>
                        <p></p>
                        <li className="nav-item">
                            <img src={LogoEbay}  className="logoempresa" alt="logo"></img>
                            <button type="button" className="btn btn-primary btn-sm"
                             onClick={()=>setPedidos(filtraempresa("ebay"))}>Active</button>
                        </li>
                        <li className="nav-item">
                            <img src={LogoAmazon}  className="logoempresa" alt="logo"></img>
                            <button type="button" className="btn btn-primary btn-sm"
                            onClick={()=>setPedidos(filtraempresa("amazon"))}>Active</button></li>
                        <li className="nav-item">
                            <img src={LogoNike}  className="logoempresa" alt="logo"></img>
                            <button type="button" className="btn btn-primary btn-sm"
                             onClick={()=>setPedidos(filtraempresa("nike"))}>Active</button></li>
                        <li className="nav-item">
                            <img src={LogoAliExpress} className="logoempresa" alt="logo"></img>
                            <button type="button" className="btn btn-primary btn-sm"
                             onClick={()=>setPedidos(filtraempresa("aliexpress"))}>Active</button></li>
                        <li className="nav-item">
                            <button type="button" className="btn btn-primary btn-sm">Active</button>
                        </li>
                    </ul>
                </div>
                <div className="elementopedidos">
                    {pedidos.map((pedido, index)=>
                        <Pedido
                            key= {index}
                            id= {pedido.id}
                            title= {pedido.title}
                            description= {pedido.description}
                        />
                    )}


                </div>
            </div>      
          
        </div>
    )
}