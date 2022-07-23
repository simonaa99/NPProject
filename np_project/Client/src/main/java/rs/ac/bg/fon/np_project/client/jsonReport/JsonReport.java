package rs.ac.bg.fon.np_project.client.jsonReport;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import rs.ac.bg.fon.np_project.commonlibrary.model.User;

public class JsonReport {

	public static void generateReport(User user){
       JsonObject jsonReport = new JsonObject();
       JsonObject korisnik = new JsonObject();
       JsonObject kategorija = new JsonObject();
       
       korisnik.addProperty("Ime", user.getName());
       korisnik.addProperty("Prezime", user.getLastName());
       korisnik.addProperty("Adresa", user.getAddress());
       korisnik.addProperty("Broj telefona", user.getPhoneNumber());
       kategorija.addProperty("Kategorija", user.getUserCategory().getName());
       jsonReport.addProperty("Broj clanske kartice", user.getUsercard().getCardNumber());
       jsonReport.addProperty("Kartica istice", user.getUsercard().getExpiryDate().getMonthValue()+"/"+user.getUsercard().getExpiryDate().getYear());

       korisnik.add("Kategorija", kategorija);
       jsonReport.add("Korisnik", korisnik);
       
       
        writeToAFile(jsonReport);
	}


    private static void writeToAFile(JsonObject jsonReport) {
        try (FileWriter out = new FileWriter("clanskaKarticaKorisnika.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            out.write(gson.toJson(jsonReport));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
