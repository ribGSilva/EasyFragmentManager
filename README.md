# EasyFragmentManager
A Fragment Manager easy to use

This Manager works with supportFragments, therefore, use supportFragments. 
I DID NOT TEST with the FragmentManager from android.app which uses Fragment not supportFragmet

To use the class, you need to first, settup the enum of fragments that you will have in the app:

        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put(FragmentTypes.TYPE_1.name(), FragmentType1.class);
        classMap.put(FragmentTypes.TYPE_2.name(), FragmentType2.class);

I create a enum with all the fragments that I will use:

        public enum FragmentTypes {
            TYPE_1,
            TYPE_2
        }
    
Extend FragmentActivity to your activity:

    public class MainActivity extends FragmentActivity {
    
    }
    
    
Then initiate the Manager in your main:

    private EasyFragmentManager easyFragmentManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put(FragmentTypes.TYPE_1.name(), FragmentType1.class);
        classMap.put(FragmentTypes.TYPE_2.name(), FragmentType2.class)
      
        easyFragmentManager = EasyFragmentManager.newInstance(
                                    this, // main activity that extends FragmentActivity
                                    R.id.contentPanel, // place where the fragments will appear
                                    classMap, // the map with all the fragment
                                    FragmentTypes.TYPE_1.name(), // the first fragment that will pop up
                                    getSupportFragmentManager() // pass the supportFragmentManager from your activity
                                    ); 

    }
    
And add this method to the "onBackPressed" method:

     @Override
    public void onBackPressed() {
        super.onBackPressed();
        easyFragmentManager.updateManagerOnBackStackChange(); //will keep the manager updated
    }
    
To get the current fragment, call the method "getCurrentFragment()"

To get the current fragmentType, call the method "getCurrentFragmentType()"
    
To change to other fragment just call this method:

    easyFragmentManager.changeFragment(
        FragmentTypes.TYPE_2.name(), // the next fragment 
        false // set 'true' to stack the current fragment (which will be replaced) on the backstack, 
              // so latter, when (end if) you call it once again, it will be caught from the backstack,
              // therefore, you get performance and memory.
              // if 'false', once you call it again, the fragment will be recreated.
        );
 
 To change the fragment from another fragment, use the "EasyFragmentController":
 
 1 - Implement the activity with EasyFragmentController and implement the method:
 
      public class MainActivity extends FragmentActivity implements EasyFragmentController {
            ...
            
        @Override
        public void changeFragment(String newFragment, boolean addToBackStack) {
            easyFragmentManager.changeFragment(newFragment, addToBackStack);
        }
            
            ...
      }
 
 2 - Create the field EasyFragmentController on the fragment
 
        private EasyFragmentController easyFragmentController;
 
 3 - Override the method 'onAttach(Context context)' from the fragment and initiate the EasyFragmentController
 
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);

            if (context instanceof EasyFragmentController)
                easyFragmentController = (EasyFragmentController) context;
        }
 
 4 - Call the method from anywhere :)
 
        easyFragmentController.changeFragment(FragmentTypes.TYPE_1.name(), true);
