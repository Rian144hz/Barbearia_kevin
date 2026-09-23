package kevin.barbearia.service;

import kevin.barbearia.model.Servico;
import kevin.barbearia.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {

    @Autowired
    private final ServicoRepository servicoRepository;

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public Servico buscarPorId(Long id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado com id: " + id));
    }

    public Servico salvar(Servico servico) {
        return servicoRepository.save(servico);
    }

    public Servico atualizar(Long id, Servico dadosAtualizados) {
        Servico servico = buscarPorId(id);
        servico.setNome(dadosAtualizados.getNome());
        servico.setDuracaoMinutos(dadosAtualizados.getDuracaoMinutos());
        servico.setPreco(dadosAtualizados.getPreco());
        return servicoRepository.save(servico);
    }

    public void deletar(Long id) {
        if (!servicoRepository.existsById(id)) {
            throw new RuntimeException("Serviço não encontrado com id: " + id);
        }
        servicoRepository.deleteById(id);
    }
}