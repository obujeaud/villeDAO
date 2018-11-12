package business.entities;

public class Ville {
	private long id;
	private String nomVille;
	private String pays;
	private int nbHab;

	public Ville(String nomVille, String pays, int nbHab) {
		super();
		this.nomVille = nomVille;
		this.pays = pays;
		this.nbHab = nbHab;
	}

	public Ville() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNomVille() {
		return nomVille;
	}

	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public int getNbHab() {
		return nbHab;
	}

	public void setNbHab(int nbHab) {
		this.nbHab = nbHab;
	}

	public long getId() {
		return id;
	}

	public void setId(long id2) {
		this.id = id2;
	}

}
