package es.upm.dit.isst.backend;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.upm.dit.isst.backend.model.Direccion;
import es.upm.dit.isst.backend.model.Empresa;
import es.upm.dit.isst.backend.model.Usuario;
import es.upm.dit.isst.backend.repository.UsuarioRepository;



@SpringBootTest
class BackendTest {

	@Autowired
	UsuarioRepository repo;

	@Test
	void prueba1(){
		boolean a = true;
		assertTrue(a);
	}

	@Test
	void compruebaSetNombreUsuario() {
		Direccion direccion1 = new Direccion(0, 25.3, 0.29, "Cuenca", "Tarancon", "calle santo tomas", "28698");
		Empresa empresa1 = new Empresa(0, "empresa1", "empresa1@gmail.com", "62512555", direccion1);
		Usuario usu1 = new Usuario(7, "raul", "raul@gmail.com", "contra", "69785695", false, empresa1);
		Usuario usu2 = new Usuario();

		usu2.setNombre("alberto");

		System.out.println(usu1.getNombre());
		System.out.println(usu2.getNombre());

		assertNotEquals(usu1.getNombre(), usu2.getNombre());
	}

	@Test
	void compruebaSetContrasenaUsuario() {
		Direccion direccion1 = new Direccion(0, 25.3, 0.29, "Cuenca", "Tarancon", "calle santo tomas", "28698");
		Empresa empresa1 = new Empresa(0, "empresa1", "empresa1@gmail.com", "62512555", direccion1);
		Usuario usu1 = new Usuario(7, "raul", "raul@gmail.com", "contra", "69785695", false, empresa1);
		Usuario usu2 = new Usuario();

		usu2.setContrasena("pass");

		System.out.println(usu1.getContrasena());
		System.out.println(usu2.getContrasena());

		assertNotEquals(usu1.getContrasena(), usu2.getContrasena());
	}

	@Test
	void compruebaSetEmailUsuario() {
		Direccion direccion1 = new Direccion(0, 25.3, 0.29, "Cuenca", "Tarancon", "calle santo tomas", "28698");
		Empresa empresa1 = new Empresa(0, "empresa1", "empresa1@gmail.com", "62512555", direccion1);
		Usuario usu1 = new Usuario(7, "raul", "raul@gmail.com", "contra", "69785695", false, empresa1);
		Usuario usu2 = new Usuario();

		usu2.setEmail("j1@gmail.com");

		System.out.println(usu1.getEmail());
		System.out.println(usu2.getEmail());

		assertNotEquals(usu1.getEmail(), usu2.getEmail());
	}

	@Test
	void compruebaSetTelefonoUsuario() {
		Direccion direccion1 = new Direccion(0, 25.3, 0.29, "Cuenca", "Tarancon", "calle santo tomas", "28698");
		Empresa empresa1 = new Empresa(0, "empresa1", "empresa1@gmail.com", "62512555", direccion1);
		Usuario usu1 = new Usuario(7, "raul", "raul@gmail.com", "contra", "69785695", false, empresa1);
		Usuario usu2 = new Usuario();

		usu2.setTelefono("5985885");

		System.out.println(usu1.getTelefono());
		System.out.println(usu2.getTelefono());

		assertNotEquals(usu1.getTelefono(), usu2.getTelefono());
	}

	@Test
	void compruebaFindByNombre(){
		Usuario usu1 = new Usuario(7, "raul", "raul@gmail.com", "contra", "69785695", false, null);
		repo.save(usu1);
		List<Usuario> a = repo.findByNombre("raul");
		boolean resultado = a.isEmpty();
		assertFalse(resultado);
		assertEquals(usu1, a.get(0));
	}

}
