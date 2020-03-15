/*
 * Copyright (C) 2020 PekinSOFT Systems
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * *****************************************************************************
 * *****************************************************************************
 *  Project    :   Northwind-Basic
 *  Class      :   Startup.java
 *  Author     :   Sean Carrick
 *  Created    :   Mar 8, 2020 @ 12:30:11 PM
 *  Modified   :   Mar 8, 2020
 *  
 *  Purpose:
 *  
 *  Revision History:
 *  
 *  WHEN          BY                  REASON
 *  ------------  ------------------- ------------------------------------------
 *  Mar 8, 2020  Sean Carrick        Initial creation.
 * *****************************************************************************
 */

package com.pekinsoft.northwind;

import com.pekinsoft.northwind.accounting.exceptions.InvalidAccountingDataException;
import com.pekinsoft.northwind.accounting.Math;
import com.pekinsoft.northwind.basic.Application;
import com.pekinsoft.northwind.desktop.MainFrame;
import com.pekinsoft.northwind.utils.ArgumentParser;
import com.pekinsoft.northwind.utils.exceptions.InvalidLoggingLevelException;

/**
 *
 * @author Sean Carrick &lt;sean at pekinsoft dot com&gt;
 */
public class Startup {

    /**
     * Main entry point method for the Startup Project
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Perform application startup procedures.
        System.out.println("Application: " + Application.TITLE);
        System.out.println("Version: " + Application.MAJOR + "." +
                Application.MINOR + "." + Application.REVISION + " Build " +
                Application.BUILD);
        
        int loggingLevel = 0;
        
        // Parse out the arguments to the application.
        if ( args.length > 0 ) {
            // We know that we have arguments. First, let's parse them out.
            ArgumentParser arguments = new ArgumentParser(args);
            
            if ( arguments.isSwitchPresent("--debug") ||
                    arguments.isSwitchPresent("-d") ) {
                loggingLevel = Application.log.DEBUG;
            } else if ( arguments.isSwitchPresent("--config") ||
                    arguments.isSwitchPresent("-c") ) {
                loggingLevel = Application.log.CONFIG;
            } else if ( arguments.isSwitchPresent("--info") ||
                    arguments.isSwitchPresent("-i") ) {
                loggingLevel = Application.log.INFO;
            } else if ( arguments.isSwitchPresent("--warn") ||
                    arguments.isSwitchPresent("-w") ) {
                loggingLevel = Application.log.WARN;
            } else if ( arguments.isSwitchPresent("--critical") ||
                    arguments.isSwitchPresent("-x") ) {
                loggingLevel = Application.log.CRITICAL;
            }
        }
        
        try {
            Application.log.setLevel(loggingLevel);
        } catch (InvalidLoggingLevelException ex) {
            Application.log.error(ex, "Not handled locally.");
        }
        
        try {
            // Perform calculations using the functions in the Math class.
            double temp = Math.CurrentRatio(8000.00, 2000.00);
            temp = Math.DuPontROI(260.0, 15000.0);
            temp = Math.NetIncome(173155.55, 126782.03);
            temp = Math.PerMileBreakdown(173155.55, 110084);
            temp = Math.ReturnOnInvestment(0.07, 8500.0);
            boolean tmp = Math.isBalanced(23000.0, 15000.0, 8000.0);
        } catch (InvalidAccountingDataException ex) {
            Application.log.handledError(ex, "Caught the error...");
        }
        
        Application.log.config("Showing the MainFrame window.");
        MainFrame.main(args);
    }

    public static void exit() {
        System.exit(0);
    }

}
