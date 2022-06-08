package dbms.views.model;

public abstract class DatabaseTable {

    protected final Database db;
    protected DatabaseTable(String dbname){
        this.db = Database.getDatabase(dbname);
        createTable();
    }
    protected abstract void createTable();

}
