package ifce.polo.sippi.repository.access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.model.access.Usuario;
import ifce.polo.sippi.model.register.Pessoa;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{

/* --------------------------------------------------------------------------------------------- */

    public Usuario findByNome(String nome);

/* --------------------------------------------------------------------------------------------- */

    public Usuario findByPessoa(Pessoa pessoa);

/* --------------------------------------------------------------------------------------------- */

}
