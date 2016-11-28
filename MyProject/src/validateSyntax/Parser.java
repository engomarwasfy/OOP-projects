package validateSyntax;

public class Parser extends Validator {
    private Validator create;
    private Validator delete;
    private Validator drop;
    private Validator insert;
    private Validator select;
    private Validator update;
    private Validator use;

    public Parser() {
	create = new CreateValidator();
	delete = new DeleteValidator();
	drop = new dropValidator();
	insert = new InsertValidator();
	select = new SelectValidator();
	update = new UpdateValidator();
	use = new UseValidator();
    }

    @Override
    public boolean validate(String[] sql) {
	if (sql[0].equalsIgnoreCase("select")) {
	    return select.validate(sql);
	} else if (sql[0].equalsIgnoreCase("create")) {
	    return create.validate(sql);
	} else if (sql[0].equalsIgnoreCase("insert")) {
	    return insert.validate(sql);
	} else if (sql[0].equalsIgnoreCase("delete")) {
	    return delete.validate(sql);
	} else if (sql[0].equalsIgnoreCase("drop")) {
	    return drop.validate(sql);
	} else if (sql[0].equalsIgnoreCase("update")) {
	    return update.validate(sql);
	} else if (sql[0].equalsIgnoreCase("use")) {
	    return use.validate(sql);
	} else {
	    return false;
	}

    }

}
