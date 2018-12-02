
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5,       // aloitustalukon koko
                            OLETUSKASVATUS = 5;     // luotava uusi taulukko on 
                                                    // näin paljon isompi kuin vanha
    
    private int kasvatuskoko;                       // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;                         // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLukumaara;                 // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IllegalArgumentException("Kapasiteetin ja kasvatuskoon on oltava positiivisia");
        }

        lukujono = new int[kapasiteetti];
        
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        
        alkioidenLukumaara = 0;
        this.kasvatuskoko = kasvatuskoko;

    }
    
    public void kasvataTaulukkoa() {
        int[] uusiTaulukko = new int[lukujono.length + kasvatuskoko];

        kopioiTaulukko(lukujono, uusiTaulukko);

        lukujono = uusiTaulukko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            lukujono[alkioidenLukumaara] = luku;
            alkioidenLukumaara++;
            
            if (alkioidenLukumaara == lukujono.length) {
                kasvataTaulukkoa();
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLukumaara; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        
        return false;
    }
    
    public int haeLuvunIndeksi(int luku) {
        for (int i = 0; i < alkioidenLukumaara; i++) {
            if (lukujono[i] == luku) {
                return i;
            }
        }

        return -1;
    }

    public boolean poista(int luku) {
        int apu;
        
        int indeksi = haeLuvunIndeksi(luku);

        if (indeksi != -1) {
            for (int i = indeksi; i < alkioidenLukumaara - 1; i++) {
                apu = lukujono[i];
                lukujono[i] = lukujono[i + 1];
                lukujono[i + 1] = apu;
            }
            alkioidenLukumaara--;
            return true;
        }


        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int koko() {
        return alkioidenLukumaara;
    }


    @Override
    public String toString() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLukumaara; i++) {
            if (i > 0) {
                tuotos += ", ";
            }

            tuotos += lukujono[i];
        }
        
        tuotos += "}";

        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLukumaara];
        
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko joukkoA, IntJoukko joukkoB) {
        IntJoukko yhdisteJoukko = new IntJoukko();
        
        int[] aTaulu = joukkoA.toIntArray();
        int[] bTaulu = joukkoB.toIntArray();
        
        for (int i = 0; i < aTaulu.length; i++) {
            yhdisteJoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdisteJoukko.lisaa(bTaulu[i]);
        }

        return yhdisteJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko joukkoA, IntJoukko joukkoB) {
        IntJoukko leikkausJoukko = new IntJoukko();
        
        int[] aTaulu = joukkoA.toIntArray();
        int[] bTaulu = joukkoB.toIntArray();
        
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkausJoukko.lisaa(bTaulu[j]);
                }
            }
        }
        
        return leikkausJoukko;
    }
    
    public static IntJoukko erotus ( IntJoukko joukkoA, IntJoukko joukkoB) {
        IntJoukko erotusJoukko = new IntJoukko();
        
        int[] aTaulu = joukkoA.toIntArray();
        int[] bTaulu = joukkoB.toIntArray();
        
        for (int i = 0; i < aTaulu.length; i++) {
            erotusJoukko.lisaa(aTaulu[i]);
        }
        
        for (int i = 0; i < bTaulu.length; i++) {
            erotusJoukko.poista(i);
        }
 
        return erotusJoukko;
    }
        
}
