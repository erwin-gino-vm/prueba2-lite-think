package com.egvm.retolitethinking2.service;

import com.egvm.retolitethinking2.models.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> findAllUsuarios();
    Usuario createUsuario(Usuario usuario);
}
