package repositories;

abstract class AbstractRepository {
    abstract public void add(Object o);
    abstract public Object load(String path);
    abstract public boolean save(String path);
}
