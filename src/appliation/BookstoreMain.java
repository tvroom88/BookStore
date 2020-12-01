/*
 * TCSS 305 Assignment 1 - Bookstore
 */

package appliation;

import io.InventoryLoader;
import java.awt.EventQueue;
import java.util.List;
import res.R;
import view.LoginFrame;

/**
 * BookstoreMain provides the main method for a simple shopping cart GUI
 * displayer and calculator.
 * 
 * 
 * @author Jaehong Lee Student
 * @version Fall 2020 
 */

public final class BookstoreMain {  

    /**
     * A private constructor, to prevent external instantiation.
     */
    private BookstoreMain() {
    }

    /**
     * The main() method - displays and runs the bookstore GUI.
     * 
     * @param theArgs Command line arguments, ignored by this program.
     */
    public static void main(final String... theArgs) {
        
        final List<String> campusNames =
                        InventoryLoader.readConfigurationFromFile(R.Strings.IO_FILE_LOCATION
                                                             + R.Strings.IO_CONFIG_FILE);
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame(campusNames).setVisible(true);     
            }
        });

    } // end main()

} // end class BookstoreMain