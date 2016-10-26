package fr.eventmanager.utils.persistence;

import fr.eventmanager.utils.type.Tuple;

import javax.persistence.criteria.CommonAbstractCriteria;
import javax.persistence.criteria.Root;

/**
 * @author Clément Garbay
 */
public class BaseQuery<T, C extends CommonAbstractCriteria> extends Tuple<Root<T>,C> {

    public BaseQuery(Root<T> entity, C abstractCriteria) {
        super(entity, abstractCriteria);
    }

    public Root<T> getEntity() {
        return super.getValue1();
    }

    public C getAbstractCriteria() {
        return super.getValue2();
    }
}
