package ifce.polo.sippi.repository.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import ifce.polo.sippi.model.config.TableItemList;

@Repository
public class TableListDataRepository
{
    @PersistenceContext private EntityManager entityManager;

/* --------------------------------------------------------------------------------------------- */

    @SuppressWarnings("unchecked")
    public List<Object[]> getIntermediateFields(String tableName)
    {
        StringBuilder sb = new StringBuilder("SELECT")
                .append(" tc.table_name, kcu.column_name FROM")
                .append(" information_schema.table_constraints tc")
                .append(" JOIN information_schema.key_column_usage kcu ON tc.constraint_name = kcu.constraint_name")
                .append(" JOIN information_schema.constraint_column_usage ccu ON ccu.constraint_name = tc.constraint_name")
                .append(" WHERE constraint_type = 'FOREIGN KEY' AND ccu.table_name='")
                .append(tableName)
                .append("'");

        Query query = entityManager.createNativeQuery(sb.toString());
        List<Object[]> results = query.getResultList();

        return results;
    }

/* --------------------------------------------------------------------------------------------- */

    @SuppressWarnings("unchecked")
    public PageImpl<TableItemList> loadTableListData(String tableName, Pageable pageable)
    {
        List<Object[]> intermediateFields = getIntermediateFields(tableName);

        StringBuilder sb = new StringBuilder("SELECT a.id, a.nome, COALESCE(b.sum, 0) AS vinculos, a.ativado FROM ")
                .append(tableName).append(" a FULL OUTER JOIN (SELECT SUM(c) AS sum, id FROM (");


        for (Object[] dataField : intermediateFields)
        {
            sb.append(" SELECT COUNT(*) AS c, ")
              .append(dataField[1])
              .append(" as id FROM ")
              .append(dataField[0])
              .append(" WHERE ")
              .append(dataField[1])
              .append(" IS NOT NULL GROUP BY ")
              .append(dataField[1]);

            if (!dataField.equals(intermediateFields.get(intermediateFields.size() - 1))) {
                sb.append(" UNION ALL ");
            }
        }

        sb.append(") AS a GROUP BY id) AS b ON (a.id = b.id) ORDER BY a.id");

        Query query = entityManager.createNativeQuery(sb.toString());
        List<Object> results = query.getResultList();

        Object[] data;
        List<TableItemList> items = new ArrayList<TableItemList>();

        for (Object result: results)
        {
            data = (Object[]) result;
            items.add(new TableItemList((Short)data[0], data[1].toString(), (BigDecimal) data[2], (boolean) data[3]));
        }

        PageImpl<TableItemList> page = new PageImpl<TableItemList>(items, pageable, results.size());

        return page;
    }

/* --------------------------------------------------------------------------------------------- */

}
