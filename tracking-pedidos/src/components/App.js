import './../style/App.css';
import { useState, useEffect } from 'react';
import Navbar from './navegacion/Navbar';
import {Route, Routes} from "react-router-dom"
import InicioLogin from './paginas/InicioLogin';
import NoMatch from './NoMatch.js';
import Login from './paginas/Login.js';
import Registro from './paginas/Registro.js';
import UnPedido from './paginas/UnPedido';
import {mockdata} from './../data/pedidos'
<<<<<<< HEAD
import Welcome from './paginas/Welcome';
import PaginaPedido from './paginas/PaginaPedido';
=======
import Comprador from './paginas/Comprador';
import Gestor from './paginas/Gestor';
>>>>>>> f56341a70018f7ef23af1c6b07ee1f23f91da160

function App() {

  const [data, setData] = useState(mockdata);
  
  return (
    <div>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossOrigin="anonymous"/>
      <Navbar/>
<<<<<<< HEAD
      <Routes>  
          <Route path='/' element={<Welcome/>} />  
          <Route path="/iniciologin" element={<InicioLogin pedidos={data.pedidos}/>}/>
          <Route path="/login" element={<Login/>}/>
          <Route path="/registro" element={<Registro/>}/>
          <Route path="/pedido/:pedidoId" element={<PaginaPedido pedidos={data.pedidos}/>}/>
=======
      <Routes>   
          <Route path="/" element={<Inicio/>}/>
          <Route path="/login" element={<Login/>}/>
          <Route path="/registro" element={<Registro/>}/>
          <Route path="/comprador" element={<Comprador pedidos={data.pedidos}/>}/>
          <Route path="/gestor" element={<Gestor pedidos={data.pedidos}/>}/>
          <Route path="*" element={<NoMatch />} />
      </Routes>
    </div>
  );
}

export default App;
