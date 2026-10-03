package com.nassau.backend.service;

import java.time.LocalDateTime;
import com.nassau.backend.model.Senha;
import com.nassau.backend.repository.SenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SenhaService {

    @Autowired
    private SenhaRepository senhaRepository;

    public Senha emitirSenha(String tipo) {
        String dataAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        
        long quantidade = senhaRepository.countByTipo(tipo.toUpperCase());
        String numeroSequencial = String.format("%s-%s%03d", dataAtual, tipo.toUpperCase(), quantidade + 1);

        Senha novaSenha = new Senha(numeroSequencial, tipo.toUpperCase(), "AGUARDANDO");
        return senhaRepository.save(novaSenha);
    }

    public List<Senha> listarFilaEspera() {
        return senhaRepository.findByStatusOrderByDataHoraEmissaoAsc("AGUARDANDO");
    }

    public Senha chamarProximaSenha(Integer guiche) {
        List<Senha> senhasAguardando = senhaRepository.findByStatusOrderByDataHoraEmissaoAsc("AGUARDANDO");
        if (senhasAguardando.isEmpty()) {
            throw new RuntimeException("Não há senhas aguardando na fila.");
        }
        
        Senha proxima = senhasAguardando.get(0);
        proxima.setStatus("ATENDIDO");
        proxima.setDataHoraAtendimento(LocalDateTime.now());
        proxima.setGuiche(guiche);
        
        return senhaRepository.save(proxima);
    }

    
}