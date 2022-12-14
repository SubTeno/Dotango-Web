package com.subteno.dotango.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query.Direction;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

@Controller
public class DotangoController {
    private static final String COLLECTION_HERO = "heroes";
    private static final String COLLECTION_ABILITIES = "abilities";
    private CollectionReference collection_hero = FirestoreClient.getFirestore().collection(COLLECTION_HERO);
    private CollectionReference collection_abilities = FirestoreClient.getFirestore().collection(COLLECTION_ABILITIES);

    @GetMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String frontcontent(Model model) throws InterruptedException, ExecutionException {
        List<QueryDocumentSnapshot> res = collection_hero.orderBy("id", Direction.ASCENDING)
                .get()
                .get()
                .getDocuments();
        model.addAttribute("res", res);
        return "listhero";
    }

    @SuppressWarnings("unchecked")
    @GetMapping(path = "/herodetail", produces = MediaType.TEXT_HTML_VALUE)
    public String herodetail(@ModelAttribute("id") String id, Model model)
            throws InterruptedException, ExecutionException, JsonSyntaxException, JsonIOException,
            FileNotFoundException {
        DocumentSnapshot hero = collection_hero.document(id).get().get();
        List<String> abilityListS = (List<String>) hero.get("abilities");
        List<DocumentSnapshot> abilityList = new ArrayList<DocumentSnapshot>();
        abilityListS.forEach(object -> {
            try {
                abilityList.add(collection_abilities.document(object).get().get());
            } catch (InterruptedException | ExecutionException e) {
            }
        });
        model.addAttribute("abilities", abilityList);
        model.addAttribute("hero", hero);
        return "herodetail";
    }

   
   
}
