package machine_a_glace;

import java.util.concurrent.TimeUnit;

public class Terrain {
	private static int taille = 30;
	public static Case terrain[][] = new Case[taille][taille];

	private Terrain() {

	}

	public static void initialiser() {
		for (int i = 0; i < taille; i++) {
			if (i == 0 || i == taille - 1) {
				for (int j = 0; j < taille; j++) {
					terrain[i][j] = new Case();
					terrain[i][j].setCase(Contenu.Obstacle);
				}
			} else {
				for (int j = 0; j < taille; j++) {
					if (j == 0 || j == taille - 1) {
						terrain[i][j] = new Case();
						terrain[i][j].setCase(Contenu.Obstacle);
					} else {
						terrain[i][j] = new Case();
						if (Math.random() < 0.20) {
							terrain[i][j].setCase(Contenu.Obstacle);
						}
					}

				}
			}

		}
	}

	public static void afficher() {
		for (int i = 0; i < 10; i++) {
			for (Case c : terrain[i]) {
				System.out.print(c.toString() + "|");
			}
			System.out.println();
			for (int j = 0; j < 10; j++) {
				System.out.print("------");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int count = 0;
		Terrain.initialiser();
		Terrain.afficher();

		Joueur j = new Joueur(4, 4, Couleur.Bleu);
		Robot r = new Robot(3, 3, Couleur.Rouge);

		while (true) {
			count++;
			if (j.next_case().isAccessible()) {
				j.Avancer(1);
			} else {
				if (Math.random() < 0.5) {
					switch (j.direction()) {
					case Nord:
						j.Tourner(Direction.Est);
						break;
					case Est:
						j.Tourner(Direction.Sud);
						break;
					case Sud:
						j.Tourner(Direction.Ouest);
						break;
					case Ouest:
						j.Tourner(Direction.Nord);
						break;
					}
				} else {
					switch (j.direction()) {
					case Nord:
						j.Tourner(Direction.Ouest);
						break;
					case Est:
						j.Tourner(Direction.Nord);
						break;
					case Sud:
						j.Tourner(Direction.Est);
						break;
					case Ouest:
						j.Tourner(Direction.Sud);
						break;
					}
				}
			}

			if (r.next_case().isAccessible()) {
				r.Avancer(1);
			} else {
				if (Math.random() < 0.5) {
					switch (r.direction()) {
					case Nord:
						r.Tourner(Direction.Est);
						break;
					case Est:
						r.Tourner(Direction.Sud);
						break;
					case Sud:
						r.Tourner(Direction.Ouest);
						break;
					case Ouest:
						r.Tourner(Direction.Nord);
						break;
					}
				} else {
					switch (r.direction()) {
					case Nord:
						r.Tourner(Direction.Ouest);
						break;
					case Est:
						r.Tourner(Direction.Nord);
						break;
					case Sud:
						r.Tourner(Direction.Est);
						break;
					case Ouest:
						r.Tourner(Direction.Sud);
						break;
					}
				}
			}
			Terrain.afficher();
			TimeUnit.SECONDS.sleep(1);

		}
	}
}
