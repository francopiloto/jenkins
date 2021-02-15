package ifce.polo.sippi.repository.access;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ifce.polo.sippi.dto.access.AccessBadge;
import ifce.polo.sippi.util.UID;

@Repository
public class UserAccessRepository
{
    @PersistenceContext private EntityManager entityManager;

/* --------------------------------------------------------------------------------------------- */

    @SuppressWarnings("unchecked")
    public List<AccessBadge> loadProfileBadges(UID uid, String profileName, String uniqueColumn,
            Boolean ativado)
    {
        String uidColumn = uid.getTable().equals(profileName) ? "id" : (uid.getTable() + "_id");

        StringBuilder sb = new StringBuilder("SELECT id");

        if (uniqueColumn != null) {
            sb.append(',').append(uniqueColumn);
        }

        sb.append(" FROM ").append(profileName)
          .append(" WHERE ").append(uidColumn).append('=').append(uid.getId());

        if (ativado != null) {
            sb.append(" AND ativado = ").append(ativado);
        }

        Query query = entityManager.createNativeQuery(sb.toString());
        List<Object> results = query.getResultList();

        Object[] data;

        List<AccessBadge> badges = new ArrayList<AccessBadge>();

        for (Object result : results)
        {
            if (result instanceof Object[])
            {
                data = (Object[]) result;
                badges.add(new AccessBadge(profileName, data[0].toString(), data[1].toString()));
            }
            else {
                badges.add(new AccessBadge(profileName, result.toString(), null));
            }
        }

        return badges;
    }

/* --------------------------------------------------------------------------------------------- */

}
