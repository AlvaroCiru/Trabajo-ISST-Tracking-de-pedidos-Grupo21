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

function App() {

  const [data, setData] = useState(mockdata);
  
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
          <Route path='/gestor/:orderId' element={<UnPedido pedidos={data.pedidos} ruta={'/gestor/'}/>}/>
          <Route path='/comprador/:orderId' element={<UnPedido pedidos={data.pedidos} ruta={'/comprador/'}/>}/>
          <Route path="*" element={<NoMatch />} />
      </Routes>
    </div>
  );
}

export default App;
