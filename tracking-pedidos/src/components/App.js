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
import Footer from './Footer';

function App() {

  const [data, setData] = useState(mockdata);
  
  return (
    <div>
      <Navbar/>
      <Routes>   
          <Route path="/" element={<Inicio/>}/>
          <Route path="/login" element={<Login/>}/>
          <Route path="/registro" element={<Registro/>}/>
          <Route path="/comprador" element={<Comprador pedidos={data.pedidos}/>}/>
          <Route path="/gestor" element={<Gestor pedidos={data.pedidos}/>}/>
          <Route path="*" element={<NoMatch />} />
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
