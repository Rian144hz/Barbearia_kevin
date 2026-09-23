package kevin.barbearia.controller;

import kevin.barbearia.model.Pessoa;
import kevin.barbearia.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listarTodas() {
        return pessoaService.ListarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa) {
        Pessoa salva = pessoaService.salvar(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.atualizar(id, pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pessoaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}