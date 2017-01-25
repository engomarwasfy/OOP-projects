package Controller;

public interface ObservableIF {

  public void add(ObserverIF observer);
  public void notify(Object o);
}
