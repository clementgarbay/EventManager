package fr.eventmanager.utils.persistence;

import fr.eventmanager.utils.type.Tuple;

import javax.persistence.criteria.CommonAbstractCriteria;
import javax.persistence.criteria.Root;

/**
 * @author Clément Garbay
 */
public class BaseQuery<T> extends Tuple<Root<T>, CommonAbstractCriteria> {

    public BaseQuery(Root<T> entity, CommonAbstractCriteria abstractCriteria) {
        super(entity, abstractCriteria);
    }

    public Root<T> getEntity() {
        return super.getValue1();
    }

    public CommonAbstractCriteria getAbstractCriteria() {
        return super.getValue2();
    }
}
