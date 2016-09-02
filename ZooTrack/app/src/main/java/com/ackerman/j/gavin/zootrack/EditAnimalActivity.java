package com.ackerman.j.gavin.zootrack;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ackerman.j.gavin.zootrack.Domain.Animal;
import com.ackerman.j.gavin.zootrack.Repository.Rest.RestAnimalApi;
import com.ackerman.j.gavin.zootrack.services.AnimalService;
import com.ackerman.j.gavin.zootrack.services.Impl.AnimalServiceImpl;

/**
 * Created by gavin.ackerman on 2016-08-28.
 */
public class EditAnimalActivity extends AppCompatActivity {
   // private AnimalServiceImpl activateService;

    private boolean isBound = false;
    private AnimalService activateAccountService;
    EditText name,country,age,species;
    Intent t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_animal_activity);
        Intent intent = new Intent(this, AnimalServiceImpl.class);


        name = (EditText)findViewById(R.id.name);
        species = (EditText)findViewById(R.id.species);
        age = (EditText)findViewById(R.id.age);
        //country = (TextView)findViewById(R.id.country);

        String animalName = getIntent().getStringExtra("animalName");
       // String animalCountry = getIntent().getStringExtra("animalCountry");
        String animalSpecies = getIntent().getStringExtra("animalSpecies");
        int animalAge = getIntent().getIntExtra("animalAge",0);

        name.setText(animalName);
        species.setText(animalSpecies);
        age.setText(Integer.toString(animalAge));
       // country.setText(animalCountry);

    }

    public void onClick(View v) {
        RestAnimalApi activateService = new RestAnimalApi();
        String nameText = name.getText().toString();
        int ageText = Integer.parseInt(age.getText().toString());
        String speciesText = species.getText().toString();
       // String countryText = country.getText().toString();
        String countryText = "england";
        long aniId = getIntent().getLongExtra("animalId",0);
        Animal animal = new Animal.Builder()
                .id(aniId)
                .age(ageText)
                .country("england")
                .name(nameText)
                .species(speciesText)
                .build();


        activateService.put(animal);

        Intent intentLogin = new Intent(this, ViewActivity.class);
        startActivity(intentLogin);
        finish();
    }
}
