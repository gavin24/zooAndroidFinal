package com.ackerman.j.gavin.zootrack;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ackerman.j.gavin.zootrack.Domain.Animal;
import com.ackerman.j.gavin.zootrack.Repository.Rest.RestAnimalApi;
import com.ackerman.j.gavin.zootrack.services.AnimalService;
import com.ackerman.j.gavin.zootrack.services.Impl.AnimalServiceImpl;

public class AddScreen extends AppCompatActivity {
   // private AnimalServiceImpl activateService;
    EditText name,country,age,species;
    Intent t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen);

        name = (EditText)findViewById(R.id.name);
        species = (EditText)findViewById(R.id.species);
        age = (EditText)findViewById(R.id.age);
       // country = (EditText)findViewById(R.id.country);

    }

    public void onClick(View v) {
        RestAnimalApi activateService = new RestAnimalApi();
        String nameText = name.getText().toString();
        int ageText = Integer.parseInt(age.getText().toString());
        String speciesText = species.getText().toString();
       // String countryText = country.getText().toString();


        Animal animal = new Animal.Builder()
                .age(ageText)
                .country("England")
                .name(nameText)
                .species(speciesText)
                .build();

        activateService.post(animal);

        Intent intentLogin = new Intent(this, MenuActivity.class);
  /*      intentLogin.putExtra("animalName",animal.getName().toString());
        intentLogin.putExtra("animalAge",animal.getAge());
        intentLogin.putExtra("animalSpecies",animal.getSpecies().toString());*/
        startActivity(intentLogin);
        finish();
    }



}
