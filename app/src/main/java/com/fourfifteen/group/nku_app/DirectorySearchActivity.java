package com.fourfifteen.group.nku_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.LinkedList;

public class DirectorySearchActivity extends AppCompatActivity {

    public LinkedList<Student> directory = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_search);
        Button button = findViewById(R.id.searchButton);

        createDirectory();

        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(DirectorySearchActivity.this,
                        DirectoryActivity.class);

                ArrayList<String> searchResults = getSearchResults();

                Bundle bundle = new Bundle();
                bundle.putStringArrayList("filteredList", searchResults);
                //bundle.putStringArray("filteredList", searchResults);
                myIntent.putExtras(bundle);

                startActivity(myIntent);
            }
        });

    }

    private void createDirectory() {
        Student Evan = new Student(), James = new Student(), Jake = new Student(), Garrett = new Student(), Logan = new Student(), Joseph = new Student();
        Evan.setStudent("Evan", "Berkemeyer", "berkemeyee1@nku.edu");
        James.setStudent("James", "Kennedy", "kennedyj16@nku.edu");
        Jake.setStudent("Jake", "Frommeyer", "frommeyerj4@nku.edu");
        Garrett.setStudent("Garrett", "Foister", "foisterg1@nku.edu");
        Logan.setStudent("Logan", "Vanway", "wanwayl1@nku.edu");
        Joseph.setStudent("Joseph", "Wernke", "wernkej11@nku.edu");

        directory.add(Evan);
        directory.add(James);
        directory.add(Jake);
        directory.add(Garrett);
        directory.add(Logan);
        directory.add(Joseph);
    }

    private ArrayList<String> getSearchResults() {
        ArrayList<String> searchResults = new ArrayList<>();

        String fName = ((EditText) findViewById(R.id.searchFirstName)).getText().toString();
        String lName = ((EditText) findViewById(R.id.searchLastName)).getText().toString();

        if (fName.length() > 0 && lName.length() == 0) {
            for (int i = 0; i < directory.size(); i++) {
                if (directory.get(i).firstName.toLowerCase().contains(fName.toLowerCase())) {
                    searchResults.add(directory.get(i).firstName + " " + directory.get(i).lastName);
                }
            }
        } else if (fName.length() == 0 && lName.length() > 0) {
            //put code for only last name search here
            for (int i = 0; i < directory.size(); i++) {
                if (directory.get(i).lastName.toLowerCase().contains(lName.toLowerCase())) {
                    searchResults.add(directory.get(i).firstName + " " + directory.get(i).lastName);
                }
            }
        } else if (fName.length() > 0 && lName.length() > 0) {
            //put code for first and last name search here
            for (int i = 0; i < directory.size(); i++) {
                if (directory.get(i).firstName.toLowerCase().contains(fName.toLowerCase()) && directory.get(i).lastName.toLowerCase().contains(lName.toLowerCase())) {
                    searchResults.add(directory.get(i).firstName + " " + directory.get(i).lastName);
                }
            }
        }

        return searchResults;
    }


}
