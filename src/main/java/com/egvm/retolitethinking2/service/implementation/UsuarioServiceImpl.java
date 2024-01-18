package com.egvm.retolitethinking2.service.implementation;


import com.egvm.retolitethinking2.models.Usuario;
import com.egvm.retolitethinking2.models.UsuarioRol;
import com.egvm.retolitethinking2.repository.UsuarioRepository;
import com.egvm.retolitethinking2.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    private void usernameOrEmailExists(String username, String email){
        if(usuarioRepository.existsByUsername(username)){
            throw new DuplicateKeyException("El username " + username + " ya fue registrado");
        }
        if (usuarioRepository.existsByEmail(email)){
            throw new DuplicateKeyException("El correo " + email + " ya fue regisrado por otro usuario");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario createUsuario(Usuario usuario) {
        this.usernameOrEmailExists(usuario.getUsername(), usuario.getEmail());

        Set<UsuarioRol> usuarioRoles = new HashSet<>();
        for(UsuarioRol usuarioRol : usuario.getUsuarioRoles()){
            UsuarioRol rol = new UsuarioRol();
            rol.setRol(usuarioRol.getRol());
            usuarioRoles.add(rol);
        }

        Usuario nuevoUsuario = Usuario.builder()
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .username(usuario.getUsername())
                .password(passwordEncoder.encode(usuario.getPassword()))
                .usuarioRoles(usuarioRoles)
                .build();

        return usuarioRepository.save(nuevoUsuario);
    }
}
