package kevin.barbearia.service;

import kevin.barbearia.model.Agendamento;
import kevin.barbearia.model.Pessoa;
import kevin.barbearia.model.Servico;
import kevin.barbearia.model.StatusAgendamento;
import kevin.barbearia.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    @Autowired
    private final AgendamentoRepository agendamentoRepository;
    private final PessoaService pessoaService;
    private final ServicoService servicoService;

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    public Agendamento buscarPorId(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado com id: " + id));
    }

    public Agendamento criar(Long clienteId, Long barbeiroId, Long servicoId, LocalDateTime dataHora) {
        Pessoa cliente = pessoaService.buscarPorId(clienteId);
        Pessoa barbeiro = pessoaService.buscarPorId(barbeiroId);
        Servico servico = servicoService.buscarPorId(servicoId);

        verificarConflitoDeHorario(barbeiroId, dataHora);

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setBarbeiro(barbeiro);
        agendamento.setServico(servico);
        agendamento.setDataHora(dataHora);
        agendamento.setStatus(StatusAgendamento.AGENDADO);

        return agendamentoRepository.save(agendamento);
    }

    public Agendamento atualizarStatus(Long id, StatusAgendamento novoStatus) {
        Agendamento agendamento = buscarPorId(id);
        agendamento.setStatus(novoStatus);
        return agendamentoRepository.save(agendamento);
    }

    public void deletar(Long id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new RuntimeException("Agendamento não encontrado com id: " + id);
        }
        agendamentoRepository.deleteById(id);
    }

    private void verificarConflitoDeHorario(Long barbeiroId, LocalDateTime dataHora) {
        boolean existeConflito = agendamentoRepository
                .existsByBarbeiroIdAndDataHora(barbeiroId, dataHora);
        if (existeConflito) {
            throw new RuntimeException("Barbeiro já possui um agendamento nesse horário.");
        }
    }
}