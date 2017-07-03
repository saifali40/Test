package com.controller.Backend;

import java.util.Arrays;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;


@Controller
@RequestMapping("")
public class BasicPageController {


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView loginPage(){
		demo();
		return new ModelAndView("index");	
	}
	
	public void demo(){
		try{
			System.out.println("comes here");
	         // To connect to mongodb server
			
			
			MongoClientURI uri = new MongoClientURI("mongodb://dbAdmin:saifali@45.32.100.99:27017/?authSource=demo");
			MongoClient mongoClient = new MongoClient(uri);
	         
	         MongoDatabase db = mongoClient.getDatabase("demo");
	         final MongoCollection<Document> collection = db.getCollection("MyTest");
//	         Document document = new Document("name", "Café Con Leche1")
//	                 .append("contact", new Document("phone", "228-555-01491")
//	                                         .append("email", "cafeconleche@example.com1")
//	                                         .append("location",Arrays.asList(-73.92502, 40.8279556)))
//	                 .append("stars", 4)
//	                 .append("categories", Arrays.asList("Bakery1", "Coffee1", "Pastries1"));
//
//	         collection.insertOne(document);	         
//
//	         BasicDBObject newDocument = new BasicDBObject();
//	         newDocument.append("$set", new BasicDBObject().append("phone", "228-555-01491"));
//
//	         BasicDBObject searchQuery = new BasicDBObject().append("phone", "12312312");
//	         collection.updateOne(searchQuery, newDocument);

	         Block<Document> printBlock = new Block<Document>() {
	             @Override
	             public void apply(final Document document) {
	            	 
	                 System.out.println(document.toJson());
	             }
	      }; 
	     collection.find().forEach(printBlock);
	     
	     Document user = null;
//	     Bson filterByUsername = Filters.eq("name", "Café Con Leche1");
//	     user = collection.find(filterByUsername).first();
//	     System.out.println(user.toJson());
	     
	     
	     Bson filterByUsername = Filters.eq("name", "Café Con Leche");
	     
	     
	     user = collection.find(filterByUsername).first();
	     
		collection.findOneAndUpdate(filterByUsername, new Document("$set", new Document("Name", "100Lights")));
		
	      }catch(Exception e){
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      }
		
	}

	
	
	
}
