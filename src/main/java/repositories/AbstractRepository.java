package repositories;

import java.util.Objects;

abstract class AbstractRepository {
    abstract void add(Object o);
    abstract Object load();
    abstract boolean save();
}
