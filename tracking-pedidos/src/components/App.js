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

const [sesion, setSesion] = useState(
  JSON.parse(window.localStorage.getItem('sesion'))
)
//   const [users, setUsers] = useState(null);
//   const[loading, setLoading] = useState(true);
// const downloadUsers = async () => {
//   let downloadedUsers;
//   try {
//     const res = await fetch(CONFIG.users_url);
//     downloadedUsers = await res.json();
//   } catch (e) {
//     alert("No se ha podido recuperar la información."); 
//   }
//   setUsers(downloadedUsers);
// }

// useEffect(() => {
//   async function fetchDataUsers() {
//     await downloadUsers();
      
//     setTimeout(()=>{
//       setLoading(false);
//     },500);		
//   }

//   if(CONFIG.use_server){
//     fetchDataUsers()
//   } else{
//     setUsers(mockdata);
//     setTimeout(()=>{
//       setLoading(false);
//     },500);
//   };
// }, [loading]);

  useEffect(() => {
    window.localStorage.setItem('sesion', JSON.stringify(sesion))
    console.log("La sesión contiene el usuario:")
    console.log(JSON.parse(sesion))
    console.log(typeof(sesion))
  }, [sesion])
  
  return (
    <div>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossOrigin="anonymous"/>
      <Navbar sesion={sesion} logout={setSesion}/>
      <Routes>   
          <Route path="/" element={<Inicio sesion={sesion}/>}/>
          <Route path="/login" element={<Login login={setSesion}/>}/>
          <Route path="/registro" element={<Registro/>}/>
          <Route path="/comprador/:idUsuario/pedidos" element={<Comprador/>}/>
          <Route path="/gestor/:idUsuario/pedidos" element={<Gestor/>}/>
          <Route path="/comprador/:idUsuario/pedidos/:idPedido" element={<UnPedido/>}/>
          <Route path="/gestor/:idUsuario/pedidos/:idPedido" element={<UnPedido/>}/>
          <Route path="*" element={<NoMatch />} />
      </Routes>
    </div>
  );
}

export default App;
