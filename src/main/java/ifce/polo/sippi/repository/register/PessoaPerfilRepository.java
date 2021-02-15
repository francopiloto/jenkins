package ifce.polo.sippi.repository.register;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import ifce.polo.sippi.dto.register.ProfileRegisterNotification;
import ifce.polo.sippi.model.register.Pessoa;

@NoRepositoryBean
public interface PessoaPerfilRepository<Profile> extends PerfilRepository<Profile>
{

/* --------------------------------------------------------------------------------------------- */

    public Long countByAtivadoAndPessoa(boolean ativado, Pessoa pessoa);

/* --------------------------------------------------------------------------------------------- */

    @Override
    @Query(value = "SELECT e.id, e.data_criacao AS dataCriacao, p.nome AS descricao FROM #{#entityName} e "    +
                   "JOIN pessoa p ON (e.pessoa_id = p.id) WHERE e.ativado = false",
           nativeQuery = true)
    public Page<ProfileRegisterNotification> findPending(boolean ativado, Pageable pageable);

/* --------------------------------------------------------------------------------------------- */

}
