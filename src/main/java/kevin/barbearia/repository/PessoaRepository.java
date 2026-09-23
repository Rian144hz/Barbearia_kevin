package kevin.barbearia.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import kevin.barbearia.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
}
