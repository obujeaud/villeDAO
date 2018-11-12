package persistance.daotest;

import business.entities.Ville;
import persistance.TU.TU_Pere;
import persistance.dao.VilleDAO;

public class TestVilleDAO extends TU_Pere {
	VilleDAO vdao = new VilleDAO();
	private String selectList = "select count(id) from ville";
	String updateName = "Lyon";
	Ville v;
	
	public void testCreate() throws Exception {
		v = new Ville("Paris", "France", 12000000);
		vdao.create(v);
		assertNotNull(vdao.findById(v.getId()));
		assert (vdao.findById(vdao.findList().size())) != null;
		assertEquals(v.getNomVille(), vdao.findById(vdao.findList().size()).getNomVille());

		// Object null
		//assertNull(vdao.create(null));
	}
	
	public void testFindList() throws Exception {
		int realNb = getInserter().select(selectList).getDataAsInt();
		assertEquals(vdao.findList().size(), realNb);
	}

	public void testUpdateById() throws Exception {
		// Normal
		v = new Ville("Paris", "France", 12000000);
		vdao.create(v);
		v.setNomVille(updateName);
		vdao.updateById(v);
		assertEquals(v.getNomVille(), vdao.findById(v.getId()).getNomVille());

		// Object null
		//assertNull(vdao.updateById(null));

		// Object with id > list size
		/*v.setId(150);
		assertNull(vdao.updateById(v));
		assertNull(null);

		// Id not present in database
		v.setId(0);
		assertNull(vdao.updateById(v));*/
	}

	public void testDeleteById() throws Exception {
		// Normal
		v = new Ville("Paris", "France", 12000000);
		vdao.create(v);
		vdao.deleteById(v.getId());
		int realNb = getInserter().select(selectList).getDataAsInt();
		assertEquals(vdao.findList().size(), realNb);

		// Object with id > list size
		vdao.deleteById(15);
		realNb = getInserter().select(selectList).getDataAsInt();
		assertEquals(vdao.findList().size(), realNb);

		// Id not present in database
		vdao.deleteById(0);
		realNb = getInserter().select(selectList).getDataAsInt();
		assertEquals(vdao.findList().size(), realNb);
	}
}