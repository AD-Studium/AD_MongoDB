package es.studium.Mongo;
/**
 * 
 */
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
/**
 * @author Alvca
 *
 */
public class TestMongo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Creamos un objeto de tipo MongoClient, con el que establecemos la conexion a la base de datos
		MongoClient conexion = MongoClients.create("mongodb://localhost:27017");
		//Creamos la base de datos
		MongoDatabase database = conexion.getDatabase("ejemplo_mongoDB");
		//
		MongoCollection<Document> alumnos = database.getCollection("studium_coleccion");
		
		MongoCRUD.insertarDocumentos(alumnos);
		MongoCRUD.consultarDocumentos(alumnos);
		MongoCRUD.actualizarDocumentos(alumnos);
		MongoCRUD.eliminarDocumentos(alumnos);
	}
}
