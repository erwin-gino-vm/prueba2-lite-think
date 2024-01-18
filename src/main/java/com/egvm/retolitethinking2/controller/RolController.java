package com.egvm.retolitethinking2.controller;

import com.egvm.retolitethinking2.models.Rol;
import com.egvm.retolitethinking2.service.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/roles")
@RequiredArgsConstructor
public class RolController {

    private final IRolService rolService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('INVITED')")
    public ResponseEntity<List<Rol>> findAllRoles(){
        return new ResponseEntity<>(rolService.findAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Rol> findRolById(@PathVariable Integer id){
        return new ResponseEntity<>(rolService.findRolById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol){
        return new ResponseEntity<>(rolService.createRol(rol), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Rol> updateRol(@PathVariable Integer id, @RequestBody Rol rol){
        return new ResponseEntity<>(rolService.updateRolById(id, rol), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteRolById(@PathVariable Integer id){
        rolService.deleteRolById(id);
        return new ResponseEntity<>("El rol con id '" + id +"' fue eliminado", HttpStatus.OK);
    }
}
