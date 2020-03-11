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
import com.pekinsoft.northwind.basic.Application;
import com.pekinsoft.northwind.utils.Logger;
import com.pekinsoft.northwind.utils.enums.SysExits;
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
        
        try {
            if (Application.DEBUGGING) {
                Application.log.setLevel(Logger.DEBUG);
            } else {
                Application.log.setLevel(Logger.CONFIG);
            }
        } catch (InvalidLoggingLevelException ex) {
            Application.log.error(ex, "Not handled locally.");
        }
        
        Application.exit(SysExits.EX_OK);
    }

    public static void exit() {
        System.exit(0);
    }

}
