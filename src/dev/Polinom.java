package dev;
import java.util.*; 
public class Polinom {
	String[] monomStr;
	ArrayList<Monom> monom = new ArrayList<Monom>();
	public int x;
	
	public Polinom(String[] monomStr, int x) {
		this.monomStr = monomStr;
		this.x = x;
		for(int i=0; i<monomStr.length; i++) {
			System.out.println(monomStr[i]);
			monom.add(new Monom(monomStr[i]));
		}
		for (int i = 0; i < monom.size(); i++) {
		    Monom temp = monom.get(i);
		    temp.afisare();
		}
		// Se sorteaza la final polinomul
		this.sort_minify();
		this.sort_minify();
	}
	
	private Polinom(int x) {
		this.x = x;
	}
	
	public String afisare() {
		String display_pol = new String("");
		for(int i=0;i<monom.size();i++) {
			if(monom.get(i).isNegative()) {
				display_pol += monom.get(i).getMonom();
			} else {
				display_pol += (0 == i) ? monom.get(i).getMonom() : "+" + monom.get(i).getMonom();		
			}
		}
		return display_pol;
	}
	
	public int valoare() {
		int s = 0;
		for(int i=0;i<monom.size(); i++) {
			s+=monom.get(i).valoare(x);
		}
		return s;
	}
	
	private int getGrad() {
		int c=-1; //nu exista polinom cu grad negativ
		for(int i=0;i<monom.size();i++) {
			if(c < monom.get(i).getPutere()) c = monom.get(i).getPutere();
		}
		return c;
	}
	
	private void sort_minify() {
		for(int i=0;i<monom.size();i++) {
			int putere = monom.get(i).getPutere();
			for(int j=i+1;j<monom.size();j++) {
				int temp_putere = monom.get(j).getPutere();
				if(putere == temp_putere) {
					monom.get(i).addCoef(monom.get(j).getCoeff());
					this.monom.remove(j);
				}
			}
		}
		/**
		 * Collections reprezinta clasa cu care putem trata elementele listelor
		 * */
		Collections.sort(this.monom, Monom.getCompByPutere());
	}
	
	public Polinom add(Polinom poli2) {
		// Definim polinomul rezultant in care vom memora suma
		Polinom poli3 = new Polinom(x);
		
		for(int i=0;i<this.monom.size();i++) {
			// De mentionat ca in acest moment polinoamele sunt minime si sortate
			int p1 = this.monom.get(i).getPutere();
			int c1 = this.monom.get(i).getCoeff();
			
			int i_pow = poli2.getIndexPutere(p1);
			if(i_pow == -1) {
				// Nu am gasit deci adaugam efectiv un nou monom
				poli3.monom.add(new Monom(c1, p1));
			} else {
				// Am gasit ceva in polinomul 2 deci sumam
				int p3 = poli2.monom.get(i_pow).getPutere();
				int c3 = poli2.monom.get(i_pow).getCoeff();
				poli3.monom.add(new Monom(c1+c3, p3));
				// Eliminam ce avem deja
				poli2.monom.remove(i_pow);
			}
		}
		// Pasul final mai e sa adaugam ce a ramas in poli2
		for(int j=0;j<poli2.monom.size();j++) {
			int p2 = poli2.monom.get(j).getPutere();
			int c2 = poli2.monom.get(j).getCoeff();
			poli3.monom.add(new Monom(c2, p2));
		}
		return poli3;
	}
	
	public Polinom times(Polinom poli2) {
		Polinom poli3 = new Polinom(x);
		for(int i=0;i<this.monom.size();i++) {
			int c1 = this.monom.get(i).getCoeff();
			int p1 = this.monom.get(i).getPutere();
			for(int j=0;j<poli2.monom.size();j++) {
				int c2 = poli2.monom.get(j).getCoeff();
				int p2 =  poli2.monom.get(j).getPutere();
				// Pentru simplificarea procesului, doar adaugam monoamele
				poli3.monom.add(new Monom(c1*c2, p1+p2));
			}
		}
		poli3.sort_minify();
		poli3.sort_minify();
		return poli3;
	}
	
	private int getIndexPutere(int putere) {
		for(int i=0;i<monom.size();i++) {
			Monom mon = monom.get(i);
			if(putere == mon.getPutere()) 
				return i;
		}
		return -1;
	}
}
