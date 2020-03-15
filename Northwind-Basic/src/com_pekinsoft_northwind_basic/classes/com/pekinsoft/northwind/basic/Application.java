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
 *  Class      :   Application.java
 *  Author     :   Sean Carrick
 *  Created    :   Mar 8, 2020 @ 12:46:44 PM
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

package com.pekinsoft.northwind.basic;

import com.pekinsoft.northwind.utils.Logger;
import com.pekinsoft.northwind.utils.enums.SysExits;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Sean Carrick &lt;sean at pekinsoft dot com&gt;
 * 
 * @version 0.1.0
 * @since 0.1.0
 */
public class Application {
    //<editor-fold defaultstate="collapsed" desc="Public Static Constants">
    public static final Logger log;
    public static final boolean DEBUGGING;
    
    public static final int MAJOR;
    public static final int MINOR;
    public static final int REVISION;
    public static final long BUILD;
    
    public static final String NAME = "Northwind";
    public static final String TITLE = "Northwind Traders Complete Accounting";
    public static final String EDITION = "Basic Edition";
    public static final String VENDOR = "PekinSOFT Systems";
    public static final String VENDOR_WEB = "https://www.pekinsoft.com";
    public static final String VENDOR_PHONE = "(309) 989-0672";
    public static final String PROJ_LEAD = "Sean Carrick";
    public static final String PROJ_EMAIL = "sean@pekinsoft.com";
    
    public static final String FILE_SEPARATOR;
    public static final String TOP_LEVEL_DIR;
    public static final String LOG_DIR;
    public static final String ERR_DIR;
    public static final String DATA_DIR;
    public static final String COMPANY_DIR;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Private Member Fields">
    private static final Properties props = new Properties();
    //</editor-fold>

    static {
        if ( System.getProperty("os.name").toLowerCase().contains("windows") ) {
            FILE_SEPARATOR = "\\";
        } else {
            FILE_SEPARATOR = "/";
        }
        
        TOP_LEVEL_DIR = System.getProperty("user.home") + FILE_SEPARATOR
                + ".northwind" + FILE_SEPARATOR;
        ERR_DIR = TOP_LEVEL_DIR + "var" + FILE_SEPARATOR + "err" 
                + FILE_SEPARATOR;
        DATA_DIR = TOP_LEVEL_DIR + "data" + FILE_SEPARATOR;
        COMPANY_DIR = DATA_DIR + FILE_SEPARATOR + "companies" 
                + FILE_SEPARATOR;

        LOG_DIR = TOP_LEVEL_DIR + "var" + FILE_SEPARATOR + "logs" 
                + FILE_SEPARATOR;
        File logPath = new File(LOG_DIR);
        File logFile = new File(logPath.getAbsoluteFile() + FILE_SEPARATOR
                + "application.log");
        if ( !logPath.exists() ) {
                logPath.mkdirs();
        }
        if ( !logFile.exists() ) {
            try {
                logFile.createNewFile();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace(System.err);
            }
        }
        log = new Logger(logFile.getAbsolutePath());
        log.enter("com.is2300.northwind.Application", "static initializer{}");

        log.debug("Attempting to load the application properties from file.");
        try ( FileReader in = new FileReader(new File(
                System.getProperty("user.home") 
                + FILE_SEPARATOR + "." + 
                NAME.toLowerCase() + ".conf")); ) {
            props.load(in);
        } catch ( IOException ex ) {
            // If we come in this error handler, odds are the application hasn't
            //+ been run before, which is why the file doesn't exist. Therefore,
            //+ we'll just write a configuration log entry, advising that this
            //+ exception was thrown, but not write an error message to the log.
            log.config("FIRST RUN: No properties file exists. It will be "
                    + "created when the application exits.");
            
            // Since this is the first time the system has been run, we are 
            //+ going to create the file structure right here.
            File topLevelDir = new File(TOP_LEVEL_DIR);
            File logDir = new File (LOG_DIR);
            File errDir = new File(ERR_DIR);
            File dataDir = new File(DATA_DIR);
            File companyDir = new File(COMPANY_DIR);
            topLevelDir.mkdirs();
            logDir.mkdirs();
            errDir.mkdirs();
            dataDir.mkdirs();
            companyDir.mkdirs();
            // Folder structure created \\
        }
        DEBUGGING = Boolean.parseBoolean(props.getProperty("debugging", "false"));
        long bui = Long.valueOf(props.getProperty("app.build", "0"));
        int rev = Integer.valueOf(props.getProperty("app.revision", "0"));
        int min = Integer.valueOf(props.getProperty("app.minor", "1"));
        int maj = Integer.valueOf(props.getProperty("app.major", "0"));
        
        log.debug("Calculating the version of the application.");
        if ( DEBUGGING ) { 
            if ( bui == 0 ) {
                bui = 1583;
            } else {
                bui++;
            }

            if ( bui > 9999 ) {
                bui = 0;
                rev++;
            }
            if ( rev > 100 ) {
                rev = 0;
                min++;
            }
            if ( min > 10 ) {
                min = 0;
                maj++;
            }
        }
        
        MAJOR = maj; props.setProperty("app.major", String.valueOf(MAJOR));
        MINOR = min; props.setProperty("app.minor", String.valueOf(MINOR));
        REVISION = rev; props.setProperty("app.revision", String.valueOf(REVISION));
        BUILD = bui; props.setProperty("app.build", String.valueOf(BUILD));
        log.debug("---> Application version set at: " + MAJOR + "." + MINOR +
                "." + REVISION + " Build " + BUILD);
        
        
        log.exit("com.is2300.northwind.Application", "static initializer{}");
    }
    
