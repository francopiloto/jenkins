package ifce.polo.sippi.repository.register;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TableListRepository<E> extends JpaRepository<E, Short>
{

/* --------------------------------------------------------------------------------------------- */

    public Iterable<E> findAllByOrderByNomeAsc();

/* --------------------------------------------------------------------------------------------- */

}
