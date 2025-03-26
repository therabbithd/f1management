package CRUD;



import java.sql.SQLException;
import java.util.ArrayList;

public interface CRUDSERVICE<T> {
    /** requestAll
     * Devuelve todos los registros de 
     * base de datos
     * @return
     * @throws SQLException
     */
    public ArrayList<T> requestAll() throws SQLException;

    /** requestById
     * Obtiene un registro del modelo con la clave primaria
     * @param id
     * @return
     * @throws SQLException
     */
    public T requestById(long id) throws SQLException;

    /** Create
     * Crear un registro en la base de datos 
     * @param model
     * @return
     * @throws SQLException
     */
    public void create(T model) throws SQLException;

    /** update
     * Actualiza la información de un registro de la base de datos
     * para el modelo en cuestión
     * @param object
     * @return el número de registros afectados
     * @throws SQLException
     */
    public int update(T object) throws SQLException;

    /** delete
     * Elimina un registro del modelo dada su clave primaria
     * @param id
     * @return
     * @throws SQLException
     */
    public boolean delete(long id) throws SQLException;
}

