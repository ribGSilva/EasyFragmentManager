# FragmentManagerPlus
A Fragment Manager easy to use

This Manager works with supportFragments, therefore, use supportFragments. 
I DID NOT TEST with the FragmentManager from android.app which uses Fragment not supportFragmet

To use the class, you need to first, settup the enum of fragments that you will have in the app:

    public enum FragmentTypes {
        // follow this pattern
        TYPE_1(FragmentType1.class),
        TYPE_2(FragmentType1.class);

        public final Class<?> fragmentClass;

        FragmentTypes(Class<?> fragmentClass) {
            this.fragmentClass = fragmentClass;
        }
    }
    
Extend FragmentActivity to your activity:

    public class MainActivity extends FragmentActivity {
    
    }
    
    
Then initiate the Manager in your main:

    private FragmentManagerPlus fragmentManagerPlus;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
        fragmentManagerPlus = FragmentManagerPlus.newInstance(
                                    this, // main activity that extends FragmentActivity
                                    R.id.contentPanel, // place where the fragments will appear
                                    FragmentTypes.TYPE_1, // the first fragment that will pop up
                                    getSupportFragmentManager() // pass the supportFragmentManager from your activity
                                    ); 

    }
    
To get the current fragment, call the method "getCurrentFragment()"

To get the current fragmentType, call the method "getCurrentFragmentType()"
    
To change to other fragment just call this method:

    plus.changeFragment(
        FragmentTypes.TYPE_2, // the next fragment 
        false // set 'true' to stack the current fragment (which will be replaced) on the backstack, 
              // so latter, when (end if) you call it once again, it will be caught from the backstack,
              // therefore, you get performance and memory.
              // if 'false', once you call it again, the fragment will be recreated.
        );
 
 To change the fragment from another fragment, use the "FragmentManagerPlus.FragmentControllerPlus":
 
 1 - Implement the activity with FragmentManagerPlus.FragmentControllerPlus and implement the method:
 
      public class MainActivity extends FragmentActivity implements FragmentManagerPlus.FragmentControllerPlus {
            ...
            
            @Override
            public void changeFragment(FragmentTypes newFragment, boolean addToBackStack) {
                fragmentManagerPlus.changeFragment(newFragment, addToBackStack);
            }
            
            ...
      }
 
 2 - Create the field FragmentManagerPlus.FragmentControllerPlus on the fragment
 
        private FragmentManagerPlus.FragmentControllerPlus fragmentController;
 
 3 - Override the method 'onAttach(Context context)' from the fragment and initiate the FragmentManagerPlus.FragmentControllerPlus
 
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);

            if (context instanceof FragmentManagerPlus.FragmentControllerPlus)
                fragmentController = (FragmentManagerPlus.FragmentControllerPlus) context;
        }
 
 4 - Call the method from anywhere :)
 
        fragmentController.changeFragment(FragmentTypes.TYPE_2, true);
