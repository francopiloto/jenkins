package ifce.polo.sippi.repository.register;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.dto.register.ProfileRegisterNotification;
import ifce.polo.sippi.model.register.Empresa;

@Repository
public interface EmpresaRepository extends PerfilRepository<Empresa>
{

/* --------------------------------------------------------------------------------------------- */

    public Empresa findByCnpj(String cnpj);

/* --------------------------------------------------------------------------------------------- */

    @Override
    @Query(value = "SELECT id, data_criacao AS dataCriacao, razao_social AS descricao FROM empresa WHERE ativado = false",
           nativeQuery = true)
    public Page<ProfileRegisterNotification> findPending(boolean ativado, Pageable pageable);

/* --------------------------------------------------------------------------------------------- */

}
