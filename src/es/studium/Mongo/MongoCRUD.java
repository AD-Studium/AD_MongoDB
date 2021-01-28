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

import java.util.Arrays;
import java.util.List;

import org.bson.Document;

/**
 * @author Alvca
 *
 */
public class MongoCRUD {
	/* Función para insertar documentos en la base de datos */
	public static void insertarDocumentos(MongoCollection<Document> alumnos)
	{
		/*
		 * Creamos un documento mediante new, al que llamamos documento con append()
		 * añadimos cada propiedad al documento creado.
		 */
		Document documento = new Document("nombre", "Manolo").append("apellido", "García").append("edad", 28);
		Document documento1 = new Document();
		/* Añadimos un único documento */
		alumnos.insertOne(documento);
		/* Insertamos varios documentos creados parseando strings */
		alumnos.insertMany(Arrays.asList(Document.parse("{ nombre: 'Ana', edad: 25, pagado: true, nota: 7 }"),
				Document.parse("{ nombre: 'Benito', apellido: 'Benítez', edad: 50, nota: 9.8 }"),
				Document.parse("{ nombre: 'Carmen', edad: 31, pagado: false, nota: 3 }")));
		/* Creamos un nuevo documento, al que llamamos documento2 */
		Document documento2 = new Document("nombre", "Pedro").append("apellido", "López").append("edad", 44);
		/* Añadimos un único documento */
		alumnos.insertOne(documento2);
		alumnos.insertOne(Document.parse("{ nombre: 'Benito', apellido: 'Benítez', edad: 50, nota: 9.8 }"));
		/*Insertamos varios documentos creados guardando los documentos en una Lista*/
		List<Document> arg0 = Arrays.asList(Document.parse("{ nombre: 'Ana', edad: 25, pagado: true, nota: 7 }"),
				Document.parse("{ nombre: 'Benito', apellido: 'Benítez', edad: 50, nota: 9.8 }"),
				Document.parse("{ nombre: 'Carmen', edad: 31, pagado: false, nota: 3 }"));
		alumnos.insertMany(arg0);
	}
	public static void consultarDocumentos(MongoCollection<Document> alumnos)
	{
		/* Buscar todos los alumnos */
		FindIterable<Document> buscaTodos = alumnos.find();
		/* Buscar todos los alumnos llamados (igual que) Ana */
		FindIterable<Document> buscaAna = alumnos.find(eq("nombre", "Ana"));
		/* Buscar todos los alumnos que han aprobado */
		FindIterable<Document> busquedaAprobados = alumnos.find(gte("nota", 5));
		/* Busca todos los alumnos llamados Ana que han aprobado */
		FindIterable<Document> buscaAnaAprobados = alumnos.find(and(eq("nombre", "Ana"), gte("nota", 5)));
		/*
		 * Recorremos todos los resultados y los mostramos Según el objeto colección
		 * FindIterable<Document> que ponga, será el que recorra y por tanto seran esos
		 * elementos los que nos mostrará
		 */
		System.out.println("Mostramos todos los alumnos: ");
		for (Document alumnoBuscado : buscaTodos) {
			System.out.println("\t" + alumnoBuscado.toJson());
		}
		System.out.println("Mostramos a la alumna de nombre Ana: ");
		for (Document alumnoBuscado : buscaAna) {
			System.out.println("\t" + alumnoBuscado.toJson());
		}
		System.out.println("Mostramos los alumnos que han aprobado: ");
		for (Document alumnoBuscado : busquedaAprobados) {
			System.out.println("\t" + alumnoBuscado.toJson());
		}
		System.out.println("Mostramos los aprobados de Ana: ");
		for (Document alumnoBuscado : buscaAnaAprobados) {
			System.out.println("\t" + alumnoBuscado.toJson());
		}
	}
	public static void actualizarDocumentos(MongoCollection<Document> alumnos)
	{
		/* Actualizamos el primer documento que cumpla estas condiciones */
		alumnos.updateOne(eq("nombre", "Ana"), combine(set("apellidos", "Andrea"), set("nota", 8)));
		alumnos.updateOne(eq("nombre", "Benito"), combine(set("nota", 3)));
		/*Actualizamos todos los documentos que cumplan esta condición*/
		alumnos.updateMany(eq("nombre", "Ana"), combine(set("apellidos", "Andrea"), set("nota", 4)));
	}
	public static void eliminarDocumentos(MongoCollection<Document> alumnos) {
		/*Eliminamos el primer documento que cumpla esta condición.*/
		alumnos.deleteOne(eq("nombre", "Ana"));
		/*Eliminamos todos los documentos que cumplan esta condición*/
		alumnos.deleteMany(eq("nombre", "Ana"));
	}
}
