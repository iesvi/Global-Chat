package com.iesvi.shared.domain;

public class Identifier<T> {
    final T value;

    public Identifier(T value) {
        this.value = value;
    }

    protected Identifier() {
        this.value=null;
    }

    public T value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identifier)) return false;

        Identifier<?> that = (Identifier<?>) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
