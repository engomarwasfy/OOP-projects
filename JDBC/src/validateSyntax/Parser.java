package validateSyntax;

public class Parser extends Validator {
  private final Validator create;
  private final Validator delete;
  private final Validator drop;
  private final Validator insert;
  private final Validator select;
  private final Validator update;
  private final Validator use;
  private final Validator alter;
  private final DistinctValidator distinct;

  public Parser() {
    create = new CreateValidator();
    delete = new DeleteValidator();
    drop = new DropValidator();
    insert = new InsertValidator();
    select = new SelectValidator();
    update = new UpdateValidator();
    use = new UseValidator();
    alter = new AlterValidator();
    distinct = new DistinctValidator();
  }

  @Override
  public boolean validate(final String[] sql) {
    if (sql[0].equalsIgnoreCase("select")) {
      if (sql[1].equalsIgnoreCase("distinct")) {
        return distinct.validate(sql);
      }
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
    } else if (sql[0].equalsIgnoreCase("ALTER")) {
      return alter.validate(sql);
    } else {
      return false;
    }

  }

}
