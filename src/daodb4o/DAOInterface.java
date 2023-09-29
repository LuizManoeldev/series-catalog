/**********************************
*	Catalogo
*	Luiz Manoel 
*	Maicon Felipe

 **********************************/

package daodb4o;

import java.util.List;
// Estabelencendo padrões de assinatura (nome dos metodos)
public interface DAOInterface<T> {
	public void create(T obj);
	public T read(Object chave);
	public T update(T obj);
	public void delete(T obj) ;
	public List<T> readAll();
	public void deleteAll();
}