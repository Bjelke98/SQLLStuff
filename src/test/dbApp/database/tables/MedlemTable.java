package test.dbApp.database.tables;

public class MedlemTable extends Table{
    private static final String TABLE_NAME = "Medlem";
    public MedlemTable(String connectionUrl) {
        super(TABLE_NAME, connectionUrl);
        primaryKey = "epost";
        createTable();
    }

    @Override
    protected void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS Medlem (
                    epost TEXT PRIMARY KEY,
                    navn TEXT,
                    adresse TEXT,
                    tlf INTEGER
                );
                """;
        createTable(sql);
    }
}
