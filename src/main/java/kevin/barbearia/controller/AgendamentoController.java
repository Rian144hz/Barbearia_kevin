package kevin.barbearia.controller;

import kevin.barbearia.dto.AgendamentoRequestDTO;
import kevin.barbearia.dto.AgendamentoResponseDTO;
import kevin.barbearia.model.Agendamento;
import kevin.barbearia.model.StatusAgendamento;
import kevin.barbearia.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @GetMapping
    public List<AgendamentoResponseDTO> listarTodos() {
        return agendamentoService.listarTodos().stream()
                .map(AgendamentoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        Agendamento agendamento = agendamentoService.buscarPorId(id);
        return ResponseEntity.ok(AgendamentoResponseDTO.fromEntity(agendamento));
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> criar(@RequestBody AgendamentoRequestDTO dto) {
        Agendamento agendamento = agendamentoService.criar(
                dto.getClienteId(),
                dto.getBarbeiroId(),
                dto.getServicoId(),
                dto.getDataHora()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AgendamentoResponseDTO.fromEntity(agendamento));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AgendamentoResponseDTO> atualizarStatus(
            @PathVariable Long id, @RequestParam StatusAgendamento status) {
        Agendamento agendamento = agendamentoService.atualizarStatus(id, status);
        return ResponseEntity.ok(AgendamentoResponseDTO.fromEntity(agendamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        agendamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}