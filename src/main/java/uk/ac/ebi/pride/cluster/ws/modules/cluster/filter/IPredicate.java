package uk.ac.ebi.pride.cluster.ws.modules.cluster.filter;

/**
 * Determine a true or false value for a given input
 *
 * @author Rui Wang
 * @version $Id$
 */
public interface IPredicate<T> {

    boolean apply(T o);
}
