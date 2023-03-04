import React, { useEffect, useState } from "react";
import "./../../style/Inicio.css"
import Logo from "./../navegacion/svg-1.svg"
import LogoEbay from "./../../images/450px-EBay_logo.svg.png"
import LogoAmazon from "./../../images/Amazon_logo.svg.png"
import LogoAliExpress from "./../../images/Aliexpress_logo.svg.png"
import Pedido from "../Pedido";


export default function Inicio(props){
    
    const [pedidos, setPedidos] = useState(null);

    const empresas = props.pedidos.reduce(
        (previousValue, currentValue)=>{
            if(!previousValue.includes(currentValue.empresa)){
                previousValue.push(currentValue.empresa);
            }
            return previousValue;
    },[]);


    const filtraempresa = (emp) => {

        const resultado = props.pedidos;

        return resultado.filter((el)=>
        el.empresa.toString().toLowerCase().includes(emp.toString().toLowerCase()));
    }

console.log(LogoEbay)
    return(
        <div>
            <h1 className="titulo">Bienvenido Alvaro!</h1>
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
                             onClick={()=>setPedidos(filtraempresa("ebay"))}>Ver</button>
                        </li>
                        <li className="nav-item">
                            <img src={LogoAmazon}  className="logoempresa" alt="logo"></img>
                            <button type="button" className="btn btn-primary btn-sm"
                            onClick={()=>setPedidos(filtraempresa("amazon"))}>Ver</button></li>
                        <li className="nav-item">
                            <img src="Logo_NIKE.svg.png"  className="logoempresa" alt="logo"></img>
                            <button type="button" className="btn btn-primary btn-sm"
                             onClick={()=>setPedidos(filtraempresa("nike"))}>Ver</button></li>
                        <li className="nav-item">
                            <img src={LogoAliExpress} className="logoempresa" alt="logo"></img>
                            <button type="button" className="btn btn-primary btn-sm"
                             onClick={()=>setPedidos(filtraempresa("aliexpress"))}>Ver</button></li>
                        <li className="nav-item">
                            <button type="button" className="btn btn-primary btn-sm">Más empresas</button>
                        </li>
                    </ul>
                </div>
                <div className="elementopedidos">
                    {pedidos ? pedidos.map((pedido, index)=>
                        <Pedido
                            key= {index}
                            id= {pedido.id}
                            title= {pedido.title}
                            description= {pedido.description}
                            img= {pedido.img}
                        />
                    ) : <h3>Haz click en la compañía que deseas consultar tus pedidos en curso</h3>}


                </div>
            </div>      
          
        </div>
    )
}