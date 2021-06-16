package me.B9038462.ExamHelper.ExamHelperApp.Database.DatabaseBuilder;

public class Table {
    protected String tableName = "";
    private String columns = "";
    private String primaryKey = "";
    private String tableCreation = "";


    public Table(String tableName) {
        this.tableName = tableName;
    }

    public Table setPrimaryKey(String primaryKey) {
        this.primaryKey = "(`" + primaryKey + "`)";
        return this;
    }

    //CONSTRAINT fk_departments
    //    FOREIGN KEY (department_id)
    //    REFERENCES departments(department_id)
    public Table addForeignKey(String table, String tableCol) {
        this.columns = this.columns + " FOREIGN KEY (`" + tableCol + "`)" + " REFERENCES " + table + " (`" + tableCol + "`),";
        return this;
    }
    public Table addColumn(String name, String dataInfo) {
        //System.out.print("Adding the column " + name + " [" + dataInfo + "]");
        if (this.columns.equals("")) {
            this.columns = "`" + name + "` " + dataInfo + ",";
        } else {
            this.columns = this.columns + "`" + name + "` " + dataInfo + ",";
        }
        return this;
    }

    public Database build(Database database) {
        this.tableCreation = "CREATE TABLE IF NOT EXISTS " + this.tableName + " (" + this.columns + "PRIMARY KEY "
                + this.primaryKey + ");";

        database.loadTable(this);
        return database;
    }

    public String getTableName() {
        return this.tableName;
    }
    public String getColumns() {
        return this.columns;
    }
    public String getPrimaryKey() {
        return this.primaryKey;
    }
    public String getTableCreation() {
        return this.tableCreation;
    }

    @Override
    public String toString() {
        return "Table{" +
                ", tableName='" + tableName + '\'' +
                ", columns='" + columns + '\'' +
                ", primaryKey='" + primaryKey + '\'' +
                ", tableCreation='" + tableCreation + '\'' +
                '}';
    }
}
