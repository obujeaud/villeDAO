package persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import business.entities.Ville;
import persistance.manager.JDBCManager;

public class VilleDAO {

	public static final String sqlSelectAllVille = "SELECT * FROM Ville";
	public static final String sqlInsertVille = "INSERT INTO Ville (nom_ville, pays, nbHabitant) values (?,?,?)";
	public static final String sqlUpdateVille = "UPDATE Ville SET  nom_ville = ?, pays = ?, nbHabitant = ? WHERE id = ?";
	public static final String sqlDeleteVille = "DELETE FROM Ville WHERE id = ?";
	public static final String sqlSelectOneVille = "SELECT * FROM Ville WHERE id = ?";



	public Ville create(Ville pT) throws Exception {
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement preparedStatement = cnx.prepareStatement(sqlInsertVille ,PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, pT.getNomVille());
		preparedStatement.setString(2, pT.getPays());
		preparedStatement.setInt(3, pT.getNbHab());
		preparedStatement.execute();

		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		long id = 0;
		while(resultSet.next()) {
			id = resultSet.getLong("GENERATED_KEY");
			//System.out.println("new key is "+id);
		}
		pT.setId(id);
		JDBCManager.getInstance().closeConnection();
		return pT;
	}

	public Ville findById(long pId) throws Exception {
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement preparedStatement = cnx.prepareStatement(sqlSelectOneVille);
		preparedStatement.setLong(1, pId);
		ResultSet st = preparedStatement.executeQuery();

		Ville ville = null;
		while(st.next()) {
			String nomVille = st.getString("nom_ville");
			String pays = st.getString("pays");
			Integer nbHabitant = st.getInt("nbHabitant");
			/* ... */
			ville = new Ville(nomVille, pays, nbHabitant);
		}
		JDBCManager.getInstance().closeConnection();
		return ville;
	}

	public List<Ville> findList() throws Exception {
		List<Ville> list = new ArrayList<>();
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement preparedStatement = cnx.prepareStatement(sqlSelectAllVille);
		ResultSet st = preparedStatement.executeQuery();

		Ville ville = null;
		while(st.next()) {
			String nomVille = st.getString("nom_ville");
			String pays = st.getString("pays");
			Integer nbHabitant = st.getInt("nbHabitant");
			/* ... */
			ville = new Ville(nomVille, pays, nbHabitant);
			list.add(ville);
		}
		JDBCManager.getInstance().closeConnection();
		return list;
	}

	public void updateById(Ville pT) throws Exception {
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement preparedStatement = cnx.prepareStatement(sqlUpdateVille,PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, pT.getNomVille());
		preparedStatement.setString(2, pT.getPays());
		preparedStatement.setInt(3, pT.getNbHab());
		preparedStatement.setLong(4, pT.getId());
		preparedStatement.execute();

		JDBCManager.getInstance().closeConnection();

	}

	public void deleteById(long pId) throws Exception {
		if(pId >= 0) {
			Connection cnx = JDBCManager.getInstance().openConection();	
			PreparedStatement preparedStatement = cnx.prepareStatement(sqlDeleteVille);
			preparedStatement.setLong(1, pId);
			preparedStatement.execute();
			JDBCManager.getInstance().closeConnection();
		}

	}
}
