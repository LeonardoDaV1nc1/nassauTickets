package com.nassau.backend.controller;

import com.nassau.backend.model.Senha;
import com.nassau.backend.service.SenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/senhas")
@CrossOrigin(origins = "*")
public class SenhaController {

    @Autowired
    private SenhaService senhaService;

    @PostMapping("/emitir")
    public ResponseEntity<Senha> emitirSenha(@RequestParam String tipo) {
        Senha novaSenha = senhaService.emitirSenha(tipo);
        return ResponseEntity.ok(novaSenha);
    }

    @GetMapping
    public ResponseEntity<List<Senha>> listarFila() {
        List<Senha> fila = senhaService.listarFilaEspera();
        return ResponseEntity.ok(fila);
    }

    @PutMapping("/chamar")
    public ResponseEntity<Senha> chamarProxima(@RequestParam(defaultValue = "1") Integer guiche) {
        Senha senhaAtendida = senhaService.chamarProximaSenha(guiche);
        return ResponseEntity.ok(senhaAtendida);
    }

}