    //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
    private Application() {
        /* Static class...cannot be instantiated. */
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public Static Methods">
    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns `null`
     * if the property is not found.
     * 
     * @param   key the property key
     * @return  the value in this property list with the specified key value
     * @see #setProperty
     * @see #defaults
     */
    public static String getProperty(String key) {
        return props.getProperty(key);
    }
    
    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns the
     * default value argument if the property is not found.
     *
     * @param   key            the hashtable key.
     * @param   defaultValue   a default value.
     *
     * @return  the value in this property list with the specified key value.
     * @see     #setProperty
     * @see     #defaults
     */
    public static String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }
    
    /**
     * Calls the {@code Hashtable} method {@code put}. Provided for
     * parallelism with the {@code getProperty} method. Enforces use of
     * strings for property keys and values. The value returned is the
     * result of the {@code Hashtable} call to {@code put}.
     *
     * @param key the key to be placed into this property list.
     * @param value the value corresponding to {@code key}.
     * @return     the previous value of the specified key in this property
     *             list, or {@code null} if it did not have one.
     * @see #getProperty
     * @since    1.2
     */
    public static void setProperty(String key, String value) {
        props.setProperty(key, value);
    }
    
    /**
     * Provides a means to exit the application in a normalized manner. By using
     * this method to exit, we are able to provide useful meaning to the 
     * underlying operating system, such as the status by which we are exiting,
     * meaning, whether or not it is a normal exit or an exit with an error.
     * 
     * When exiting using this method, the application is able to store to disk
     * any properties settings that have been updated, as well as perform any
     * other necessary housekeeping tasks, such as removing any temporary files
     * that were created.
     * 
     * @param exitStatus `com.is2300.utilities.enums.SysExits` enumeration value
     */
    public static void exit(SysExits exitStatus) {
        log.enter("com.is2300.northwind.Application", "exit", exitStatus.toInt());
        props.setProperty("app.major", String.valueOf(MAJOR));
        props.setProperty("app.minor", String.valueOf(MINOR));
        props.setProperty("app.revision", String.valueOf(REVISION));
        props.setProperty("app.build", String.valueOf(BUILD));
        
        log.config("Attempting to write the properties to file.");
        File propsFile = new File(System.getProperty("user.home") 
                + FILE_SEPARATOR + "." + 
                NAME.toLowerCase() + ".conf");
        try ( FileOutputStream out = new FileOutputStream(propsFile); ) {
            props.store(out, "Written at " + TITLE + " exit.");
            log.debug("Properties file written to: " 
                    + propsFile.getAbsolutePath());
        } catch ( IOException ex ) {
            log.error(ex, "Storing properties file from Application.exit()");
        }
        
        log.debug("Calling System.exit(" + exitStatus.toInt() + ")");
        log.debug("---> Exit Status: " + exitStatus.toString());
        log.exit("com.is2300.northwind.Application", "exit", exitStatus.toInt());
        System.exit(exitStatus.toInt());
    }
    
    public static String version() {
        return "Version " + MAJOR + "." + MINOR + "." + REVISION + "." + BUILD;
    }
    
    public static String getComments() {
        StringBuilder sb = new StringBuilder();
        sb.append("Copyright© 2007-2019 ").append(Application.VENDOR).append("\n\n");
        sb.append("Northwind Traders Complete Accounting aims to become the de" +
                " facto standard by which all future accounting systems " +
                "are measured. Primarily, we aim to have Northwind Traders " +
                "become the number one accounting system for small-business, " +
                "owner/operator truck drivers, providing financial reporting" +
                " that makes sense for a trucking company, including per mile"
                + " breakdown of all financial information.");
        
        return sb.toString();
    }
    //</editor-fold>
}
