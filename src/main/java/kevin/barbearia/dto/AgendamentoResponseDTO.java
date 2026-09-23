package kevin.barbearia.dto;

import kevin.barbearia.model.Agendamento;
import kevin.barbearia.model.StatusAgendamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoResponseDTO {

    private Long id;
    private String nomeCliente;
    private String nomeBarbeiro;
    private String nomeServico;
    private LocalDateTime dataHora;
    private StatusAgendamento status;

    public static AgendamentoResponseDTO fromEntity(Agendamento agendamento) {
        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getCliente().getNome(),
                agendamento.getBarbeiro().getNome(),
                agendamento.getServico().getNome(),
                agendamento.getDataHora(),
                agendamento.getStatus()
        );
    }
}