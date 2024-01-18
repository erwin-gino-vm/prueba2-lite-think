package com.egvm.retolitethinking2.service;
import com.egvm.retolitethinking2.models.Rol;

import java.util.List;

public interface IRolService {
    Rol createRol(Rol rol);
    List<Rol> findAllRoles();

    Rol updateRolById(Integer id, Rol rol);

    Rol findRolById(Integer id);
    void deleteRolById(Integer id);
}
