package com.intelligentz.skoolvan.view;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.intelligentz.skoolvan.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

public class SearchActivity extends AppCompatActivity {
    SearchableSpinner from_spinner;
    SearchableSpinner school_spinner;
    TextView advanced_btn;
    ImageView advanced_icon;
    View.OnClickListener advanced_listener;
    boolean advanced_visibility = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setBackgroundResource(0);
                    toolbar.setLogo(R.drawable.logo_color);
                    isShow = true;
                } else if(isShow) {
                    toolbar.setLogo(null);
                    collapsingToolbarLayout.setBackgroundResource(R.drawable.main);
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
        from_spinner = (SearchableSpinner) findViewById(R.id.input_from);
        from_spinner.setTitle("From");
        school_spinner = (SearchableSpinner) findViewById(R.id.input_school);
        school_spinner.setTitle("School");
        String[] froms = {"Ahangama",
        "Ahungalla",
        "Aluthgama",
        "Ambalangoda",
        "Ampara",
        "Anuradhapura",
        "Armour Street",
        "Arugam Bay",
        "Badulla",
        "Balapitiya",
        "Bambalapitiya",
        "Bandarawela",
        "Batticaloa",
        "Bentota",
        "Beruwala",
        "Borella",
        "Colombo Fort",
        "Colombo",
        "Dambulla",
        "Dehiwala",
        "Dematagoda",
        "Dikwella",
        "Ella",
        "Galle",
        "Gampaha",
        "Grandpass",
        "Habaraduwa",
        "Habarana",
        "Hambantota",
        "Havelock Town",
        "Hikkaduwa",
        "Jaffna",
        "Kalkudah",
        "Kalutara",
        "Kandy",
        "Katunayake",
        "Kegalle",
        "Kilinochchi",
        "Kirinda",
        "Kirulapane",
        "Kochchikade",
        "Kollupitiya",
        "Kolonnawa",
        "Kosgoda",
        "Kotahena",
        "Kurunegala",
        "Mannar",
        "Maradana",
        "Marawila",
        "Matale",
        "Matara",
        "Mattakuliya",
        "Mirissa",
        "Moneragala",
        "Mount Lavinia",
        "Mullaitivu",
        "Negombo",
        "Negombo",
        "Nilaveli",
        "Nuwara Eliya",
        "Pamankada",
        "Panadura",
        "Panchikawatte",
        "Pasicuda",
        "Pettah",
        "Polonnaruwa",
        "Puttalam",
        "Ratmalana",
        "Ratnapura",
        "Sigiriya",
        "Slave lsland",
        "Talpe",
        "Tangalle",
        "Tissamaharama",
        "Trincomalee",
        "Trincomalee",
        "Unawatuna",
        "Vavuniya",
        "Wadduwa",
        "Weligama",
        "Wellawatta",
        "Wellawaya",
        "Yala"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, froms);
        from_spinner.setAdapter(adapter);

        String[] schools = {"Alexandra College",
        "AA-IIT Campus - Ruzlie",
        "AL Nasser college, Colombo",
        "Ananda College, Colombo",
        "Ananda balika Vidyalaya, Colombo",
        "Boys' Model School, Malabe",
                "Anula Vidyalaya, Nugegoda",
        "Asoka Vidyalaya, Colombo",
        "Bomiriya National School",
        "Buddhist Ladies College",
        "C. W. W. Kannangara College",
        "Colombo Hindu College",
        "Defence Services School, Colombo",
        "D. S. Senanayake College, Colombo",
        "Devi Balika Vidyalaya, Colombo",
        "Dharmapala Vidyalaya, Pannipitiya",
        "Gothami Balika Vidyalaya, colombo",
        "Hameed Al Husseinie College, Colombo",
        "Hewavitharana Maha Vidyalaya, Colombo",
        "Isipathana College, Colombo",
        "Lumbini College, Colombo",
        "Mahanama College, Colombo",
        "Mahamaya Balika Maha Vidyalaya",
        "Musaeus College",
        "Muslims Ladies College, Colombo 04",
        "Mutwal Hindu College",
        "Nalanda College, Colombo",
        "Piliyandala Central College",
        "President's College, Sri Jayawardenapura Kotte",
        "Presbyterian Girls' National School, Dehiwala",
        "Prince of Wales' College, Moratuwa",
        "Princess of Wales' College, Moratuwa",
        "Ramanathan Hindu Ladies College",
        "Royal College, Colombo",
        "Rathnavali Balika Vidyalaya, Gampaha",
        "Rathnawali Balika Vidyalaya, Borella",
        "Samudradevi Balika Vidyalaya Nugegoda",
        "Seethawaka National School, Avissawella",
        "Sirimavo Bandaranaike Vidyalaya, Colombo",
        "Sri Subhuthi National School, Battaramulla.",
        "St. Paul’s Girls School, Milagiriya",
        "Susamaya Wardana College",
        "Thurstan College, Colombo",
        "Veluwana College, Dematagoda",
        "Visakha Vidyalaya, Colombo",
        "Zahira College, Colombo",
        "Vivekananda College Colombo",
        "St. John's College Colombo",
        "Dharmapala Vidyalaya Kottawa",
        "Al Hikma College",
        "Al Badriya M.V Kahatowita",
        "Ananda Balika Vidyalaya Kotte",
        "Al Hidhaya Maha Vidyalaya",
        "All Saints' College, Colombo",
        "Ananda Sastralaya, Kotte",
        "Anurudhdha Balika Maha Vidyalaya, Dematagoda",
        "Basilica College, Colombo",
        "Atamie International School, Kotahena",
        "Clifton Girls School, Colombo",
        "C. W. W.r Kannangara Vidyalaya",
        "Dehiwala Central College",
        "Devi Balika Vidyalaya",
        "Dharmasena Attygala Balika Vidyalaya, Kesbewa.",
        "Dharmaraja Vidyalaya, Homagama",
        "Dudley Senanayake Maha Vidyalaya, Colombo",
        "Fathima Muslim Ladies College",
        "Girls' High School, Mount Lavinia",
                "Good Shepherd Convent, Colombo",
        "Gothami Balika Vidyalaya",
        "Herman Gminer School, Kesbewa",
        "Hewavitharana Maha Vidyalaya, Rajagiriya",
        "Indrajothi Vidyalaya, Battaramulla.",
        "Lanka Sabaha Primary School, Battaramulla.",
        "Lalith Athulamudali College, Mt- Lavinia",
        "Logos College, Havelock Town",
        "Janadhipathi Balika Vidyalaya, Nawala",
        "Karlshrue College, Colombo",
        "Kottawa Dharmapala Maha Vidyalaya",
        "Lumbini Vidyalaya",
        "M.D.H. Jayawardhana primary school, Padukka",
        "M.D.H. Jayawardhana Vidyalaya, Battaramulla.",
        "Malagala Maha Vidyalaya, Malagala, Padukka",
        "Meegoda Dharmaraja Vidyalaya, Meegoda",
        "Mutwal Hindu College, Colombo",
        "Musaeus Vidyalaya",
        "Presbyterian Girls School,Regent Street, Colombo",
        "President's College Maharagama",
                "Ramanathan Hindu Ladies College, Colombo",
        "Rathnawali Balika Maha Vidyalaya, Borella",
        "Samudradevi Balika Maha Vidyalaya, Nugegoda",
        "Sevalee Vidyalaya, Dematagoda",
        "Sirimavo Balika Vidyalaya",
        "Siri Piyarathana Maha Vidyalaya, Padukka",
        "Siri Piyarathana Kanshta Vidyalaya, Padukka",
        "Somaweera Chandrasiri Vidyalaya, Piliyandala.",
        "Sri Jayawardenepura Maha Vidyalaya, Kotte",
        "Sri Jayawardenapura Balika Mahaya Vidyalaya, Kotte",
        "Sri Rahula Maha Vidyalaya, Mulleriyawa New Town",
        "Sri Rahula Balika Vidyalaya, Malabe",
        "Sri Revatha Royal College, Nugegoda",
        "Sri Shuboothi Madaya Maha Vidyalaya, Baththaramulla",
        "St. Anne's Girls Maha Vidyalayam, Colombo",
                "St. Anthony's Balika Vidyalaya, Colombo",
                "St. Anthony's Boys Maha Vidyalayam, Colombo",
                "St.John's College-Nugegoda",
                "St. Joseph's Girls' School, Nugegoda",
        "St. Peter's College, Colombo",
                "St. Thomas'College, Kotte",
                "Susamaya Wardana Maha Vidyalaya, Borella",
        "Sunathra Davi Balika Vidyalaya, Pepiliyana",
        "Vidura College, Nawala",
        "Vidya Wardhana Vidyalaya, Battaramulla.",
        "Vipulanantha Tamil Maha Vidyalayam, Dematagoda",
        "Yashodara Balika Vidyalaya",
        "Zahira College, Maradana",
        "Vivekananda College Colombo",
        "wulfendal high school colombo",
        "Alethea School (Government recognised)",
        "Amal International School, Colombo",
        "Bishop's College, Colombo",
                "Buddhist Ladies College (semi-government)",
        "Carey College, Colombo",
        "Christ King College, Pannipitiya",
        "C.M.S. Ladies' College, Colombo",
                "Girls' High School, Mount Lavinia",
                "Good Shepherd Convent, Colombo",
        "Highlands College, Nugegoda",
        "Holy Family Convent, Colombo (semi-government)",
        "Holy Family Convent, Dehiwela (semi-government)",
        "Ikra International School, Colombo",
        "Ladies College, Colombo",
        "Mutwal Hindu College, Colombo",
        "Methodist College, Colombo (semi-government)",
        "Musaeus College, Colombo 7",
        "Our Lady of Victories Convent, Moratuwa",
        "St. Anthony's College, Wattala",
                "St Benedict's College Colombo",
                "St Bridget's Convent, Colombo",
                "St. Joseph's College, Colombo (semi-government)",
                "St. Joseph's Boys' College, Nugegoda (semi-government)",
        "St. Lawrence's Convent, Colombo (semi -government)",
                "St. Lucia's College Colombo 13",
                "St. Michael's College, Homagama",
                "St. Michael's College, Meegoda",
                "St. Paul's Girls School, Milagiriya (semi-government)",
                "St Peter's College, Colombo (semi-government)",
                "St. Sebastian's College, Moratuwa (semi-government)",
                "S. Thomas' College, Kotte (semi-government)",
                "S. Thomas' College, Mount Lavinia",
                "S. Thomas' Preparatory School, Kollupitiya",
                "Sujatha Vidyalaya, Nugegoda",
        "The School for the Blind",
        "The Learning Room, Avissawella",
        "Wesley College, Colombo (semi-government)",
        "Zahira College, Colombo",
        "St.Peter's college Gampaha (semi-government)",
        "Al- Manar International School, Colombo",
        "Alexor International College, Colombo",
        "Aba Beel International College",
        "Alethea International School, Colombo",
        "American College of Higher Education",
        "Amal International School, Colombo",
        "Asian International School",
        "Belvoir College International",
        "Bond International School",
        "Brightens College",
        "Buckingham International College",
        "Buddhist Ladies College International",
        "The British School in Colombo",
        "C.P.M Faith School",
        "College Of World Education",
        "Colombo International School",
        "Colombo Overseas School",
        "Colombo South International College",
        "Crescent Schools International",
        "Dharul Uloom Academy, Bambalapitiya",
        "Dhilshaath International College, Dematagoda",
        "Elizabeth Moir School, Colombo",
        "East Asian International College, Nugegoda",
        "Gateway College, Colombo",
        "Gateway College, Dehiwala",
        "Graceland International Academy- Mount Lavinia",
        "Green Bridge International College",
        "Harcourts International School",
        "Harrow International College",
        "Hejaaz International School[1]",
        "Highlands College",
        "Horizon College International",
        "Ikra International School",
        "Ilma International Girls' School",
                "Institute for London A/L Studies, Havelock Town",
        "Jennings International School",
        "J.M.C. International School",
        "Kingston College international",
        "Learnium International School, Ethul Kotte",
        "Lakeland Inter-American School",
        "Lead the Way Girls' International",
                "Learningexcellence International College of Education, Battaramulla",
        "Leeds International School, Kotte",
        "LPF Academy",
        "Lyceum International School",
        "Minhal International Boys' School",
                "Mukarramah International School",
        "The Overseas School of Colombo",
        "Oxford College International",
        "OKI International School, Wattala",
        "OKI International School, Kiribathgoda",
        "OKI International School, Kadana",
        "Rawdha Aysha, Wellawatha",
        "Readway International College of Education, Dematagoda.",
        "Rotary International School",
        "Royal Institute, Nugegoda",
        "Royal Institute, Havelock Town",
        "Royal Institute, Maharagama",
        "Royal Institute Girls school, Havelock Town",
        "Linfield College International[2]",
        "Southgate International Academy",
        "Stafford International School",
        "St. Nicholas' International School",
                "Willesden College International",
        "Wycherley International School, Colombo",
        "Winway International School",
        "The Victoria International College",
        "York International School - Sapugaskanda",
        "York International School - Wattala",
        "York International School - Kadawatha",
        "Yoshida International School",};

        ArrayAdapter schoolAdaptor = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, schools);
        school_spinner.setAdapter(schoolAdaptor);
        final LinearLayout advanced_layout = (LinearLayout) findViewById(R.id.advance_layout);
        advanced_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (advanced_visibility){
                    advanced_layout.setVisibility(View.GONE);
                    advanced_icon.setImageResource(android.R.drawable.ic_input_add);
                    advanced_visibility = false;
                }else {
                    advanced_layout.setVisibility(View.VISIBLE);
                    advanced_visibility = true;
                    advanced_icon.setImageResource(android.R.drawable.ic_delete);
                }
            }
        };
        advanced_btn = (TextView) findViewById(R.id.advanced_btn);
        advanced_btn.setOnClickListener(advanced_listener);
        advanced_icon = (ImageView) findViewById(R.id.advanced_icon);
        advanced_icon.setOnClickListener(advanced_listener);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
