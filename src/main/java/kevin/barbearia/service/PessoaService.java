package kevin.barbearia.service;

import kevin.barbearia.model.Pessoa;
import kevin.barbearia.repository.PessoaRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    @Autowired
    private final PessoaRepository pessoaRepository;

    public List<Pessoa> ListarTodas() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com id: " + id));
    }

    public Pessoa salvar(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizar(Long id, Pessoa dadosAtualizados) {
        Pessoa pessoa = buscarPorId(id);
        pessoa.setNome(dadosAtualizados.getNome());
        pessoa.setTelefone(dadosAtualizados.getTelefone());
        pessoa.setEmail(dadosAtualizados.getEmail());
        pessoa.setTipo(dadosAtualizados.getTipo());
        return pessoaRepository.save(pessoa);
    }

    public void deletar(Long id){
        if (!pessoaRepository.existsById(id)){
            throw new RuntimeException("Pessoa com o id: "+id+" não encontrada.");
        }else{
            pessoaRepository.deleteById(id);
        }
    }

}

