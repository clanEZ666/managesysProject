package repositories;

import java.util.Objects;

abstract class AbstractRepository {
    abstract void add(Objects o);
    abstract Objects get();
    abstract boolean load(Objects o);
    abstract boolean save(Objects o);
}
