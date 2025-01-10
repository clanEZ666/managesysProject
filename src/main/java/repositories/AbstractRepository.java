package repositories;



public abstract class AbstractRepository {
    abstract public void add(Object o);
    abstract public Object load(Object o);
    abstract public boolean save(Object o);


}

