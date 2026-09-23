package kevin.barbearia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_pessoa")
public class Pessoa {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String nome;

   @Column(nullable = false)
   private String telefone;

   @Column(nullable = false)
   private String email;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private TipoPessoa tipo;




}