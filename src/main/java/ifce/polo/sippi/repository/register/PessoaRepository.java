package ifce.polo.sippi.repository.register;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.model.register.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>
{

/* --------------------------------------------------------------------------------------------- */

    public Pessoa findByCpf(String cpf);

/* --------------------------------------------------------------------------------------------- */

    public Long countByCpf(String cpf);

/* --------------------------------------------------------------------------------------------- */

    @Query("SELECT p.id, p.nome, p.cpf FROM Pessoa p WHERE p.ativado=true")
    public Page<Object[]> findIdNomeCpf(Pageable pageable);

/* --------------------------------------------------------------------------------------------- */

    @Query("SELECT p.id, p.nome, p.cpf FROM Pessoa p WHERE p.ativado=true AND LOWER(p.nome) LIKE LOWER(CONCAT('%', ?1,'%'))")
    public Page<Object[]> findIdNomeCpfByNome(String nome, Pageable pageable);

/* --------------------------------------------------------------------------------------------- */

}
