package org.generation.ecomerce;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.generation.ecomerce.model.Producto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;


@SpringBootTest
@AutoConfigureMockMvc
class EcomerceApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Prueba para obtener (GET) la lista de productos")
	void pruebaGET() throws Exception {
		this.mockMvc.perform(
				get("/api/productos/1"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(
				content().string(containsString("norma1.gif"))
				);
	}
	@Test
	@Disabled("Probado la primera vez y deshabilitado")
	@DisplayName("Prueba para delete ID 2")
	void pruebaDELETE() throws Exception {
		this.mockMvc.perform(
				delete("/api/productos/2"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(
				content().string(containsString("bic2.jpg"))
				);
	}
	@Test
	@DisplayName("Prueba para crear un nuevo producto")
	void pruebaPOST() throws Exception{
		Producto p=new Producto();
		p.setNombre("Marcador");
		p.setDescripcion("Esta bonito");
		p.setImagen("marcador.jpg");
		p.setPrecio(10);
		
		this.mockMvc.perform(
				post("/api/productos/")
				.contentType(MediaType.APPLICATION_JSON)
				.contentType(asJsonString(p))
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(
				content().string(containsString("marcador.jpg")
						)
				);
	}//post
	@Test
	@DisplayName("Se modifica el producto 1 en precio")
	void pruebaPUT()  throws Exception {
		this.mockMvc.perform(
				put("/api/productos/1")
				.queryParam("precio","1.5"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(
				content().string(containsString("norma1.gif")
						
						)
				);
		}
	private static String asJsonString(final Object obj) {
	    try {
	      return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }//catch
	 } // asJsonString

}
