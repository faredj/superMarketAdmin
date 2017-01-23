package donnees;

import java.util.List;
/**
 * 
 * @author faredj
 * Interface DAO (Objet d'accès aux données)
 */
public interface DaoTemplate <R, T>{
/**
 * 
 * @param t objet à insérer
 */
	public R insert(T t);
/**
 * 
 * @param t objet à modifier
 */
	public R update(T t);
/**
 * 
 * @param t objet à supprimer
 */
	public R delete(T t);
/**
 * 
 * @return liste d'objet t
 */
	public List<T> findAll();
	
}
