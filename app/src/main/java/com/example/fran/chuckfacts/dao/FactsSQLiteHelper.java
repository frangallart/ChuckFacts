package com.example.fran.chuckfacts.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Classe que estén les funcionalitats de SQLiteOpenHelper
 *
 * Created by Fran on 20/02/2015.
 */
public class FactsSQLiteHelper extends SQLiteOpenHelper {

    private Context context;
    private String nomBD;
    private CursorFactory factory;
    private int versio;

    // Sentència SQL per crear la taula de Facts
    private final String SQL_CREATE_FACTS = "CREATE TABLE Facts (" +
            " codi INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            " title TEXT," +
            " content TEXT," +
            " gif TEXT);";

    // Sentència SQL per inserir les dades a la taula
    private String SQL_INSERT_FACTS = "INSERT INTO Facts (title, content, gif) VALUES ";

    /**
     * Constructor amb paràmetres
     *
     * @param context context de l'aplicació
     * @param nomBD   nom de la base de dades
     * @param factory cursor o null
     * @param versio  versió de la base de dades. Si és més gran que l'actual es farà un Upgrade;
     *                si és més petita es faraà un Downgrade
     */
    public FactsSQLiteHelper(Context context, String nomBD, CursorFactory factory, int versio) {
        super(context, nomBD, factory, versio);
        this.context = context;
        this.nomBD = nomBD;
        this.factory = factory;
        this.versio = versio;
    }

    /**
     * Event que es produeix quan s'ha de crear la BD
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // S'execcuten les sentències SQL de creació de la BD
        db.execSQL(SQL_CREATE_FACTS);
        createInsertsData();
        db.execSQL(SQL_INSERT_FACTS);
    }

    /**
     * Mètode que ens permet introduir les dades a la base de dades
     */
    public void createInsertsData() {
        String[] rNumber = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IV", "X"};

        String[] facts = {"Les llàgrimes de Chuck Norris curen el càncer. Llàstima que mai ha plorat."
                , "Chuck Norris va comptar fins a l\'infinit, dues vegades."
                , "Chuck Norris no caça, perquè la paraula caça infereix la probabilitat de fracàs. Chuck Norris va a matar."
                , "Si pots veure a Chuck Norris, ell pot veure\'t. Si no pots veure a Chuck Norris pots estar a pocs segons de la mort."
                , "Chuck Norris va vendre la seva ànima al diable per una rude aparença i una inigualable capacitat per les arts marcials. Poc després del pacte, Chuck va clavar una puntada voladora a la cara del diable i es va emportar la seva ànima. El diable, que aprecia la ironia, no podia estar enutjat i va admetre que l\'hauria d\'haver vist venir. Ara juguen al pòquer cada segon dimecres de cada mes."
                , "No hi ha mentó després de la barba de Chuck Norris. Només hi ha un altre puny."
                , "Chuck Norris va construir una màquina del temps i va tornar a temps per aturar l\'assassinat de JFK. Quan Oswald va disparar, Chuck Norris va aturar les tres bales amb la seva barba, desviant-les del president. El cap de JFK va esclatar de pura sorpresa."
                , "Chuck Norris ja ha estat a Mart, és per això que allà no hi ha senyals de vida."
                , "l\'IPod de Chuck Norris venia amb un carregador real en lloc de només un cable USB."
                , "Un cec una vegada va trepitjar una sabata de Chuck Norris. Chuck va respondre: \'No saps qui sóc? Sóc Chuck Norris!\' La sola menció del seu nom va guarir la ceguesa d'aquest home. Lamentablement la primera, darrera i única cosa que aquest home va veure, va ser una fatal puntada voladora."};

        String[] gif = {"cn_approves.gif",
                "cn_ball.gif",
                "cn_kittens.gif",
                "cn_punching.gif",
                "cn_super_kick.gif",
                "cn_chin_punch.gif",
                "cn_killed_jfk.gif",
                "cn_grenade.gif",
                "cn_harry_potter.gif",
                "cn_class.gif"};

        for (int i = 0; i < facts.length; i++) {
            if (i < 9) {
                SQL_INSERT_FACTS += "(\"Fact " + rNumber[i] + "\",\"" + facts[i] + "\",\"" + gif[i] + "\"),";
            } else {
                SQL_INSERT_FACTS += "(\"Fact " + rNumber[i] + "\",\"" + facts[i] + "\",\"" + gif[i] + "\");";
            }
        }
    }

    /**
     * Event que es produeix quan s'ha d'actualitzar la BD a una versió superior
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // NOTA: Per simplificar l'exemple, aquí s'utilitza directament
        // l'opció d'eliminar la taula anterior i tornar-la a crear buida
        // amb la nova estructura.
        // No obstant, el més habitual és migrar les dades de la taula antiga
        // a la nova, per la qual cosa aquest mètode hauria de fer més coses.

        // S'elimina la versió anterior de la taula
        //db.execSQL("DROP TABLE IF EXISTS Facts");
        // Es crea la nova versió de la taula
        //db.execSQL(SQL_CREATE_FACTS);
    }
}
