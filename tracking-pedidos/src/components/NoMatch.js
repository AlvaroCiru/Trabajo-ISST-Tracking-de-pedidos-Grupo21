import {Link} from "react-router-dom";

export default function NotFound(props){
  return <div>
    <h3 id="info">Ruta no encontrada</h3>  
    <Link to="/"><button id="volver">VOLVER</button></Link>  
    </div>
}