package org.generation.ecomerce.service;

import java.util.List;
import java.util.Optional;

import org.generation.ecomerce.model.ChangePassword;
import org.generation.ecomerce.model.Usuario;
import org.generation.ecomerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UsuarioService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	private final UsuarioRepository usuarioRepository;
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public List<Usuario> getAllUsuarios(){
		return usuarioRepository.findAll();
	}

	public Usuario getUsuario(Long id) {
		return usuarioRepository.findById(id).orElseThrow(
				()-> new IllegalArgumentException("Usuario con id "+id+" no existe")
				);
	}

	public Usuario deleteUsuario(Long id) {
		Usuario userTmp = null;
		if(usuarioRepository.existsById(id)) {
			userTmp = usuarioRepository.findById(id).get();
			usuarioRepository.deleteById(id);
		}
		return userTmp;
	}

	public Usuario addUsuario(Usuario usuario) {
		Usuario tmp=null;
		if(usuarioRepository.findByEmail(usuario.getEmail()).isEmpty()) {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			tmp=usuarioRepository.save(usuario);
		}
		return tmp;
	}

	public Usuario updateeUsuario(Long id, ChangePassword changePassword) {
		Usuario tmp=null;
		if(usuarioRepository.existsById(id)) {
			if((changePassword.getPassword() !=null) && 
					(changePassword.getNewPassword() !=null)) {
				tmp=usuarioRepository.findById(id).get();
				if(passwordEncoder.matches(changePassword.getPassword(),tmp.getPassword())) {
					tmp.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
					usuarioRepository.save(tmp);
				} else {
					tmp=null;
				}//if eq
			}//if null
		}else {
			System.out.println("Update - El usuario con id "+id+" no existe");
		}//else
		return tmp;
	}

public boolean validateUsuario(Usuario usuario) {
	Optional<Usuario> userByEmail=
			usuarioRepository.findByEmail(usuario.getEmail());
	if(userByEmail.isPresent()) {
		Usuario user=userByEmail.get();
		if(passwordEncoder.matches(usuario.getPassword(),user.getPassword())) {
			return true;
		}//if equals
	}//if isPresent
	return false;
}//validateUsuario

}