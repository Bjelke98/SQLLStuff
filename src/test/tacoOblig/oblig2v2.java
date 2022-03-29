package test.tacoOblig;
// PRG1100 - Java oblig 2, Medlemsregister 
  //Programmert av Adrian Joachin, 247020

import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import java.util.*;
import java.io.*;
import java.sql.*;

public class oblig2v2 {
  private static String url = "jdbc:sqlite:medlemmer.db";
  public static Statement stmt;
  public static Connection conn;
  private static String logging = "log: ";
  private static int medlemNrInput;
  private static int tlfNr;
  public static String sql;
  public static ResultSet rs;
  public static File fil = new File("medlemmer.db");
  public static File f = new File("registerBackup.txt");
  
  public static void main(String[] args) throws Exception {
    final File fil = new File("medlemmer.db");

    // Sjekker om medlemmer.db eksistereri samme rotmappe. Hvis ikke, opprettes den
    if (!fil.exists()) {

      showMessageDialog(null, "Databasefil ikke funnet, oppretter " + "'" + fil.toString() + "'");
      conn = DriverManager.getConnection(url);
       // Oppretter en tabell Medlem i databasen
      sql = "create table Medlem (medlemNr integer primary key AUTOINCREMENT, fNavn varcharS, eNavn varchar, adresse varchar, tlfNr integer(8))";
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
      out.println(logging + "Databasefil ikke funnet, oppretter");
      
      File innFil = new File("register.txt");
      Scanner leser = new Scanner(innFil);
      leser = new Scanner(innFil);

      while ( leser.hasNextLine()) {
        String split = ";";
        String linje = leser.nextLine();
        String[] dataTab = linje.split(";");
        int medlemNr = parseInt(dataTab[0]);
        String forNavn = dataTab[1];
        String etterNavn = dataTab[2];
        String adresse = dataTab[3];
        
        int tlf;
      // Denne if'en sjekker om ";" finnes 4 ganger i linja. Dersom den ikke gjør det settes tlfnummer til "0". Fant formelen på stackoverflow.
        if (linje.matches(".*(;.*){4}.*")){
          tlf = parseInt(dataTab[4]);
        } else {
          tlf = 0;
        }
      // setter inn verdier i tabellen Medlemmer
        sql = "insert into Medlem values(" + medlemNr + ",'" + forNavn + "','"+ etterNavn + "','" + adresse + "'," + tlf +");";
        stmt.executeUpdate(sql);

      }
      out.println(logging + "Register opprettet");
    }
    
    else {
      showMessageDialog(null, "Databasen" + " '" + fil.toString() + "' " + "er allerede opprettet.");
      out.println(logging + "Tabell eksisterer");
    }
    int valg = 0;
    do {
      out.println(logging + "Åpner meny");
      valg = visMeny();
      if (valg != 0)
        switch (valg) {
          case 1: visAlleEtternavn();  ;break;
          case 2: visAlleTlf();       ;break;
          case 3: registrereMedlem()  ;break;
          case 4: endreMedlem();      ;break;
          case 5: slettMedlem();      ;break;
          case 6: taBackup();         ;break;
          case 7: hentBackup();       ;break;
          case 8: test(); break;
          default: break;
        }
      } while (valg !=0);
    }
    public static void lagNyTabell(File fil) throws SQLException {
      showMessageDialog(null, "Databasefil ikke funnet, oppretter " + "'" + fil.toString() + "'");
      conn = DriverManager.getConnection(url);
      sql = "create table Medlem (medlemNr integer primary key AUTOINCREMENT, fNavn varchar, eNavn varchar, adresse varchar, tlfNr integer(8))";
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
      out.println(logging + "Databasefil ikke funnet, oppretter");
    }
    public static int visMeny() {   
      String meny = "[1] Vis alle etternavn" + "\n" 
      + "[2] Vis alle tlf.nr" + "\n"  
      + "[3] Legg til medlem" + "\n"   
      + "[4] Endre medlem" + "\n"   
      + "[5] Slett medlem" + "\n" 
      + "[6] Ta backup" + "\n"   
      + "[7] Hent backup" + "\n"
      + "[0] Avslutt";    
      try { 
       return parseInt(showInputDialog(meny + "\n" + "Velg et nr. (0-7):"));
     }
     catch (NumberFormatException nfe) {
      feilMeldingNummer();
      visMeny();
      return 0;
    } 
  }
  public static void test() throws SQLException {
    conn = DriverManager.getConnection(url);
    stmt = conn.createStatement();
    sql = "select * from Medlem where odjad";
    rs = stmt.executeQuery(sql);
  }
  public static void feilMeldingNummer() {
    showMessageDialog(null, "Feil input");
  }
  public static void visAlleEtternavn() throws SQLException {
    showMessageDialog(null, "1: Alle medlemmer, sortert på etternavn");
    conn = DriverManager.getConnection(url);
    stmt = conn.createStatement();
    String spørringmelding = "";
    sql = "select * FROM Medlem ORDER BY eNavn";
    rs = stmt.executeQuery(sql);

    while ( rs.next()) {
      int medlemNr = rs.getInt("medlemNr");
      String fornavn = rs.getString("fNavn");
      String etternavn = rs.getString("eNavn");
      spørringmelding += etternavn + " " + fornavn + " - " + medlemNr+ "\n";
    }
    out.println(logging + "Viser alle medlemmer sortert på etternavn");
    showMessageDialog(null, spørringmelding);
  }
  public static void visAlleTlf() throws SQLException { 
    showMessageDialog(null, "2: Alle medlemmer med tlf, sortert på tlf.nr");
      //Kobler til databasefil
    conn = DriverManager.getConnection(url);
    stmt = conn.createStatement();
    String spørringmelding = "";
      //Spør database og sorterer utifra tlfnr
    sql = "select * FROM Medlem ORDER BY tlfNr";
    rs = stmt.executeQuery(sql);

    while ( rs.next()) {
      int medlemNr = rs.getInt("medlemNr");
      String fornavn = rs.getString("fNavn");
      String etternavn = rs.getString("eNavn");
      int tlfNr = rs.getInt("tlfNr");
      spørringmelding += tlfNr + " - " + fornavn + " - " + etternavn + " - " + medlemNr + "\n";
    }
    showMessageDialog(null, spørringmelding);
  }  
  // Husk å skrive logg-meldinger i konsollet for endringsmetoder!
  public static void registrereMedlem() throws SQLException {
    try {
      showMessageDialog(null, "3: registrere nytt medlem");
      conn = DriverManager.getConnection(url);
      stmt = conn.createStatement();

      String fNavn = showInputDialog("Skriv inn fornavn");
      String eNavn = showInputDialog("Skriv inn etternavn");
      String adresse = showInputDialog("Skriv inn adresse");
      int tlfNr = parseInt(showInputDialog("Skriv inn tlfnr"));
      sql = "insert into Medlem (fNavn, eNavn, adresse, tlfNr) values (" + "'" + fNavn + "','"+ eNavn + "','" + adresse + "'," + tlfNr +");";
      stmt.executeUpdate(sql);
      showMessageDialog(null, fNavn + " " + eNavn + " ble registrert som medlem");
    }
    catch (NumberFormatException nfe) {
      feilMeldingNummer();
    }   
  }  
  public static void endreMedlem() throws SQLException {
    try {
      showMessageDialog(null, "4: Endre eller legge til tlf.nr");
      conn = DriverManager.getConnection(url);
      stmt = conn.createStatement();

      int medlemNrInput = parseInt(showInputDialog("Angi medlemNr"));
      int tlfNr = parseInt(showInputDialog("Angi nytt tlfnr"));
      sql = "update Medlem set tlfNr = " + tlfNr + " WHERE medlemNr = " + medlemNrInput;
      stmt.executeUpdate(sql);
    }
    catch (NumberFormatException nfe) {
      feilMeldingNummer();
    }   
  }  
  public static void slettMedlem() throws SQLException {
    try { 
      showMessageDialog(null, "5: Slette medlem");
      conn = DriverManager.getConnection(url);
      stmt = conn.createStatement();
      int medlemNrInput = parseInt(showInputDialog("Angi medlemnummer som skal slettes"));

      if (showConfirmDialog(null, "Er du sikker på at du vil slette medlemnr " + medlemNrInput + "?", "Bekreftelse til sletting",
        YES_NO_OPTION) == YES_OPTION) {
        sql = "delete from Medlem where medlemNr = " + medlemNrInput +";";
      stmt.executeUpdate(sql);
      showMessageDialog(null, "Medlemnr: " + medlemNrInput + " ble slettet");
    } 
    else {
      showMessageDialog(null, "Medlemnr: " + medlemNrInput + " ble ikke slettet");
    }
  }
  catch (NumberFormatException nfe) {
    feilMeldingNummer();
  }   
}
public static void taBackup() throws SQLException, FileNotFoundException { 
  showMessageDialog(null, "6: Ta backup");

  if (!f.exists()) {
    PrintWriter skriver = new PrintWriter("registerBackup.txt");
    sql = "select * from Medlem";
    rs = stmt.executeQuery(sql);
    while (rs.next()) {
      int medlemNr = rs.getInt("medlemNr");
      String fNavn = rs.getString("fNavn");
      String eNavn = rs.getString("eNavn");
      String adresse = rs.getString("adresse");
      int tlfNr = rs.getInt("tlfNr");
      skriver.println(medlemNr +";"+ fNavn + ";" + eNavn + ";" + adresse + ";" + tlfNr);
    }
    skriver.close();
    showMessageDialog(null, "Backup opprettet");
  }
  else {
    showMessageDialog(null, "registerBackup.txt eksisterer allerede. Ingen endringer ble gjort");
  }
}
public static void hentBackup() throws Exception {
  showMessageDialog(null, "7: Hent backup");
  if (showConfirmDialog(null, "Dette vil slette all data i eksisterende database. Er du sikker på at du vil importere fra backup?", "Bekreftelse til sletting",
    YES_NO_OPTION) == YES_OPTION) {
    try {
      if (!f.exists()) {
        showMessageDialog(null, "Backupfil ikke funnet");
      }
      else {
        conn = DriverManager.getConnection(url);
        stmt = conn.createStatement();
        sql = "drop table Medlem";
        stmt.executeUpdate(sql);
        lagNyTabell(fil);
        File innFil = new File("registerBackup.txt");
        Scanner leser = new Scanner(innFil);
        leser = new Scanner(innFil);
        while ( leser.hasNextLine()) {
          String split = ";";
          String linje = leser.nextLine();
          String[] dataTab = linje.split(";");
          int medlemNr = parseInt(dataTab[0]);
          String forNavn = dataTab[1];
          String etterNavn = dataTab[2];
          String adresse = dataTab[3];
          int tlf = parseInt(dataTab[4]);
          sql = "insert into Medlem values(" + medlemNr + ",'" + forNavn + "','"+ etterNavn + "','" + adresse + "'," + tlf +");";
          stmt.executeUpdate(sql);
        }
        showMessageDialog(null, "Backup hentet fra registerBackup.txt");
      }
    }
    catch (FileNotFoundException fnfe) {
      showMessageDialog(null,"registerBackup.txt ble ikke funnet, ingen endringer er gjort");
    }
  }
  else {
    showMessageDialog(null, "Bekreftet med nei, åpner meny.");
  }
}
}