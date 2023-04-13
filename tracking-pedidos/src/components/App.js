import './../style/App.css';
import { useState, useEffect } from 'react';
import Navbar from './navegacion/Navbar';
import {Route, Routes} from "react-router-dom"
import Inicio from './paginas/Inicio';
import NoMatch from './NoMatch.js';
import Login from './paginas/Login.js';
import Registro from './paginas/Registro.js';
import UnPedido from './paginas/UnPedido';
import {mockdata} from './../data/pedidos'
import Comprador from './paginas/Comprador';
import Gestor from './paginas/Gestor';
import CONFIG from '../config/config'

function App() {

  const [data, setData] = useState(mockdata);
  const[loading, setLoading] = useState(true);

const download = async () => {
  let downloadedProducts;
  try {
    const res = await fetch(CONFIG.SERVER_URL);
    downloadedProducts = await res.json();
  } catch (e) {
    alert("No se ha podido recuperar la información."); 
  }
  setData(downloadedProducts);
}

useEffect(() => {
  async function fetchData() {
    await download();
      
    setTimeout(()=>{
      setLoading(false);
    },500);		
  }

  if(CONFIG.USE_SERVER){
    fetchData()
  } else{
    setData(mockdata);
    setTimeout(()=>{
      setLoading(false);
    },500);
  };
}, [loading]);
  
  return (
    <div>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossOrigin="anonymous"/>
      <Navbar/>
      <Routes>   
          <Route path="/" element={<Inicio/>}/>
          <Route path="/login" element={<Login/>}/>
          <Route path="/registro" element={<Registro/>}/>
          <Route path="/comprador" element={<Comprador pedidos={data.pedidos}/>}/>
          <Route path="/gestor" element={<Gestor pedidos={data.pedidos}/>}/>
          <Route path="/comprador/:idPedido" element={<UnPedido pedidos={data.pedidos}/>}/>
          <Route path="/gestor/:idPedido" element={<UnPedido pedidos={data.pedidos}/>}/>
          <Route path="*" element={<NoMatch />} />
      </Routes>
    </div>
  );
}

export default App;
