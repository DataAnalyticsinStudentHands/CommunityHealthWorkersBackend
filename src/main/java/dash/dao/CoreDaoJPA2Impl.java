package dash.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class CoreDaoJPA2Impl implements CoreDao {

	@PersistenceContext(unitName = "dashPersistence")
	private EntityManager entityManager;

	@Override
	public int getCoresByClassId(int class_id) {
		try {
			String qlString = "SELECT u FROM CoreEntity u WHERE u.class_id = ?1";
			TypedQuery<CoreEntity> query = entityManager.createQuery(qlString,
					CoreEntity.class);
			query.setParameter(1, class_id);
			return query.getResultList().size();
		} catch (NoResultException e) {
			return 0;
		}
	}
	
	@Override
	public void createCore(CoreEntity coreentity) {
			entityManager.persist(coreentity);
			entityManager.flush();// force insert to receive the id of the post
	}
}