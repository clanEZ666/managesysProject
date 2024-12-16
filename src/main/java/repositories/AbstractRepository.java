package repositories;

abstract class AbstractRepository {
    abstract void add(Object o);
    abstract Object load(String path);
    abstract boolean save(String path);
}
