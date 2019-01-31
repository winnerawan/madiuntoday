package net.winnerawan.madiun.data.network.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by adriyo on 11/17/17.
 * adriyo.github.io
 */

public class Categories  implements Serializable {

    private List<Category> categories;

    public Categories() {
    }

    public Categories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categories that = (Categories) o;

        return categories != null ? categories.equals(that.categories) : that.categories == null;
    }

    @Override
    public int hashCode() {
        return categories != null ? categories.hashCode() : 0;
    }
}
