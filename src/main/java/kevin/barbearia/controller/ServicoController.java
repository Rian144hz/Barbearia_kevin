package kevin.barbearia.controller;

import kevin.barbearia.model.Servico;
import kevin.barbearia.service.ServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@RequiredArgsConstructor
public class ServicoController {

    @Autowired
    private final ServicoService servicoService;

    @GetMapping
    public List<Servico> listarTodos() {
        return servicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Servico> criar(@RequestBody Servico servico) {
        Servico salvo = servicoService.salvar(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable Long id, @RequestBody Servico servico) {
        return ResponseEntity.ok(servicoService.atualizar(id, servico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        servicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}